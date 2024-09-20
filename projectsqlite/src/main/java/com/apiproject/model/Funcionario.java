package com.apiproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "funcionarios")
public class Funcionario{
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "funcionario_id")
  private Integer funcionario_id;

  @Column(name = "name", length = 200, nullable = true)
  private String name;


  public Integer getFuncionario_id() {
    return funcionario_id;
  }

  public void setFuncionario_id(Integer funcionario_id) {
    this.funcionario_id = funcionario_id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
