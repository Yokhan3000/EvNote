package com.epam.evnote.domain;

import static org.junit.Assert.*;

import com.epam.evnote.config.ApplicationConfiguration;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import lombok.extern.java.Log;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class NoteTest {

  Note test;

  @Before
  public void setUp(){
    test = new Note();
    test.setId(100L);
    test.setNoteBody("body");
    test.setCreationTime(LocalDate.now());
    test.setNotepad(new Notepad());
    test.setTitle("title");
  }
  @Test
  public void equals() throws Exception {
    Note note = new Note();
    note.setId(100L);
    note.setNoteBody("body");
    note.setCreationTime(LocalDate.now());
    note.setNotepad(new Notepad());
    note.setTitle("title");
    Note note1 = new Note();
    assertNotEquals(note, note1);
  }

  @Test
  public void hashCodeTest() throws Exception {
    Note note = new Note();
    note.setTitle("note");
    Note note1 = new Note();
    note1.setTitle("note");


    assertEquals(note.hashCode(), note1.hashCode());
  }

  @Test
  public void getId() throws Exception {
    assertNotNull(test.getId());
  }

  @Test
  public void getNotepad() throws Exception {
    assertNotNull(test.getNotepad());

  }

  @Test
  public void getTitle() throws Exception {
    assertNotNull(test.getTitle());

  }

  @Test
  public void getNoteBody() throws Exception {
    assertNotNull(test.getNoteBody());

  }

  @Test
  public void getCreationTime() throws Exception {
    assertNotNull(test.getCreationTime());

  }







}