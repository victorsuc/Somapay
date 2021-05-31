package com.somapay.contaBancaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.somapay.contaBancaria.model.Empresa;
import com.somapay.contaBancaria.service.EmpresaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path="empresa")
public class EmpresaController {
	@Autowired
	private EmpresaService empresaService;

	@PostMapping
	@ApiOperation(value = "Adiciona uma nova empresa")
	public ResponseEntity<Empresa> salvar(@RequestBody Empresa empresa) throws Exception {
		return new ResponseEntity<Empresa>(empresaService.salvar(empresa), HttpStatus.CREATED);
	}
	
	@GetMapping()
	@ApiOperation(value = "Busca todas as empresas")
    public ResponseEntity<List<Empresa>> buscarEmpresas() {
        return new ResponseEntity<List<Empresa>>(empresaService.buscarTodasEmpresas(), HttpStatus.OK);
    }
	
	@PutMapping(value="/pagar-salarios")
	@ApiOperation(value = "Paga os salários de todos os funcionários da empresa")
	@Transactional
	public HttpStatus pagarSalarioParaFuncionariosDaEmpresa(@RequestParam Long idEmpresa) throws Exception {
		empresaService.pagarSalarios(idEmpresa);
		return HttpStatus.OK;
	}
}
