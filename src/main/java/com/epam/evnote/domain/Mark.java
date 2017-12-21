package com.epam.evnote.domain;

import java.io.Serializable;
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
import lombok.ToString;


@NoArgsConstructor
@Data
@Entity
@Table
public class Mark implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  private Long id;

  private String name;


  @ManyToMany(fetch = FetchType.EAGER)
  @JoinTable(name = "notes_marks",
      joinColumns = @JoinColumn(
          name = "noteId",
          referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(
          name = "markId",
          referencedColumnName = "id"))
  private List<Note> notes = new ArrayList<>();

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
  @JoinColumn(name = "user_id")
  private User user;
}
