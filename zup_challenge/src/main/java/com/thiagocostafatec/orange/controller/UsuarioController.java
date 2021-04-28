package com.thiagocostafatec.orange.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.thiagocostafatec.orange.model.UsuarioModel;
import com.thiagocostafatec.orange.repository.UsuarioRepository;

@RestController
@RequestMapping(value = "/user")
public class UsuarioController {

	@Autowired
	private UsuarioRepository UsuarioRepository;
	
	//Nesse método é possível mostrar todos os usuários cadastrados, 
	//através do EndPoint /user
	@GetMapping
	public ResponseEntity<List<UsuarioModel>> findAll() {
		List<UsuarioModel> result = UsuarioRepository.findAll();
		return ResponseEntity.ok(result);

	}
	
	/*
	 * paginação path (caminho) 
	 * /user/page 
	 * /user/page?page=1
	 * /user/page?page=0&size=15 
	 * /user/page?page=0&size=15&sort=user,asc
	 */
	//Esse método foi criado para que a visualização dos 
	//dados seja feita através de paginação. Caso seja uma API 
	//com um banco de dados volumoso,
	//o ideal é utilizar a paginação
	@GetMapping(value = "/page")
	public ResponseEntity<Page<UsuarioModel>> findAll(Pageable pageable) {
		Page<UsuarioModel> result = UsuarioRepository.findAll(pageable);
		return ResponseEntity.ok(result);
	}
	
	//Nesse método é possível cadastrar um usuário através do EndPoint /cadastrar
	//Formato JSON
	/* {
    "nome": "Fátima",
    "email": "fatima@gmail.com",
    "cpf": "54658489699",
    "dataNascimento": "30-04-1995"
  	}*/
	@PostMapping("/cadastrar")
	public ResponseEntity<UsuarioModel> post(@RequestBody UsuarioModel Usuario) {

		try {
			return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioRepository.save(Usuario));
		} catch (Exception exception) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		}
	}

}