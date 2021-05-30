package com.somapay.contaBancaria.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somapay.contaBancaria.model.ContaBancaria;
import com.somapay.contaBancaria.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService {
	@Autowired
	private ContaBancariaRepository contaBancariaRepository;
	
	public ContaBancaria salvar(ContaBancaria contaBancaria) throws Exception {
		if(contaBancaria.getSaldo() == null) {
			contaBancaria.setSaldo(BigDecimal.ZERO);
		}
		if(contaBancaria.getEmpresa() != null && contaBancaria.getFuncionario() != null) {
			throw new Exception("A conta deve possuir apenas uma empresa ou um funcionário");
		}
		return contaBancariaRepository.save(contaBancaria);
	}
}
