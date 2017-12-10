package com.epam.evnote.service;

import java.util.List;
import org.springframework.stereotype.Service;

/**
 * @author Mikhail Chtetsov on 10/12/2017.
 */
@Service
public interface CommonService<E, I> {

  void saveOrUpdate(E e);

  E getById(I id);

  List<E> getAll();

  void delete(E e);
}
