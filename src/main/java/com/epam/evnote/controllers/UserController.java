package com.epam.evnote.controllers;

import com.epam.evnote.domain.User;
import com.epam.evnote.exceptions.UserException;
import com.epam.evnote.service.impl.UserService;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

  @RequestMapping("/info")
  public @ResponseBody String userInfo(Authentication authentication) {
    String msg = "";
    for (GrantedAuthority authority : authentication.getAuthorities()) {
      String role = authority.getAuthority();
      msg+=authentication.getName()+", You have "+ role;
    }
    return msg;
  }
}
