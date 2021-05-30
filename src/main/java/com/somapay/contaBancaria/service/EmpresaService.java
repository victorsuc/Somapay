package com.somapay.contaBancaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somapay.contaBancaria.model.Empresa;
import com.somapay.contaBancaria.repository.EmpresaRepository;
import com.somapay.contaBancaria.utils.ValidatorUtils;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	public Empresa salvar(Empresa empresa) throws Exception {
		if (!ValidatorUtils.verificarSeCnpjValido(empresa.getCnpj())) {
			throw new Exception("Número de CNPJ inválido");
		}
		if (empresaRepository.existsByCnpj(empresa.getCnpj())) {
			throw new Exception("Empresa já cadastrada");
		}
		return empresaRepository.save(empresa);
	}

	public List<Empresa> buscarTodasEmpresas() {
		return empresaRepository.findAll();
	}
}
