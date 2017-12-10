package com.epam.evnote.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@Entity
@Table
public class Note implements Serializable{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = Notepad.class)
  @JoinColumn(name = "notepad_id", nullable = false)
  private Notepad notepad;

  private String title;
  private String noteBody;
  private Timestamp creationTime;
  private Timestamp updateTime;

  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "notes_marks",
      joinColumns = @JoinColumn(
          name = "note_id",
          nullable = false),
      inverseJoinColumns = @JoinColumn(
          name = "mark_id",
          nullable = false))
  private List<Mark> marks = new ArrayList<>();
}
