package com.epam.evnote.controllers;

import com.epam.evnote.domain.Mark;
import com.epam.evnote.domain.User;
import com.epam.evnote.service.impl.MarkService;
import com.epam.evnote.service.impl.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mikhail Chtetsov on 21/12/2017.
 */
@RestController
public class MarkController {

  MarkService markService;
  UserService userService;

  @Autowired
  public MarkController(MarkService markService, UserService userService) {
    this.markService = markService;
    this.userService = userService;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/marks")
  public List<Mark> getAllMarksByUser() {
    UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
        .getPrincipal();
    User user = userService.getByLogin(principal.getName());
    markService.getAllByUser(user);
    return markService.getAll();
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping(value = "/marks")
  public void createOrUpdateMark(@RequestBody Mark mark) {
    markService.saveOrUpdate(mark);
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping(value = "/marks/{id}")
  public void deleteMark(@PathVariable("id") Long id) {
    markService.delete(id);
  }

}
