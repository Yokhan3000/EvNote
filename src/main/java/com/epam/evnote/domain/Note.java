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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@Data
@Entity
@Table
public class Note implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = Notepad.class)
  @JoinColumn(name = "notepad_id", nullable = false)
  private Notepad notepad;

  private String title;
  private String noteBody;
  private Timestamp creationTime;
  private Timestamp updateTime;

  @ManyToMany(fetch = FetchType.LAZY)
  @JoinTable(name = "notes_marks",
      joinColumns = @JoinColumn(
          name = "note_id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "mark_id", referencedColumnName = "id"))
  private List<Mark> marks = new ArrayList<>();
}
