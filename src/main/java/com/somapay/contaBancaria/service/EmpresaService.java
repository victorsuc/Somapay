package com.somapay.contaBancaria.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.somapay.contaBancaria.dto.FuncionarioPagamentoSalarioDto;
import com.somapay.contaBancaria.dto.SaldoContaBancariaDto;
import com.somapay.contaBancaria.exceptions.CnpjInvalidoException;
import com.somapay.contaBancaria.exceptions.RegistroJaExistenteException;
import com.somapay.contaBancaria.exceptions.RegistroNaoEncontradoException;
import com.somapay.contaBancaria.exceptions.SaldoInsuficienteException;
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

	private static final BigDecimal valorTarifaPagamentoFolha = BigDecimal.valueOf(0.0038);

	public Empresa salvar(Empresa empresa) {
		if (!ValidatorUtils.verificarSeCnpjValido(empresa.getCnpj())) {
			throw new CnpjInvalidoException("Número de CNPJ inválido");
		}
		if (empresaRepository.existsByCnpj(empresa.getCnpj())) {
			throw new RegistroJaExistenteException("Empresa com este cnpj já cadastrada");
		}
		return empresaRepository.save(empresa);
	}

	public List<Empresa> buscarTodasEmpresas() {
		return empresaRepository.findAll();
	}

	@Transactional
	public void pagarSalarios(Long idEmpresa) {
		List<FuncionarioPagamentoSalarioDto> listaFuncionariosDaEmpresa = funcionarioService
				.buscarIdFuncionariosESalarioPorEmpresa(idEmpresa);
		SaldoContaBancariaDto saldoContaEmpresaDto = contaBancariaService.buscarSaldoEmpresaBy(idEmpresa);

		if (verificarSeFolhaDePagamentoSalarialMaiorQueSaldoDaEmpresa(saldoContaEmpresaDto.getSaldo(),
				listaFuncionariosDaEmpresa)) {
			throw new SaldoInsuficienteException("Saldo da empresa insuficiente para pagamento de folha salarial");
		}

		for (FuncionarioPagamentoSalarioDto funcionario : listaFuncionariosDaEmpresa) {
			contaBancariaService.debitarValorContaBancariaEmpresa(idEmpresa,
					funcionario.getSalario().add(funcionario.getSalario().multiply(valorTarifaPagamentoFolha)));
			try {
				contaBancariaService.creditarValorContaBancariaFuncionario(funcionario.getId(),
						funcionario.getSalario());
			} catch (RegistroNaoEncontradoException e) {
				contaBancariaService.creditarValorContaBancariaEmpresa(idEmpresa, funcionario.getSalario());
				throw new RegistroNaoEncontradoException(e.getMessage(), e);
			}
		}
	}

	private boolean verificarSeFolhaDePagamentoSalarialMaiorQueSaldoDaEmpresa(BigDecimal saldoContaEmpresa,
			List<FuncionarioPagamentoSalarioDto> listaFuncionariosDaEmpresa) {
		BigDecimal totalFolhaDaEmpresa = BigDecimal.ZERO;

		for (FuncionarioPagamentoSalarioDto funcionario : listaFuncionariosDaEmpresa) {
			totalFolhaDaEmpresa = totalFolhaDaEmpresa.add(funcionario.getSalario().multiply(valorTarifaPagamentoFolha));
		}

		return saldoContaEmpresa.compareTo(totalFolhaDaEmpresa) == -1;
	}
}
