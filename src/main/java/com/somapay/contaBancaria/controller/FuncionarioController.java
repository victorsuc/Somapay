package com.somapay.contaBancaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somapay.contaBancaria.exceptions.CpfInvalidoException;
import com.somapay.contaBancaria.exceptions.RegistroJaExistenteException;
import com.somapay.contaBancaria.model.Funcionario;
import com.somapay.contaBancaria.service.FuncionarioService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path = "funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping
	@ApiOperation(value = "Adiciona um funcionário vinculado à empresa")
	public ResponseEntity<Funcionario> salvar(@RequestBody Funcionario funcionario) {
		try {
			return new ResponseEntity<Funcionario>(funcionarioService.salvar(funcionario), HttpStatus.CREATED);			
		} catch (CpfInvalidoException e) {
			throw e;
		} catch (RegistroJaExistenteException e) {
			throw e;
		}
	}

	@GetMapping()
	@ApiOperation(value = "Busca todos os funcionarios")
	public ResponseEntity<List<Funcionario>> buscarFuncionarios() {
		return new ResponseEntity<List<Funcionario>>(funcionarioService.buscarFuncionarios(), HttpStatus.OK);
	}
}
