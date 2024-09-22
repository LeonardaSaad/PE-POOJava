package com.apiproject.controller;

import java.util.List;
import java.util.Map;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
import com.apiproject.response.ResponseMessage;

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
			if (ponto.getEntradaPonto() != null) {
				ponto.setDataEntradaConverted(ponto.getEntradaPonto().format(formatter));
			}

			if (ponto.getSaidaPonto() != null) {
				ponto.setDataSaidaConverted(ponto.getSaidaPonto().format(formatter));
			}
		}

		return pontoList;
	}

	@PostMapping
	public ResponseEntity<ResponseMessage> createPonto(@RequestBody Map<String, Object> request) {
		String funcionarioId = (String) request.get("funcionario_id");

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		// Busca o funcionário pelo ID
		Funcionario funcionario = funcionarioRepository.findById(funcionarioId)
				.orElse(null);

		if (funcionario == null) {
			// Se o funcionário não for encontrado, retorna um status 404 com mensagem
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage(false, "Funcionário não encontrado", null, null),
					HttpStatus.NOT_FOUND);
		}

		// Verifica se existe um ponto sem saída registrado para o funcionário
		Ponto pontoExistente = pontoRepository.findByFuncionarioAndSaidaPontoIsNull(funcionario);

		if (pontoExistente != null) {
			// Se existe um ponto sem saída, atualiza com a data e hora de saída
			pontoExistente.setSaidaPonto(LocalDateTime.now());
			pontoRepository.save(pontoExistente);
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage(true, "Saída registrada com sucesso", funcionario.getFuncionario_id(), 
					LocalDateTime.now().format(formatter)),
					HttpStatus.OK);
		} else {
			// Se não existe um ponto em aberto, cria um novo registro de entrada
			Ponto novoPonto = new Ponto();
			novoPonto.setFuncionario(funcionario);
			novoPonto.setEntradaPonto(LocalDateTime.now());
			pontoRepository.save(novoPonto);
			return new ResponseEntity<ResponseMessage>(
					new ResponseMessage(true, "Entrada registrada com sucesso", funcionario.getFuncionario_id(), 
					LocalDateTime.now().format(formatter)),
					HttpStatus.CREATED);
		}
	}

	@PutMapping
	public Ponto editPonto(@RequestBody Ponto ponto) {
		return pontoRepository.save(ponto);
	}
}
