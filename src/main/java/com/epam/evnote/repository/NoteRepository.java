package com.epam.evnote.repository;

import com.epam.evnote.domain.Mark;
import com.epam.evnote.domain.Note;
import com.epam.evnote.domain.Notepad;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * @author Mikhail Chtetsov on 09/12/2017.
 */
@Repository
public interface NoteRepository extends JpaRepository<Note, Long> {

  Note getByTitle(String title);

  List<Note> getAllByNotepad(Notepad notepad);

  Note getByNotepadAndTitle(Notepad notepad, String title);


}
