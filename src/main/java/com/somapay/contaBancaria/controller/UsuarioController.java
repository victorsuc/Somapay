package com.somapay.contaBancaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somapay.contaBancaria.exceptions.RegistroJaExistenteException;
import com.somapay.contaBancaria.model.Usuario;
import com.somapay.contaBancaria.service.UsuarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "usuario")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@PostMapping
	@ApiOperation(value = "Cria um usuário com login e senha para autenticação")
	public ResponseEntity<Usuario> criarUsuario(@RequestBody Usuario usuario) {
		try {
			return new ResponseEntity<Usuario>(usuarioService.criarUsuario(usuario), HttpStatus.CREATED);
		} catch (RegistroJaExistenteException e) {
			throw e;
		}
	}
}
