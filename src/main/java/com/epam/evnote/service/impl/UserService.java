package com.epam.evnote.service.impl;

import com.epam.evnote.domain.User;
import com.epam.evnote.exceptions.UserException;
import com.epam.evnote.repository.UserRepository;
import com.epam.evnote.service.CommonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Service
public class UserService implements CommonService<User, Long> {

  UserRepository userRepository;

  @Autowired
  public UserService(UserRepository userRepository) {
    this.userRepository = userRepository;
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
    if(user.getId() != null) {
      User byLogin = userRepository.getByLogin(user.getLogin());
      if(!user.getId().equals(byLogin.getId())) {
        throw new UserException("Login already exists!" + byLogin.getLogin());
      }
    }
    User saved = userRepository.saveAndFlush(user);
    return saved;
  }

  @Override
  public User getById(Long id) {
    return userRepository.getOne(id);
  }

  @Override
  public List<User> getAll() {
    return userRepository.findAll();
  }

  @Override
  public void delete(User user) {
    userRepository.delete(user);
  }

  public User getByLogin(String login) {
    return userRepository.getByLogin(login);
  }

}
