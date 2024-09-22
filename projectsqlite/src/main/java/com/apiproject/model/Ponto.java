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
	private Integer pontoId;

	@ManyToOne
	@JoinColumn(name = "funcionario_id", referencedColumnName = "funcionario_id", nullable = true)
	private Funcionario funcionario; // Renomeado para 'funcionario'

	@Column(name = "entrada_ponto", nullable = true)
	private LocalDateTime entradaPonto; // Renomeado para 'entradaPonto'

	@Column(name = "saida_ponto", nullable = true)
	private LocalDateTime saidaPonto; // Renomeado para 'saidaPonto'

	@Transient
	private String dataEntradaConverted; // Renomeado para 'dataEntradaConverted'

	@Transient
	private String dataSaidaConverted; // Renomeado para 'dataSaidaConverted'

	@PrePersist
	protected void onCreate() {
		entradaPonto = LocalDateTime.now(); // Define a data/hora atual na entrada
	}

	@PreUpdate
	protected void onUpdate() {
		saidaPonto = LocalDateTime.now(); // Define a data/hora atual na saída ao atualizar
	}

	// Getters e Setters
	public Integer getPontoId() {
		return pontoId;
	}

	public void setPontoId(Integer pontoId) {
		this.pontoId = pontoId;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public LocalDateTime getEntradaPonto() {
		return entradaPonto;
	}

	public void setEntradaPonto(LocalDateTime entradaPonto) {
		this.entradaPonto = entradaPonto;
	}

	public LocalDateTime getSaidaPonto() {
		return saidaPonto;
	}

	public void setSaidaPonto(LocalDateTime saidaPonto) {
		this.saidaPonto = saidaPonto;
	}

	public String getDataEntradaConverted() {
		return dataEntradaConverted;
	}

	public void setDataEntradaConverted(String dataEntradaConverted) {
		this.dataEntradaConverted = dataEntradaConverted;
	}

	public String getDataSaidaConverted() {
		return dataSaidaConverted;
	}

	public void setDataSaidaConverted(String dataSaidaConverted) {
		this.dataSaidaConverted = dataSaidaConverted;
	}
}
