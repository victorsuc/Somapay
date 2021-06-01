package com.somapay.contaBancaria.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somapay.contaBancaria.dto.FuncionarioPagamentoSalarioDto;
import com.somapay.contaBancaria.exceptions.CpfInvalidoException;
import com.somapay.contaBancaria.exceptions.RegistroJaExistenteException;
import com.somapay.contaBancaria.model.Funcionario;
import com.somapay.contaBancaria.repository.FuncionarioRepository;
import com.somapay.contaBancaria.utils.ValidatorUtils;

@Service
public class FuncionarioService {
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	public Funcionario salvar(Funcionario funcionario) {
		if(!ValidatorUtils.verificarSeCpfValido(funcionario.getCpf())) {
			throw new CpfInvalidoException("Número de CPF inválido");
		}
		if(funcionarioRepository.existsByCpfAndEmpresaId(funcionario.getCpf(), funcionario.getEmpresa().getId())) {
			throw new RegistroJaExistenteException("Funcionário com este cpf já cadastrado para esta empresa");
		}
		return funcionarioRepository.save(funcionario);
	}
	
	public List<Funcionario> buscarFuncionarios(){
		return funcionarioRepository.findAll();
	}
	
	public List<FuncionarioPagamentoSalarioDto> buscarIdFuncionariosESalarioPorEmpresa(Long idEmpresa){
		return funcionarioRepository.findByEmpresaId(idEmpresa);
	}
}
