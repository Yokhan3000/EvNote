package com.epam.evnote.controllers;

import com.epam.evnote.domain.Mark;
import com.epam.evnote.domain.Note;
import com.epam.evnote.domain.Notepad;
import com.epam.evnote.service.impl.MarkService;
import com.epam.evnote.service.impl.NoteService;
import com.epam.evnote.service.impl.NotepadService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mikhail Chtetsov on 21/12/2017.
 */
@RestController
public class NoteController {

  NoteService noteService;
  NotepadService notepadService;
  MarkService markService;

  @Autowired
  public NoteController(NoteService noteService,
      NotepadService notepadService, MarkService markService) {
    this.noteService = noteService;
    this.notepadService = notepadService;
    this.markService = markService;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/notepads/{id}/notes")
  public List<Note> getAllNotesByNotepad(@PathVariable("id") Long id) {
    return noteService.getAllByNotepad(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/notes/{id}")
  public Note getNoteById(@PathVariable("id") Long id) {
    return noteService.getById(id);
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/notes")
  public void createOrUpdateNote(@RequestBody Note note, @RequestParam Long id) {
    Notepad notepad = notepadService.getById(id);
    if (note.getNotepad() == null) {
      note.setNotepad(notepad);
    }
    noteService.saveOrUpdate(note);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = "/notes/{id}")
  public void deleteNote(@PathVariable("id") Long id) {
    noteService.delete(id);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/mark/{title}/notes")
  public List<Note> getNotesByMark(@PathVariable("title") String title) {
    Mark byName = markService.getByName(title);
    return byName.getNotes();
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = "/mark/note")
  public void addMarkToNote(@RequestParam Long markId,
      @RequestParam Long noteId) {
    Mark mark = markService.getById(markId);
    Note note = noteService.getById(noteId);
    mark.getNotes().add(note);
    note.getMarks().add(mark);
    markService.saveOrUpdate(mark);
    noteService.saveOrUpdate(note);
  }

  @ResponseStatus(HttpStatus.OK)
  @PostMapping(value = "/note/mark")
  public void deleteMarkFromNote(@RequestParam Long markId,
      @RequestParam Long noteId) {
    Mark mark = markService.getById(markId);
    Note note = noteService.getById(noteId);
    mark.getNotes().remove(note);
    note.getMarks().remove(mark);
    markService.saveOrUpdate(mark);
    noteService.saveOrUpdate(note);
  }

}
