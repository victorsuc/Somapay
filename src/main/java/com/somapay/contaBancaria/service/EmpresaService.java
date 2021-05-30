package com.somapay.contaBancaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somapay.contaBancaria.model.Empresa;
import com.somapay.contaBancaria.repository.EmpresaRepository;

@Service
public class EmpresaService {
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	public Empresa salvar(Empresa empresa) {
		return empresaRepository.save(empresa);
	}
	
	public List<Empresa> buscarTodasEmpresas(){
		return empresaRepository.findAll();
	}
}
