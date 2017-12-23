package com.epam.evnote.config;

import com.epam.evnote.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  @Qualifier("customUserDetailsService")
  UserDetailsService userDetailsService;

  @Autowired
  public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService);
    auth.authenticationProvider(authenticationProvider());

    auth.inMemoryAuthentication()
        .passwordEncoder(NoOpPasswordEncoder.getInstance()) // fix for Spring 5.0 version change b
        .withUser("user").password("user").roles("USER");
    //auth.inMemoryAuthentication().withUser("admin").password("admin").roles("ADMIN");
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.csrf().disable();
    http.httpBasic();
    http.authorizeRequests().antMatchers("/console/**").permitAll();
    http.authorizeRequests().antMatchers("/create/**").permitAll();
    http.authorizeRequests().antMatchers("/users/**").permitAll();
    http.headers().frameOptions().disable();
    http.authorizeRequests()
//                .antMatchers("/notepads/**").access("hasRole('ROLE_USER')")
        .antMatchers("/info/**").access("hasRole('ROLE_USER')")
        //.antMatchers("/confidential/**").access("hasRole('ROLE_SUPERADMIN')")
        .and().formLogin().defaultSuccessUrl("/", false);

    http.logout().logoutUrl("/logout");
    http.logout().logoutSuccessUrl("/");
    http.logout()
        .clearAuthentication(true)
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID");

  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public DaoAuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder());
    return authenticationProvider;
  }
}
