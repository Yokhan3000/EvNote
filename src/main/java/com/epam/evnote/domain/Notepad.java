package com.epam.evnote.domain;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "Notepad")
@Table(name = "notepads")
public class Notepad {

  @Id
  @GeneratedValue
  private Long id;

  @ManyToOne(fetch = FetchType.EAGER, targetEntity = User.class)
  @JoinColumn(name = "user_id", nullable = false)
  private User user;

  private LocalDateTime update;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "notepad")
  private List<Note> notes;
}
