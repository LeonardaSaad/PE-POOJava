package com.apiproject.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.apiproject.model.Funcionario;
import com.apiproject.project.FuncionarioRepository;
import com.apiproject.response.ResponseMessage;

@RestController
@CrossOrigin("*")
@RequestMapping("/funcionarios")
public class FuncionarioController {

	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@GetMapping
	public List<Funcionario> getAllFuncionarios() {
		return funcionarioRepository.findAll();
	}

	@PostMapping
	public ResponseEntity<ResponseMessage> createFuncionario(@RequestBody Funcionario funcionario) {
		Optional<Funcionario> existingFuncionario = funcionarioRepository.findByName(funcionario.getName());

		if (existingFuncionario.isPresent()) {
			// Retorna status 400 com mensagem de erro
			ResponseMessage response = new ResponseMessage(false, "Nome já existente", null, null);
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		}

		Funcionario savedFuncionario = funcionarioRepository.save(funcionario);
		// Retorna status 201 com mensagem de sucesso
		ResponseMessage response = new ResponseMessage(true, "Funcionário criado com sucesso", savedFuncionario.getFuncionario_id(), null);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}

	@PutMapping
	public ResponseEntity<ResponseMessage> editFuncionario(@RequestBody Funcionario funcionario) {
		if (funcionarioRepository.existsById(funcionario.getFuncionario_id())) {
			funcionarioRepository.save(funcionario);
			// Retorna status 200 com mensagem de sucesso
			return new ResponseEntity<ResponseMessage>(
				new ResponseMessage(true, "Funcionário atualizado com sucesso", null, null),
				HttpStatus.OK);
		} else {
			// Retorna status 404 com mensagem de erro
			return new ResponseEntity<ResponseMessage>(
				new ResponseMessage(false, "Funcionário não encontrado", null, null),
				HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseMessage> deleteFuncionario(@PathVariable String id) {
		Optional<Funcionario> funcionario = funcionarioRepository.findById(id);

		if (funcionario.isPresent()) {
			funcionarioRepository.deleteById(id);
			// Retorna status 200 com mensagem de sucesso
			ResponseMessage response = new ResponseMessage(true, "Funcionário deletado com sucesso", null, null);
			return new ResponseEntity<>(response, HttpStatus.OK);
		} else {
			// Retorna status 404 com mensagem de erro
			ResponseMessage response = new ResponseMessage(false, "Funcionário não encontrado", null, null);
			return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
		}
	}
}
