package com.somapay.contaBancaria.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.somapay.contaBancaria.model.Usuario;
import com.somapay.contaBancaria.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByLogin(login);
		if (usuario != null) {
			return User.withUsername(usuario.getLogin()).password(usuario.getSenha()).roles("CLIENT").build();
		} else {
			throw new UsernameNotFoundException("Não foi possível encontrar o usuário " + login);
		}
	}
}
