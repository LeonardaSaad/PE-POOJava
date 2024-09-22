package com.apiproject.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import java.time.LocalDateTime;

@Entity
@Table(name = "registro_ponto")
public class Ponto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ponto_id")
	private Integer ponto_id;

	@ManyToOne
	@JoinColumn(name = "funcionario_id", referencedColumnName = "funcionario_id", nullable = true)
	private Funcionario funcionario_id;

	@Column(name = "entrada_ponto", nullable = true)
	private LocalDateTime entrada_ponto;

	@Column(name = "saida_ponto", nullable = true)
	private LocalDateTime saida_ponto;

	@PrePersist
	protected void onCreate() {
		entrada_ponto = LocalDateTime.now(); // Define a data/hora atual na entrada
	}

	@PreUpdate
	protected void onUpdate() {
		saida_ponto = LocalDateTime.now(); // Define a data/hora atual na saída ao atualizar
	}

	public Integer getPonto_id() {
		return ponto_id;
	}

	public void setPonto_id(Integer ponto_id) {
		this.ponto_id = ponto_id;
	}

	public Funcionario getFuncionario() {
		return funcionario_id;
	}

	public void setFuncionario(Funcionario funcionario_id) {
		this.funcionario_id = funcionario_id;
	}

	public LocalDateTime getEntrada_ponto() {
		return entrada_ponto;
	}

	public void setEntrada_ponto(LocalDateTime entrada_ponto) {
		this.entrada_ponto = entrada_ponto;
	}

	public LocalDateTime getSaida_ponto() {
		return saida_ponto;
	}

	public void setSaida_ponto(LocalDateTime saida_ponto) {
		this.saida_ponto = saida_ponto;
	}

	@Transient
	public String data_saida_converted;

	@Transient
	public String data_entrada_converted;

	public String getData_saida_converted() {
		return data_saida_converted;
	}

	public void setData_saida_converted(String data_saida_converted) {
		this.data_saida_converted = data_saida_converted;
	}

	public String getData_entrada_converted() {
		return data_entrada_converted;
	}

	public void setData_entrada_converted(String data_entrada_converted) {
		this.data_entrada_converted = data_entrada_converted;
	}

	public Funcionario getFuncionario_id() {
		return funcionario_id;
	}

	public void setFuncionario_id(Funcionario funcionario_id) {
		this.funcionario_id = funcionario_id;
	}
}