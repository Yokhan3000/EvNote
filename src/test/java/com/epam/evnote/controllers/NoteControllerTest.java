package com.epam.evnote.controllers;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.epam.evnote.config.ApplicationConfiguration;
import com.epam.evnote.domain.Mark;
import com.epam.evnote.domain.Note;
import com.epam.evnote.service.impl.MarkService;
import com.epam.evnote.service.impl.NoteService;
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
public class NoteControllerTest {

  @Autowired
  private WebApplicationContext wac;

  @Autowired
  MarkService markService;
  @Autowired
  NoteService noteService;
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
  public void getAllNotesByNotepad() throws Exception {
    mvc.perform(get("/notepads/1/notes")
        .with(httpBasic("user", "user"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void getNoteById() throws Exception {
    mvc.perform(get("/notes/1")
        .with(httpBasic("user", "user"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void createOrUpdateNote() throws Exception {
    Note note = new Note();
    note.setTitle("noteTest");
    ObjectMapper mapper = new ObjectMapper();
    String json = mapper.writeValueAsString(note);
    mvc.perform(post("/notes")
        .param("id", "1")
        .content(json)
        .with(httpBasic("user", "user"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isCreated());
  }

  @Test
  public void deleteNote() throws Exception {
    mvc.perform(delete("/notes/1")
        .with(httpBasic("user", "user"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void getNotesByMark() throws Exception {
    mvc.perform(get("/mark/JobOnly/notes")
        .with(httpBasic("user", "user"))
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void addMarkToNote() throws Exception {
    mvc.perform(post("/mark/note")
        .with(httpBasic("user", "user"))
        .param("markId", "1")
        .param("noteId", "1")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

  @Test
  public void deleteMarkFromNote() throws Exception {
    mvc.perform(delete("/mark/note")
        .with(httpBasic("user", "user"))
        .param("markId", "2")
        .param("noteId", "2")
        .contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk());
  }

}