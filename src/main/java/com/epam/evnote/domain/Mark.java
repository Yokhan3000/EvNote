package com.epam.evnote.domain;

import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "Note")
@Table(name = "notes")
public class Mark {

  @Id
  @GeneratedValue
  private Long id;

  private String name;

  @ManyToMany(fetch = FetchType.LAZY,targetEntity = Note.class)
  @JoinTable(name = "notes_marks",
      joinColumns = @JoinColumn(
          name = "mark_id",
          referencedColumnName = "id",
          nullable = false),
      inverseJoinColumns = @JoinColumn(
          name = "note_id",
          referencedColumnName = "id",
          nullable = false))
  private List<Note> notes;
}
