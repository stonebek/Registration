package org.example.registraion.form.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table
public class User {

  @Id
  @SequenceGenerator(name = "main_seq")
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "main_seq")
  private Integer id;

  @Column(name = "login", nullable = false)
  private String login;

  @Column(name = "password", nullable = false)
  private String password;

  @Column(name = "email", nullable = false)
  private String email;

  @Column(name = "full_name")
  private String fullName;
}
