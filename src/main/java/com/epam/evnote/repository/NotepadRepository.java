package com.epam.evnote.repository;

import com.epam.evnote.domain.Notepad;
import com.epam.evnote.domain.User;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Mikhail Chtetsov on 09/12/2017.
 */
@Repository
public interface NotepadRepository extends JpaRepository<Notepad, Long> {

  Notepad getByTitleAndUser(String title, User user);

  List<Notepad> getAllByUser(User user);

}
