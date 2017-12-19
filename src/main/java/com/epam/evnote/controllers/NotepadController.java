package com.epam.evnote.controllers;

import com.epam.evnote.domain.Notepad;
import com.epam.evnote.service.impl.NotepadService;
import com.epam.evnote.service.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class NotepadController {

    @Autowired
    NotepadService notepadService;

    @Autowired
    UserService userService;

    @RequestMapping(value = "/notepad", method = RequestMethod.GET)
    public ResponseEntity<List<Notepad>> getUerNotepadList(){
        return new ResponseEntity<>(notepadService.getAllByUser(userService.getById(1L)), HttpStatus.OK);
    }

    @RequestMapping(value = "/notepad", method = RequestMethod.POST)
    public void addUserNotepad(@RequestBody Notepad notepad){

    }
}
