package com.epam.evnote.services;

import static org.junit.Assert.*;

import com.epam.evnote.config.ApplicationConfiguration;
import com.epam.evnote.domain.Note;
import com.epam.evnote.domain.Notepad;
import com.epam.evnote.domain.User;
import com.epam.evnote.repository.UserRepository;
import com.epam.evnote.service.impl.NotepadService;
import com.epam.evnote.service.impl.UserService;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.java.Log;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class NotepadServiceTest {


  @Autowired
  NotepadService notepadService;


  @Autowired
  UserRepository userRepository;
  User user;

  @Before
  public void setUp() {
    User test = new User();
    test.setLogin("TestForNotepad");
    test.setPassword("000");
    user = userRepository.saveAndFlush(test);
  }

  @Transactional
  @Test
  public void createNotepad() throws Exception {
    notepadService.createNotepad("Job", user);
    Notepad job = notepadService.getByTitle("Job", user);
    String login = job.getUser().getLogin();
    assertEquals(login, "TestForNotepad");
  }

  @Test
  public void saveOrUpdate() throws Exception {
    Notepad notepad = new Notepad();
    notepad.setTitle("Home");
    notepad.setUser(user);
    notepadService.saveOrUpdate(notepad);
    Notepad home = notepadService.getByTitle("Home", user);
    assertNotNull(home);
  }

  @Transactional
  @Test
  public void getById() throws Exception {
    Notepad notepad = new Notepad();
    notepad.setTitle("Home");
    notepad.setUser(user);
    notepadService.saveOrUpdate(notepad);
    Notepad home = notepadService.getByTitle("Home",user);
    Notepad byId = notepadService.getById(home.getId());
    assertEquals(home.getTitle(), byId.getTitle());
  }

  @Test
  public void getAll() throws Exception {
    List<Notepad> all = notepadService.getAll();
    assertNotNull(all);
  }

  @Transactional
  @Test
  public void delete() throws Exception {
    Notepad notepad = new Notepad();
    notepad.setTitle("Home");
    notepad.setUpdateTime(LocalDate.now());
    notepad.setUser(user);
    notepadService.saveOrUpdate(notepad);
    notepadService.delete(notepad.getId());
    Notepad home = notepadService.getByTitle("Home",user);
    assertNull(home);
  }

}