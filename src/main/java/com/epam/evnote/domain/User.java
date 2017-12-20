package com.epam.evnote.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@Data
@Entity
@Table
@ToString(exclude = "notepads")
//@JsonIgnoreProperties(value = "notepads")
public class User implements Serializable {


  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String login;
  private String password;
  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user",
      cascade = CascadeType.ALL)
  private List<Notepad> notepads = new ArrayList<>();
  @JsonIgnore
  @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
  private List<Mark> marks = new ArrayList<>();
}



