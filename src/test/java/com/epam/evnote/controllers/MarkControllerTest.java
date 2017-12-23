package com.epam.evnote.controllers;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.evnote.config.ApplicationConfiguration;
import com.epam.evnote.domain.Mark;
import com.fasterxml.jackson.databind.ObjectMapper;
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
public class MarkControllerTest {

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
  public void getAllMarksByUser() throws Exception {
    mvc.perform(get("/marks")
        .with(httpBasic("user", "user"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void createOrUpdateMark() throws Exception {
    Mark mark = new Mark();
    mark.setName("Tag");

    ObjectMapper objectMapper = new ObjectMapper();
    String json = objectMapper.writeValueAsString(mark);

    mvc.perform(post("/marks")
        .content(json)
        .with(httpBasic("user", "user"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());

  }

  @Test
  public void deleteMark() throws Exception {
    mvc.perform(delete("/marks/1")
        .with(httpBasic("user", "user"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

}