package com.thiagocostafatec.orange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagocostafatec.orange.exception.ResourceNotFoundException;
import com.thiagocostafatec.orange.model.EnderecoModel;
import com.thiagocostafatec.orange.model.UsuarioModel;
import com.thiagocostafatec.orange.repository.EnderecoRepository;
import com.thiagocostafatec.orange.service.UsuarioService;

@RestController
@RequestMapping("/endereco")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class EnderecoController {

	@Autowired
	private EnderecoRepository EnderecoRepository;

	@Autowired
	private UsuarioService UsuarioService;

	// Nesse método é possível mostrar todos os endereços cadastrados, através do
	// EndPoint /endereco
	@GetMapping
	public ResponseEntity<List<EnderecoModel>> findAll() {
		List<EnderecoModel> result = EnderecoRepository.findAll();
		return ResponseEntity.ok(result);

	}

	// Nesse método ocorre o cadastramento do endereço do usuário através do
	// Endpoint /cadastrar
	@PostMapping("/cadastrar")
	public ResponseEntity<EnderecoModel> post(@RequestBody EnderecoModel Endereco) {

		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(EnderecoRepository.save(Endereco));
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

	// Nesse método ocorre a busca do endereço cadastrado relacionando ao ID do
	// user(usuário)
	@GetMapping("/idusuario/{id}")
	public ResponseEntity<UsuarioModel> getAllEnderecos(
			@PathVariable long id) throws ResourceNotFoundException {
		return ResponseEntity.ok(UsuarioService.EncontrarEndereco(id));
	}

}