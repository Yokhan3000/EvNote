package com.epam.evnote.service.impl;

import com.epam.evnote.domain.Notepad;
import com.epam.evnote.domain.User;
import com.epam.evnote.repository.NotepadRepository;
import com.epam.evnote.service.CommonService;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Service
public class NotepadService implements CommonService<Notepad, Long> {

  NotepadRepository notepadRepository;
  UserDetailsService userDetailsService;

  @Autowired
  public NotepadService(NotepadRepository notepadRepository,
      UserDetailsService userDetailsService) {
    this.notepadRepository = notepadRepository;
    this.userDetailsService = userDetailsService;
  }

  public Notepad createNotepad(String title, User user) {
    Notepad byTitle = getByTitle(title, user);
    if(byTitle == null) {
      byTitle = new Notepad();
      byTitle.setTitle(title);
      byTitle.setUser(user);
      byTitle.setUpdateTime(LocalDate.now());
    }
    return saveOrUpdate(byTitle);
  }

  @Override
  public Notepad saveOrUpdate(Notepad notepad) {

    Notepad saved = notepadRepository.saveAndFlush(notepad);
    return saved;
  }

  @Override
  public Notepad getById(Long id) {
    Optional<Notepad> byId = notepadRepository.findById(id);
    return byId.get();
  }

  @Override
  public List<Notepad> getAll() {
    return notepadRepository.findAll();
  }



  @Override
  public void delete(Long id) {
    notepadRepository.deleteById(id);
  }

  public Notepad getByTitle(String title, User user) {
    return notepadRepository.getByTitleAndUser(title, user);
  }

  public List<Notepad> getAllByUser(User user) {
    return notepadRepository.getAllByUser(user);
  }

}
