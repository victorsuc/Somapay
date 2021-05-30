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

import com.somapay.contaBancaria.model.Funcionario;
import com.somapay.contaBancaria.service.FuncionarioService;

@RestController
@RequestMapping(path="funcionario")
public class FuncionarioController {
	@Autowired
	private FuncionarioService funcionarioService;

	@PostMapping
	public ResponseEntity<Funcionario> salvar(@RequestBody Funcionario funcionario) throws Exception {
		return new ResponseEntity<Funcionario>(funcionarioService.salvar(funcionario), HttpStatus.OK);
	}
	
	@GetMapping()
    public ResponseEntity<List<Funcionario>> buscarFuncionarios() {
        return new ResponseEntity<List<Funcionario>>(funcionarioService.buscarFuncionarios(), HttpStatus.OK);
    }
}
