package com.apiproject.project;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiproject.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {
}