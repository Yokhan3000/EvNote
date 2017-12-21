package com.epam.evnote.service.impl;

import com.epam.evnote.domain.User;
import com.epam.evnote.exceptions.UserException;
import com.epam.evnote.repository.UserRepository;
import com.epam.evnote.service.CommonService;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Service
public class UserService implements CommonService<User, Long> {

  UserRepository userRepository;

  HttpSession httpSession;

  @Autowired
  public UserService(UserRepository userRepository, HttpSession httpSession) {
    this.userRepository = userRepository;
    this.httpSession = httpSession;
  }

  public User createUser(String login, String password) {
    User byLogin = userRepository.getByLogin(login);
    if (byLogin != null) {
      throw new UserException("Login already exists!" + byLogin.getLogin());
    }
    User user = new User();
    user.setLogin(login);
    user.setPassword(password);
    User userFromDb = userRepository.saveAndFlush(user);
    return userFromDb;
  }

  @Override
  public User saveOrUpdate(User user) {
    User saved = userRepository.save(user);
    httpSession.setAttribute("user", saved);
    return saved;
  }

  public User getUserFromSession(){
    return (User) httpSession.getAttribute("user");
  }

  @Override
  public User getById(Long id) {
    Optional<User> byId = userRepository.findById(id);
    return byId.get();
  }

  @Override
  @Transactional
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public void delete(Long id) {
    userRepository.deleteById(id);
  }

  public User getByLogin(String login) throws NoSuchElementException {
    return userRepository.getByLogin(login);
  }

}
