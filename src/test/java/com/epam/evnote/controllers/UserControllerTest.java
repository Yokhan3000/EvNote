package com.epam.evnote.controllers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.evnote.config.ApplicationConfiguration;
import com.epam.evnote.service.impl.UserService;
import javax.annotation.Resource;
import javax.servlet.Filter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Mikhail Chtetsov on 19/12/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfiguration.class})
@WebAppConfiguration
public class UserControllerTest {

  @Autowired
  private UserService userService;
  @Autowired
  private WebApplicationContext wac;
  private MockMvc mvc;
  @Resource
  private FilterChainProxy springSecurityFilterChain;

  @Before
  public void setupSpec() throws Exception {
    mvc = MockMvcBuilders.webAppContextSetup(wac)
        .apply(springSecurity())
        .addFilter(springSecurityFilterChain)
        .build();
  }

  @Test
  public void getAllUsers() throws Exception {
    mvc.perform(get("/users/1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }
}
