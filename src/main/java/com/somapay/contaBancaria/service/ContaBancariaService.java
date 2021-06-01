package com.somapay.contaBancaria.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.somapay.contaBancaria.dto.SaldoContaBancariaDto;
import com.somapay.contaBancaria.exceptions.RegistroNaoEncontradoException;
import com.somapay.contaBancaria.model.ContaBancaria;
import com.somapay.contaBancaria.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService {
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;

	public ContaBancaria salvar(ContaBancaria contaBancaria) throws Exception {
		if (contaBancaria.getSaldo() == null) {
			contaBancaria.setSaldo(BigDecimal.ZERO);
		}
		if (contaBancaria.getEmpresa() == null && contaBancaria.getFuncionario() == null) {
			throw new Exception("A conta deve possuir uma empresa ou um funcionário");
		}
		if (contaBancaria.getEmpresa() != null && contaBancaria.getFuncionario() != null) {
			throw new Exception("A conta deve possuir apenas uma empresa ou um funcionário");
		}
		if (contaBancaria.getEmpresa() != null) {
			if (contaBancariaRepository.existsByEmpresaId(contaBancaria.getEmpresa().getId())) {
				throw new Exception("Conta bancária já cadastrada para esta empresa");
			}
		} else {
			if (contaBancariaRepository.existsByFuncionarioId(contaBancaria.getFuncionario().getId())) {
				throw new Exception("Conta bancária já cadastrada para este funcionário");
			}
		}
		return contaBancariaRepository.save(contaBancaria);
	}

	public List<ContaBancaria> buscarTodasContasBancarias() {
		return contaBancariaRepository.findAll();
	}
	
	public SaldoContaBancariaDto buscarSaldoEmpresaBy(long idEmpresa) {
		SaldoContaBancariaDto saldoContaEmpresaDto = contaBancariaRepository.findByEmpresaId(idEmpresa);
		if(saldoContaEmpresaDto == null) {
			throw new RegistroNaoEncontradoException("Empresa não possui conta bancária");
		}
		
		return saldoContaEmpresaDto;
	}
	
	public SaldoContaBancariaDto buscarSaldoFuncionarioBy(long idFuncionario) {
		SaldoContaBancariaDto saldoContaFuncionarioDto = contaBancariaRepository.findByFuncionarioId(idFuncionario);
		if(saldoContaFuncionarioDto == null) {
			throw new RegistroNaoEncontradoException("Funcionário não possui conta bancária");
		}
		
		return saldoContaFuncionarioDto;
	}
	@Transactional
	public void debitarValorContaBancariaEmpresa(Long idEmpresa, BigDecimal valor) {
		SaldoContaBancariaDto saldoContaEmpresaDto = buscarSaldoEmpresaBy(idEmpresa);
		BigDecimal novoSaldo = saldoContaEmpresaDto.getSaldo().subtract(valor);

		contaBancariaRepository.alterarSaldoContaBancaria(saldoContaEmpresaDto.getId(), novoSaldo);
	}
	@Transactional
	public void creditarValorContaBancariaEmpresa(Long idEmpresa, BigDecimal valor) {
		SaldoContaBancariaDto saldoContaEmpresaDto = buscarSaldoEmpresaBy(idEmpresa);
		BigDecimal novoSaldo = saldoContaEmpresaDto.getSaldo().add(valor);
		
		contaBancariaRepository.alterarSaldoContaBancaria(saldoContaEmpresaDto.getId(), novoSaldo);
	}
	@Transactional
	public void creditarValorContaBancariaFuncionario(Long idFuncionario, BigDecimal valor) {
		SaldoContaBancariaDto saldoContaFuncionarioDto = buscarSaldoFuncionarioBy(idFuncionario);
		BigDecimal novoSaldo = saldoContaFuncionarioDto.getSaldo().add(valor);

		contaBancariaRepository.alterarSaldoContaBancaria(saldoContaFuncionarioDto.getId(), novoSaldo);
	}
}
