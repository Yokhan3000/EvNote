package com.epam.evnote.service.impl;

import com.epam.evnote.domain.Mark;
import com.epam.evnote.domain.Note;
import com.epam.evnote.domain.Notepad;
import com.epam.evnote.domain.User;
import com.epam.evnote.repository.MarkRepository;
import com.epam.evnote.repository.NoteRepository;
import com.epam.evnote.repository.NotepadRepository;
import com.epam.evnote.service.CommonService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Service
public class NoteService implements CommonService<Note, Long> {

  NoteRepository noteRepository;
  MarkRepository markRepository;
  NotepadRepository notepadRepository;

  @Autowired
  public NoteService(NoteRepository noteRepository,
      MarkRepository markRepository, NotepadRepository notepadRepository) {
    this.noteRepository = noteRepository;
    this.markRepository = markRepository;
    this.notepadRepository = notepadRepository;
  }

  @Override
  public Note saveOrUpdate(Note note) {

    Note saved = noteRepository.saveAndFlush(note);
    return saved;
  }


  @Override
  public Note getById(Long id) {
    Optional<Note> byId = noteRepository.findById(id);
    return byId.get();
  }

  @Override
  public List<Note> getAll() {
    return noteRepository.findAll();
  }


  public void delete(Long id) {
    noteRepository.deleteById(id);
  }

  public Note getByTitle(Notepad notepad, String title) {

    return noteRepository.getByNotepadAndTitle(notepad, title);

  }

  public List<Note> getByMarkTitle(String markTitle) {
    Mark mark = markRepository.getByName(markTitle);
    return mark.getNotes();
  }

  public List<Note> getAllByNotepad(Long id) {
    Notepad notepad = notepadRepository.getOne(id);
    return noteRepository.getAllByNotepad(notepad);
  }
}
