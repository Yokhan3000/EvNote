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
public class NotepadTest {

  Notepad test;

  @Before
  public void setUp() {
    test = new Notepad();
    test.setTitle("title");
    test.setUser(new User());
    test.setId(99L);
    test.setUpdateTime(LocalDate.now());
  }

  @Test
  public void equals() throws Exception {
    assertNotEquals(test, new Notepad());
  }

  @Test
  public void hashCodeTest() throws Exception {
    Notepad notepad = new Notepad();
    notepad.setId(20L);
    Notepad notepad1 = new Notepad();
    notepad1.setId(20L);
    assertEquals(notepad, notepad1);
  }

  @Test
  public void getId() throws Exception {
    assertNotNull(test.getId());
  }

  @Test
  public void getUser() throws Exception {
    assertNotNull(test.getUser());

  }

  @Test
  public void getTitle() throws Exception {
    assertNotNull(test.getTitle());

  }

  @Test
  public void getUpdateTime() throws Exception {
    assertNotNull(test.getUpdateTime());
  }


}