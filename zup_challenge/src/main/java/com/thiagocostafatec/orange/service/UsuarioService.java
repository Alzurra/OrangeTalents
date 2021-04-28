package com.thiagocostafatec.orange.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thiagocostafatec.orange.exception.ResourceNotFoundException;
import com.thiagocostafatec.orange.model.UsuarioModel;
import com.thiagocostafatec.orange.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository UsuarioRepository;

	public Optional<UsuarioModel> cadastrarUsuario(UsuarioModel Usuario) {

		if (UsuarioRepository.findByCpf(Usuario.getCpf()).isPresent()
				&& UsuarioRepository.findByEmail(Usuario.getEmail()).isPresent()) {

			return null;
		}

		return Optional.of(UsuarioRepository.save(Usuario));
	}

	public UsuarioModel EncontrarEndereco(long id) throws ResourceNotFoundException {
		Optional<UsuarioModel> Usuario = UsuarioRepository.findById(id);
		if (Usuario.isPresent() == false) {
			throw new ResourceNotFoundException(
			"Usuário " + id + " não encontrado!"
			);
		}

		Usuario.get().getEndereco();
		UsuarioRepository.save(Usuario.get());

		return UsuarioRepository.save(Usuario.get());
	}
}
