package com.somapay.contaBancaria.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.somapay.contaBancaria.dto.SaldoContaBancariaDto;
import com.somapay.contaBancaria.model.ContaBancaria;
import com.somapay.contaBancaria.service.ContaBancariaService;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(path="conta-bancaria")
public class ContaBancariaController {
	@Autowired
	private ContaBancariaService contaBancariaService;

	@PostMapping
	@ApiOperation(value = "Adiciona conta bancária para empresa ou funcionário com saldo zero")
	public ResponseEntity<ContaBancaria> salvar(@RequestBody ContaBancaria contaBancaria) throws Exception {
		return new ResponseEntity<ContaBancaria>(contaBancariaService.salvar(contaBancaria), HttpStatus.CREATED);
	}
	
	@GetMapping()
	@ApiOperation(value = "Busca todas as contas bancárias")
    public ResponseEntity<List<ContaBancaria>> buscarContasBancarias() {
        return new ResponseEntity<List<ContaBancaria>>(contaBancariaService.buscarTodasContasBancarias(), HttpStatus.OK);
    }
	
	@GetMapping(value="/saldo-empresa")
	@ApiOperation(value = "Busca saldo da conta corrente da empresa pelo id da empresa")
    public ResponseEntity<SaldoContaBancariaDto> buscarSaldoEmpresa(@RequestParam Long idEmpresa) throws Exception {
        return new ResponseEntity<SaldoContaBancariaDto>(contaBancariaService.buscarSaldoEmpresaBy(idEmpresa), HttpStatus.OK);
    }
	
	@GetMapping(value="/saldo-funcionario")
	@ApiOperation(value = "Busca saldo da conta corrente do funcionario pelo id do funcionario")
    public ResponseEntity<SaldoContaBancariaDto> buscarSaldoFuncionario(@RequestParam Long idFuncionario) throws Exception {
        return new ResponseEntity<SaldoContaBancariaDto>(contaBancariaService.buscarSaldoFuncionarioBy(idFuncionario), HttpStatus.OK);
    }
}
