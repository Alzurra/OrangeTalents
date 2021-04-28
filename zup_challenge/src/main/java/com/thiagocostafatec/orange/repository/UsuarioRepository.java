package com.thiagocostafatec.orange.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thiagocostafatec.orange.model.UsuarioModel;

@Repository
public interface UsuarioRepository extends JpaRepository<UsuarioModel, Long> {

	public Optional<UsuarioModel> findByCpf(String cpf);

	public Optional<UsuarioModel> findByEmail(String email);
}
