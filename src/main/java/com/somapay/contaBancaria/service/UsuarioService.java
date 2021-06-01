package com.somapay.contaBancaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.somapay.contaBancaria.exceptions.RegistroJaExistenteException;
import com.somapay.contaBancaria.model.Usuario;
import com.somapay.contaBancaria.repository.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	public Usuario criarUsuario(Usuario usuario) {
		if(usuarioRepository.existsByLogin(usuario.getLogin())) {
			throw new RegistroJaExistenteException("Usuário com este login já cadastrado");
		}
		usuario.setSenha(new BCryptPasswordEncoder().encode(usuario.getSenha()));
		return usuarioRepository.save(usuario);
	}
}
