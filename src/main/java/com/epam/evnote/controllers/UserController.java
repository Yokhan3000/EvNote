package com.epam.evnote.controllers;

import com.epam.evnote.domain.User;
import com.epam.evnote.exceptions.UserException;
import com.epam.evnote.service.impl.UserService;
import java.util.List;
import java.util.NoSuchElementException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * @author Mikhail Chtetsov on 12/12/2017.
 */
@RestController
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
  @PostMapping(value = "/create")
  public User createPerson(@RequestBody User user,
      HttpServletResponse response) {
    User saved = userService.saveOrUpdate(user);
    String url = "/users/" + saved.getId();
    response.setHeader("Location", url);
    return saved;
  }

  @GetMapping("/users/{id}")
  public User findById(@PathVariable("id") Long id) {
    return userService.getById(id);
  }

  @RequestMapping("/info")
  public String userInfo(Authentication authentication) {
    String msg = "";
    for (GrantedAuthority authority : authentication.getAuthorities()) {
      String role = authority.getAuthority();
      msg += authentication.getName() + ", You have " + role;
    }
    return msg;
  }
}
