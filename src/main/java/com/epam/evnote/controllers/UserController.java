package com.epam.evnote.controllers;

import com.epam.evnote.domain.User;
import com.epam.evnote.exceptions.UserException;
import com.epam.evnote.service.impl.UserService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Mikhail Chtetsov on 12/12/2017.
 */
@RestController
@RequestMapping("/")
public class UserController {

  private UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/users")
  public List<User> getAllUsers() {
    return userService.getAll();
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping(value = "/users/{$login}")
  public User getByLogin(@PathVariable("$login") String login) {
    User user = userService.getByLogin(login);
    if (user == null) {
      throw new UserException("No such user " + login);
    }
    return user;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public void createPerson(@RequestBody User user, HttpServletResponse response) {
    User saved = userService.saveOrUpdate(user);
    String url = "/users/" + saved.getId();
    response.setHeader("Location", url);
  }

  @GetMapping("/users/{id}")
  public User findById(@PathVariable Long id) {
    return userService.getById(id);
  }
}
