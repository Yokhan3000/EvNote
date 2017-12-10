package com.epam.evnote.service.impl;

import com.epam.evnote.domain.Note;
import com.epam.evnote.repository.NoteRepository;
import com.epam.evnote.service.CommonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Service
public class NoteService implements CommonService<Note, Long> {

  NoteRepository noteRepository;

  @Autowired
  public NoteService(NoteRepository noteRepository) {
    this.noteRepository = noteRepository;
  }

  @Override
  public void saveOrUpdate(Note note) {
    noteRepository.saveAndFlush(note);
  }

  @Override
  public Note getById(Long id) {
    return noteRepository.getOne(id);
  }

  @Override
  public List<Note> getAll() {
    return noteRepository.findAll();
  }

  @Override
  public void delete(Note note) {
    noteRepository.delete(note);
  }

  public Note getByTitle(String title) {
    return noteRepository.getByTitle(title);
  }
}
