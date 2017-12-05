package com.epam.evnote.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "Note")
@Table(name = "notes")
public class Note {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
  @JoinColumn(name = "notepad_id", nullable = false)
  private Notepad notepad;

  private String title;
  private String noteBody;
  private LocalDateTime creationTime;
  private LocalDateTime updateTime;

  @ManyToMany(fetch = FetchType.LAZY,targetEntity = Mark.class)
  @JoinTable(name = "notes_marks",
      joinColumns = @JoinColumn(
          name = "note_id",
          referencedColumnName = "id",
          nullable = false),
      inverseJoinColumns = @JoinColumn(
          name = "mark_id",
          referencedColumnName = "id",
          nullable = false))
  private List<Mark> marks;
}
