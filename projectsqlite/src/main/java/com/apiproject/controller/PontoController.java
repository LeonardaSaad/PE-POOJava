package com.apiproject.controller;

import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiproject.model.Funcionario;
import com.apiproject.model.Ponto;
import com.apiproject.project.FuncionarioRepository;
import com.apiproject.project.PontoRepository;

@RestController
@CrossOrigin("*")
@RequestMapping("/registro_ponto")
public class PontoController {

	@Autowired
	private PontoRepository pontoRepository;

	@Autowired
	private FuncionarioRepository funcionarioRepository; // Para buscar o funcionário

	@GetMapping
	public List<Ponto> getAllPontos() {
		List<Ponto> pontoList = pontoRepository.findAll();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		for (Ponto ponto : pontoList) {
			if (ponto.getEntrada_ponto() != null) {
				ponto.setData_entrada_converted(ponto.getEntrada_ponto().format(formatter));
			}

			if (ponto.getSaida_ponto() != null) {
				ponto.setData_saida_converted(ponto.getSaida_ponto().format(formatter));
			}
		}

		return pontoList;
	}

	@PostMapping
	public Ponto createPonto(@RequestBody Map<String, Object> request) {
		String funcionarioId = (String) request.get("funcionario_id"); // Mude para String
		String tipo = (String) request.get("tipo");

		// Busca o funcionário pelo ID
		Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
				.orElseThrow(() -> new RuntimeException("Funcionário não encontrado"));

		// Cria uma nova instância de Ponto
		Ponto ponto = new Ponto();
		ponto.setFuncionario(funcionario);

		// Define a entrada ou saída de acordo com o tipo
		if ("entrada".equals(tipo)) {
			ponto.setEntrada_ponto(LocalDateTime.now()); // Define a entrada
		}

		if ("saida".equals(tipo)) {
			ponto.setSaida_ponto(LocalDateTime.now()); // Define a saída
		}

		return pontoRepository.save(ponto);
	}

	@PutMapping
	public Ponto editPonto(@RequestBody Ponto ponto) {
		return pontoRepository.save(ponto);
	}
}
