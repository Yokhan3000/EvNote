package com.epam.evnote.initdb;

import com.epam.evnote.config.ApplicationConfiguration;
import com.epam.evnote.domain.Mark;
import com.epam.evnote.domain.Note;
import com.epam.evnote.domain.Notepad;
import com.epam.evnote.domain.User;
import com.epam.evnote.repository.MarkRepository;
import com.epam.evnote.repository.NoteRepository;
import com.epam.evnote.repository.NotepadRepository;
import com.epam.evnote.repository.UserRepository;
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.annotation.PostConstruct;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */

@Service
public class InitDB {

  @Autowired
  UserRepository userRepository;

  @Autowired
  NotepadRepository notepadRepository;

  @Autowired
  NoteRepository noteRepository;

  @Autowired
  MarkRepository markRepository;

  @PostConstruct
  public void init() {
    User user = new User();
    user.setLogin("Misha");
    user.setPassword("123");
    userRepository.saveAndFlush(user);

    Notepad notepad = new Notepad();
    notepad.setTitle("MyNotepad");
    notepad.setUpdateTime(LocalDate.now());
    notepad.setUser(user);
    notepadRepository.saveAndFlush(notepad);

    Note note = new Note();
    note.setCreationTime(Timestamp.valueOf(LocalDateTime.now()));
    note.setNotepad(notepad);
    note.setTitle("Work");
    noteRepository.saveAndFlush(note);


    Mark mark = new Mark();
    mark.setName("JobOnly");
    mark.getNotes().add(note);
    markRepository.saveAndFlush(mark);

//    while(true){}
  }
}
