package com.somapay.contaBancaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somapay.contaBancaria.model.Usuario;
import com.somapay.contaBancaria.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario criarUsuario(Usuario usuario) {
		return usuarioRepository.save(usuario);
	}
}
