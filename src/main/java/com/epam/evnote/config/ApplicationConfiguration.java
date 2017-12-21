package com.epam.evnote.config;

import java.sql.SQLException;
import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.core.io.Resource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan("com.epam.evnote.repository")
@EnableJpaRepositories("com.epam.evnote.repository")
@ImportResource("classpath:contex.xml")
public class ApplicationConfiguration {

  @Value("classpath:import.sql")
  private Resource dataScript;
  @Bean
  public DataSource dataSource() throws SQLException {
    EmbeddedDatabase dataSource = new EmbeddedDatabaseBuilder().setName("test")
        .setType(EmbeddedDatabaseType.H2)
        .build();
    return dataSource;
  }

  @Bean
  public JpaVendorAdapter jpaVendorAdapter() {
    HibernateJpaVendorAdapter bean = new HibernateJpaVendorAdapter();
    bean.setDatabase(Database.H2);
    bean.setGenerateDdl(true);
    return bean;
  }

  @Bean
  public LocalContainerEntityManagerFactoryBean entityManagerFactory(
      DataSource dataSource, JpaVendorAdapter jpaVendorAdapter) {
    LocalContainerEntityManagerFactoryBean bean = new LocalContainerEntityManagerFactoryBean();
    bean.setDataSource(dataSource);
    bean.setJpaVendorAdapter(jpaVendorAdapter);
    bean.setPackagesToScan("com.epam.evnote");
    return bean;
  }

  @Bean
  public JpaTransactionManager transactionManager(EntityManagerFactory emf) {
    return new JpaTransactionManager(emf);
  }

  @Bean
  public DataSourceInitializer dataSourceInitializer() throws Exception{
    DataSourceInitializer initializer = new DataSourceInitializer();
    initializer.setDataSource(dataSource());

    ResourceDatabasePopulator databasePopulator = new ResourceDatabasePopulator();
    databasePopulator.setContinueOnError(true);
    databasePopulator.addScript(dataScript);
    initializer.setDatabasePopulator(databasePopulator);
    return initializer;

  }

}
