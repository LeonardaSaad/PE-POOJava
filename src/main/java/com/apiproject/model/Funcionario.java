package com.apiproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "funcionarios")
public class Funcionario {

	@Id
	@Column(name = "funcionario_id", length = 4)
	private String funcionario_id;

	@Column(name = "name", length = 200, nullable = true)
	private String name;

	// Gera um ID alfanumérico de 4 caracteres antes de persistir a entidade
	@PrePersist
	public void generateFuncionarioId() {
		this.funcionario_id = generateRandomId();
	}

	private String generateRandomId() {
		return UUID.randomUUID().toString().substring(0, 4);
	}

	// Getters e Setters
	public String getFuncionario_id() {
		return funcionario_id;
	}

	public void setFuncionario_id(String funcionario_id) {
		this.funcionario_id = funcionario_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
