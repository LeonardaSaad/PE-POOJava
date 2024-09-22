package com.apiproject.project;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiproject.model.Ponto;
import com.apiproject.model.Funcionario;

public interface PontoRepository extends JpaRepository<Ponto, Integer> {
  Ponto findByFuncionarioAndSaidaPontoIsNull(Funcionario funcionario);
}