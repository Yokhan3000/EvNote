package com.epam.evnote.services;

import static org.junit.Assert.*;

import com.epam.evnote.config.ApplicationConfiguration;
import com.epam.evnote.domain.Note;
import com.epam.evnote.domain.Notepad;
import com.epam.evnote.domain.User;
import com.epam.evnote.repository.NotepadRepository;
import com.epam.evnote.repository.UserRepository;
import com.epam.evnote.service.impl.NoteService;
import java.sql.Timestamp;
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
public class NoteServiceTest {


  @Autowired
  NoteService noteService;

  @Autowired
  NotepadRepository notepadRepository;

  @Autowired
  UserRepository userRepository;

  Notepad testNotepad;

  @Before
  public void setUp() {
    User user = new User();
    user.setLogin("TestForNoteService");
    user.setPassword("999");
    User user1 = userRepository.saveAndFlush(user);
    Notepad notepad = new Notepad();
    notepad.setUser(user1);
    notepad.setTitle("NotepadForNotes");
    notepad.setUpdateTime(Timestamp.valueOf(LocalDateTime.now()));
    testNotepad = notepadRepository.saveAndFlush(notepad);

  }

  @Test
  public void saveOrUpdate() throws Exception {
    Note note = new Note();
    note.setTitle("testNote");
    note.setNotepad(testNotepad);
    note.setCreationTime(Timestamp.valueOf(LocalDateTime.now()));
    note.setNoteBody("Some body here");

    noteService.saveOrUpdate(note);

    Note testNote = noteService.getByTitle("testNote");
    assertNotNull(testNote);
    assertEquals(testNote.getNoteBody(), "Some body here");
  }

  @Transactional
  @Test
  public void getById() throws Exception {
    Note note = new Note();
    note.setTitle("testNote");
    note.setNotepad(testNotepad);
    note.setCreationTime(Timestamp.valueOf(LocalDateTime.now()));
    note.setNoteBody("Some body here");

    noteService.saveOrUpdate(note);
    Note testNote = noteService.getByTitle("testNote");
    Note byId = noteService.getById(testNote.getId());
    assertNotNull(byId);
    assertEquals(byId.getNoteBody(), testNote.getNoteBody());
  }

  @Test
  public void getAll() throws Exception {
    List<Note> all = noteService.getAll();
    assertNotNull(all);
  }

  @Test
  public void delete() throws Exception {
    Note note = new Note();
    note.setTitle("testNote");
    note.setNotepad(testNotepad);
    note.setCreationTime(Timestamp.valueOf(LocalDateTime.now()));
    note.setNoteBody("Some body here");

    noteService.saveOrUpdate(note);

    Note testNote = noteService.getByTitle("testNote");
    assertEquals(testNote.getNoteBody(), "Some body here");

    noteService.delete(testNote);
    Note deletedNote = noteService.getByTitle("testNote");
    assertNull(deletedNote);
  }

}