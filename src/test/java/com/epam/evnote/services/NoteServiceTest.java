package com.epam.evnote.services;

import static org.junit.Assert.*;

import com.epam.evnote.config.ApplicationConfiguration;
import com.epam.evnote.domain.Mark;
import com.epam.evnote.domain.Note;
import com.epam.evnote.domain.Notepad;
import com.epam.evnote.domain.User;
import com.epam.evnote.repository.MarkRepository;
import com.epam.evnote.repository.NotepadRepository;
import com.epam.evnote.repository.UserRepository;
import com.epam.evnote.service.impl.NoteService;
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
public class NoteServiceTest {


  @Autowired
  NoteService noteService;

  @Autowired
  NotepadRepository notepadRepository;

  @Autowired
  UserRepository userRepository;

  @Autowired
  MarkRepository markRepository;

  Notepad testNotepad;

  @Transactional
  @Before
  public void setUp() {
    User user = new User();
    user.setLogin("TestForNoteService");
    user.setPassword("999");
    User user1 = userRepository.saveAndFlush(user);

    Notepad notepad = new Notepad();
    notepad.setUser(user1);
    notepad.setTitle("NotepadForNotes");
    notepad.setUpdateTime(LocalDate.now());
    testNotepad = notepadRepository.saveAndFlush(notepad);

    Note note = new Note();
    note.setNotepad(testNotepad);
    note.setTitle("Testik");
    Note note1 = noteService.saveOrUpdate(note);

    Note note2 = new Note();
    note2.setNotepad(testNotepad);
    note.setTitle("Note2");
    Note note3 = noteService.saveOrUpdate(note2);

    Mark mark = new Mark();
    mark.setName("test");
    mark.getNotes().add(note1);
    mark.getNotes().add(note3);
    Mark mark1 = markRepository.saveAndFlush(mark);
  }

  @Test
  public void saveOrUpdate() throws Exception {
    Note note = new Note();
    note.setTitle("testNote");
    note.setNotepad(testNotepad);
    note.setCreationTime(LocalDate.now());
    note.setNoteBody("Some body here");

    noteService.saveOrUpdate(note);

    Note testNote = noteService.getByTitle(testNotepad,"testNote");
    assertNotNull(testNote);
    assertEquals(testNote.getNoteBody(), "Some body here");
  }

  @Transactional
  @Test
  public void getById() throws Exception {
    Note note = new Note();
    note.setTitle("testNote");
    note.setNotepad(testNotepad);
    note.setCreationTime(LocalDate.now());
    note.setNoteBody("Some body here");

    noteService.saveOrUpdate(note);
    Note testNote = noteService.getByTitle(testNotepad,"testNote");
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
    note.setCreationTime(LocalDate.now());
    note.setNoteBody("Some body here");

    noteService.saveOrUpdate(note);

    Note testNote = noteService.getByTitle(testNotepad,"testNote");
    assertEquals(testNote.getNoteBody(), "Some body here");

    noteService.delete(testNote.getId());
    Note deletedNote = noteService.getByTitle(testNotepad,"testNote");
    assertNull(deletedNote);
  }

  @Test
  @Transactional
  public void getAllNotesByMark(){
    List<Note> jobOnly1 = noteService.getByMarkTitle("test");
//    System.err.println(jobOnly1);
    for(Note n : jobOnly1) {
      System.err.println(n.getMarks());
    }
  }

}