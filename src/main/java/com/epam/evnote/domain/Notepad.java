package com.epam.evnote.domain;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@NoArgsConstructor
@Data
@Entity
@Table
@ToString(exclude = "notes")
public class Notepad implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @ManyToOne(fetch = FetchType.LAZY, targetEntity = User.class)
  @JoinColumn(name = "user_id")
  private User user;
  private String title;

  private LocalDate updateTime;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "notepad")
  private List<Note> notes = new ArrayList<>();
}
