package com.somapay.contaBancaria.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.somapay.contaBancaria.dto.FuncionarioPagamentoSalarioDto;
import com.somapay.contaBancaria.dto.SaldoContaBancariaDto;
import com.somapay.contaBancaria.model.Empresa;
import com.somapay.contaBancaria.repository.EmpresaRepository;
import com.somapay.contaBancaria.utils.ValidatorUtils;

@Service
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	@Autowired
	private FuncionarioService funcionarioService;

	@Autowired
	private ContaBancariaService contaBancariaService;

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

	public void pagarSalarios(Long idEmpresa) throws Exception {
		List<FuncionarioPagamentoSalarioDto> listaFuncionariosDaEmpresa = funcionarioService
				.buscarIdFuncionariosPorEmpresa(idEmpresa);

		if (verificarSeFolhaDePagamentoMaiorQueSaldoDaEmpresa(idEmpresa, listaFuncionariosDaEmpresa)) {
			throw new Exception("Saldo da empresa insuficiente para pagamento de folha salarial");
		}
	}

	private boolean verificarSeFolhaDePagamentoMaiorQueSaldoDaEmpresa(Long idEmpresa,
			List<FuncionarioPagamentoSalarioDto> listaFuncionariosDaEmpresa) {
		BigDecimal totalFolhaDaEmpresa = BigDecimal.ZERO;

		for (FuncionarioPagamentoSalarioDto funcionario : listaFuncionariosDaEmpresa) {
			totalFolhaDaEmpresa = totalFolhaDaEmpresa.add(funcionario.getSalario());
		}

		SaldoContaBancariaDto saldoContaEmpresaDto = contaBancariaService.buscarSaldoEmpresaBy(idEmpresa);
		return saldoContaEmpresaDto.getSaldo().compareTo(totalFolhaDaEmpresa) == -1;
	}
}
