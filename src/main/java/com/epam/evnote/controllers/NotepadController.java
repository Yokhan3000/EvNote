package com.epam.evnote.controllers;

import com.epam.evnote.domain.Notepad;
import com.epam.evnote.domain.User;
import com.epam.evnote.service.impl.NotepadService;
import com.epam.evnote.service.impl.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mikhail Chtetsov on 19/12/2017.
 */
@RestController
public class NotepadController {

  NotepadService notepadService;
  UserService userService;


  @Autowired
  public NotepadController(NotepadService notepadService,
      UserService userService) {
    this.notepadService = notepadService;
    this.userService = userService;
  }


  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/notepads")
  public List<Notepad> getAllNotepadsByUser() {
    UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String name = principal.getName();
    User user = userService.getByLogin(name);
    return notepadService.getAllByUser(user);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/notepad/{title}")
  public Notepad getAllNotepadsByTitle(@PathVariable("title") String title) {
    UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    String userName = principal.getName();
    User user = userService.getByLogin(userName);
    return notepadService.getByTitle(title, user);
  }
}
