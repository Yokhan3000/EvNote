package com.epam.evnote.controllers;

import com.epam.evnote.domain.Notepad;
import com.epam.evnote.domain.User;
import com.epam.evnote.service.impl.NotepadService;
import com.epam.evnote.service.impl.UserService;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

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
  public List<Notepad> getAllNotepadsByUser(HttpSession httpSession){
    User user =(User) httpSession.getAttribute("user");
    System.err.println("00000000000" + user);
    notepadService.getAllByUser(user);
    return notepadService.getAllByUser(user);

  }
}
