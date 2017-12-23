package com.epam.evnote.controllers;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.epam.evnote.config.ApplicationConfiguration;
import com.epam.evnote.domain.User;
import com.epam.evnote.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.List;
import javax.annotation.Resource;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Mikhail Chtetsov on 23/12/2017.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class UserControllerTest {

  @Autowired
  private WebApplicationContext wac;
  private MockMvc mvc;
  @Resource
  private FilterChainProxy springSecurityFilterChain;

  @Before
  public void setUp() {
    mvc = MockMvcBuilders.webAppContextSetup(wac)
        .addFilter(springSecurityFilterChain)
        .build();
  }

  @Test
  public void getAllUsers() throws Exception {
    mvc.perform(get("/users")
        .with(httpBasic("user", "user"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void createPerson() throws Exception {
    User user = new User();
    user.setLogin("Login");
    ObjectMapper mapper = new ObjectMapper();
    String jUser = mapper.writeValueAsString(user);
    user.setPassword("Password");
    mvc.perform(post("/create").content(jUser)
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void findById() throws Exception {
    mvc.perform(get("/users/1")
        .with(httpBasic("user", "user"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }


}