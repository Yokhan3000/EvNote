package com.epam.evnote.services;

import static org.junit.Assert.*;

import com.epam.evnote.config.ApplicationConfiguration;
import com.epam.evnote.config.WebConfig;
import com.epam.evnote.domain.Mark;
import com.epam.evnote.service.impl.MarkService;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfiguration.class)
@WebAppConfiguration
public class MarkServiceTest {

  @Autowired
  MarkService markService;

  @Test
  public void saveOrUpdate() throws Exception {
    Mark mark = new Mark();
    mark.setName("Most Important");
    markService.saveOrUpdate(mark);

    Mark most_important = markService.getByName("Most Important");
    assertNotNull(most_important);
    assertEquals(most_important.getName(), mark.getName());
  }

  @Transactional
  @Test
  public void getById() throws Exception {
    Mark mark = new Mark();
    mark.setName("Most Important");
    markService.saveOrUpdate(mark);
    Mark most_important = markService.getByName("Most Important");
    Mark byId = markService.getById(most_important.getId());
    assertNotNull(byId);
    assertEquals(byId.getName(), mark.getName());
  }

  @Test
  public void getAll() throws Exception {
    List<Mark> all = markService.getAll();
    assertNotNull(all);
  }

  @Test
  public void delete() throws Exception {
    Mark mark = new Mark();
    mark.setName("Most Important");
    markService.saveOrUpdate(mark);
    Mark most_important = markService.getByName("Most Important");
    markService.delete(most_important.getId());
    assertNull(markService.getByName("Most Important"));
  }

}