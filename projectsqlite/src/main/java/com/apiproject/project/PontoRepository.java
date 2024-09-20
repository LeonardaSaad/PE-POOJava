package com.apiproject.project;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apiproject.model.Ponto;

public interface PontoRepository extends JpaRepository<Ponto, Integer> {
}