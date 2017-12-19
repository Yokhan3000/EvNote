package com.epam.evnote.service.impl;

import com.epam.evnote.domain.Mark;
import com.epam.evnote.domain.Note;
import com.epam.evnote.domain.Notepad;
import com.epam.evnote.repository.MarkRepository;
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
  MarkRepository markRepository;

  @Autowired
  public NoteService(NoteRepository noteRepository, MarkRepository markRepository) {
    this.noteRepository = noteRepository;
    this.markRepository = markRepository;
  }

  @Override
  public Note saveOrUpdate(Note note) {

    Note saved = noteRepository.saveAndFlush(note);
    return saved;
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

  public Note getByTitle(Notepad notepad, String title) {

    return noteRepository.getByNotepadAndTitle(notepad, title);

  }

  public List<Note> getByMarkTitle(String markTitle) {
    Mark mark = markRepository.getByName(markTitle);
    return mark.getNotes();
  }
}
