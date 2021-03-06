package com.somapay.contaBancaria.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.somapay.contaBancaria.dto.FuncionarioPagamentoSalarioDto;
import com.somapay.contaBancaria.model.Funcionario;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {
	public boolean existsByCpfAndEmpresaId(String cpf, Long idEmpresa);

	public List<FuncionarioPagamentoSalarioDto> findByEmpresaId(Long idEmpresa);

}
