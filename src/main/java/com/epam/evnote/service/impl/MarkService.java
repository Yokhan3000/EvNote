package com.epam.evnote.service.impl;

import com.epam.evnote.domain.Mark;
import com.epam.evnote.domain.User;
import com.epam.evnote.repository.MarkRepository;
import com.epam.evnote.service.CommonService;
import java.util.List;
import java.util.Optional;
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
  public Mark saveOrUpdate(Mark mark) {

    Mark saved = markRepository.saveAndFlush(mark);
    return saved;
  }


  @Override
  public Mark getById(Long id) {
    Optional<Mark> byId = markRepository.findById(id);
    return byId.get();
  }

  @Override
  public List<Mark> getAll() {
    return markRepository.findAll();
  }

  @Override
  public void delete(Long id) {
    markRepository.deleteById(id);
  }

  public Mark getByName(String name) {
    return markRepository.getByName(name);
  }

  public Mark getAllByUser(User user) {
    return markRepository.getByUser(user);
  }

}
