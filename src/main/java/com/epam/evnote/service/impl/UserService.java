package com.epam.evnote.service.impl;

import com.epam.evnote.domain.User;
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

  @Override
  public void saveOrUpdate(User user) {
    userRepository.saveAndFlush(user);
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
}
