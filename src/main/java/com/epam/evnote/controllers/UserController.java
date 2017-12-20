package com.epam.evnote.controllers;

import com.epam.evnote.domain.User;
import com.epam.evnote.exceptions.UserException;
import com.epam.evnote.service.impl.UserService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * @author Mikhail Chtetsov on 12/12/2017.
 */
@RestController
@SessionAttributes("user")
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

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping
  public User createPerson(@RequestBody User user,
      HttpServletResponse response) {
    User saved = userService.saveOrUpdate(user);
    String url = "/users/" + saved.getId();
    response.setHeader("Location", url);
    return saved;
  }

  @GetMapping("/users/{id}")
  public User findById(@PathVariable Long id) {
    return userService.getById(id);
  }
}
