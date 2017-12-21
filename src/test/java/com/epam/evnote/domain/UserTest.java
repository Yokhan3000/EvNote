package com.epam.evnote.domain;

import static org.junit.Assert.*;

import com.epam.evnote.config.ApplicationConfiguration;
import java.util.Arrays;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class UserTest {

  @Test
  public void equals() throws Exception {
    User first = new User();
    first.setLogin("first");
    User second = new User();
    second.setLogin("Second");
    assertNotEquals(first, second);
  }

  @Test
  public void hashCodeTest() throws Exception {
    User first = new User();
    first.setLogin("first");
    User second = new User();
    second.setLogin("first");
    assertEquals(first.hashCode(), second.hashCode());
  }

  @Test
  public void getId() throws Exception {
    User user = new User();
    user.setId(100L);
    assertTrue(user.getId() > 0);
  }

  @Test
  public void getLogin() throws Exception {
    User user = new User();
    user.setLogin("Login");
    assertEquals(user.getLogin(), "Login");
  }

  @Test
  public void getPassword() throws Exception {
    User user = new User();
    user.setPassword("123");
    assertEquals(user.getPassword(), "123");
  }

  @Test
  public void getNotepads() throws Exception {
    User user = new User();
    user.setNotepads(Arrays.asList(new Notepad()));
    assertNotNull(user.getNotepads());
  }

  @Test
  public void setLogin() throws Exception {
    User user = new User();
    user.setLogin("Login");
    assertEquals(user.getLogin(), "Login");
  }

  @Test
  public void setPassword() throws Exception {
    User user = new User();
    user.setPassword("123");
    assertEquals(user.getPassword(), "123");
  }

}