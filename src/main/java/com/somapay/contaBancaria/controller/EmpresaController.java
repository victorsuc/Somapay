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

import com.somapay.contaBancaria.model.Empresa;
import com.somapay.contaBancaria.service.EmpresaService;

@RestController
@RequestMapping(path="empresa")
public class EmpresaController {
	@Autowired
	private EmpresaService empresaService;

	@PostMapping
	public ResponseEntity<Empresa> salvar(@RequestBody Empresa empresa) throws Exception {
		return new ResponseEntity<Empresa>(empresaService.salvar(empresa), HttpStatus.OK);
	}
	
	@GetMapping()
    public ResponseEntity<List<Empresa>> buscarEmpresas() {
        return new ResponseEntity<List<Empresa>>(empresaService.buscarTodasEmpresas(), HttpStatus.OK);
    }
}
