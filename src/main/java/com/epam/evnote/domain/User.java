package com.epam.evnote.domain;

import com.sun.tools.corba.se.idl.constExpr.Not;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity(name = "User")
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue
  private Long id;

  private String login;
  private String password;

  private String name;

  @OneToMany(fetch = FetchType.EAGER, mappedBy = "user", targetEntity = Notepad.class)
  private List<Notepad> notepads = new ArrayList<>();
}
