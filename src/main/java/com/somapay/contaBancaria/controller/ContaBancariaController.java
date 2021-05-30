package com.somapay.contaBancaria.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.somapay.contaBancaria.model.ContaBancaria;
import com.somapay.contaBancaria.service.ContaBancariaService;

@RestController
@RequestMapping(path="conta-bancaria")
public class ContaBancariaController {
	@Autowired
	private ContaBancariaService contaBancariaService;

	@PostMapping
	public ResponseEntity<ContaBancaria> salvar(@RequestBody ContaBancaria contaBancaria) throws Exception {
		return new ResponseEntity<ContaBancaria>(contaBancariaService.salvar(contaBancaria), HttpStatus.OK);
	}
}