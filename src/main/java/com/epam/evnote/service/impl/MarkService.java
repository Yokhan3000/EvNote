package com.epam.evnote.service.impl;

import com.epam.evnote.domain.Mark;
import com.epam.evnote.repository.MarkRepository;
import com.epam.evnote.service.CommonService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Service
public class MarkService implements CommonService<Mark, Long> {

  MarkRepository markRepository;

  @Autowired
  public MarkService(MarkRepository markRepository) {
    this.markRepository = markRepository;
  }

  @Override
  public void saveOrUpdate(Mark mark) {
    markRepository.saveAndFlush(mark);
  }

  @Override
  public Mark getById(Long id) {
    return markRepository.getOne(id);
  }

  @Override
  public List<Mark> getAll() {
    return markRepository.findAll();
  }

  @Override
  public void delete(Mark mark) {
    markRepository.delete(mark);
  }

  public Mark getByName(String name) {
    return markRepository.getByName(name);
  }
}
