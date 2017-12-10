package com.epam.evnote.domain;

import static org.junit.Assert.*;

import com.epam.evnote.config.ApplicationConfiguration;
import lombok.extern.java.Log;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Log
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@DirtiesContext(classMode = ClassMode.AFTER_CLASS)
public class MarkTest {

  @Test
  public void getId() throws Exception {
  }

  @Test
  public void getName() throws Exception {
  }

  @Test
  public void getNotes() throws Exception {
  }

  @Test
  public void setName() throws Exception {
  }

  @Test
  public void setNotes() throws Exception {
  }

}