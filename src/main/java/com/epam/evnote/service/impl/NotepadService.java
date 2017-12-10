package com.epam.evnote.service.impl;

import com.epam.evnote.domain.Notepad;
import com.epam.evnote.domain.User;
import com.epam.evnote.repository.NotepadRepository;
import com.epam.evnote.service.CommonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Service
public class NotepadService implements CommonService<Notepad, Long> {

  NotepadRepository notepadRepository;

  @Autowired
  public NotepadService(NotepadRepository notepadRepository) {
    this.notepadRepository = notepadRepository;
  }

  public void createNotepad(String title, User user) {
    Notepad notepad = new Notepad();
    notepad.setTitle(title);
    notepad.setUser(user);
    saveOrUpdate(notepad);
  }

  @Override
  public void saveOrUpdate(Notepad notepad) {
    notepadRepository.saveAndFlush(notepad);
  }

  @Override
  public Notepad getById(Long id) {
    return notepadRepository.getOne(id);
  }

  @Override
  public List<Notepad> getAll() {
    return notepadRepository.findAll();
  }

  @Override
  public void delete(Notepad notepad) {
    notepadRepository.delete(notepad);
  }

  public Notepad getByTitle(String title) {
    return notepadRepository.getByTitle(title);
  }

}
