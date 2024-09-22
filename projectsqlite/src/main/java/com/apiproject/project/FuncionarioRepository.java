package com.apiproject.project;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiproject.model.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, String> {
	Optional<Funcionario> findByName(String name);
}
