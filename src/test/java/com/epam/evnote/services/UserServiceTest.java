package com.epam.evnote.services;

import static org.junit.Assert.*;

import com.epam.evnote.config.ApplicationConfiguration;
import com.epam.evnote.domain.User;
import com.epam.evnote.exceptions.UserException;
import com.epam.evnote.repository.UserRepository;
import com.epam.evnote.service.impl.UserService;
import java.util.List;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class UserServiceTest {

  @Autowired
  UserService userService;
  @Autowired
  UserRepository userRepository;

  @Test
  public void createUser() throws Exception {
    User mike = userService.createUser("Mike", "321");
    assertNotNull(mike);
  }
  @Transactional
  @Test
  public void saveOrUpdate() throws Exception {
    User dima = userService.createUser("Dima", "222");
    dima.setLogin("Igor");
    userService.saveOrUpdate(dima);
    User igor = userRepository.getByLogin("Igor");
    log.info(igor.getPassword());
    assertNotNull("Igor");
  }

  @Transactional
  @Test
  public void getById() throws Exception {
    userService.createUser("Bob", "123");
    User bob = userRepository.getByLogin("Bob");
    User user = userService.getById(bob.getId());
    assertNotNull(user);
  }

  @Test
  public void getAll() throws Exception {
    List<User> all = userService.getAll();
    assertNotNull(all);
  }

  @Test
  public void delete() throws Exception {
    User user = userService.createUser("John", "111");
    userService.delete(user);
    User john = userService.createUser("John", "222");
    assertNotNull(john);
  }


  @Test(expected = UserException.class)
  public void userCreateExistedTest() {
    userService.createUser("John", "111");
    userService.createUser("John", "222");
  }

}
