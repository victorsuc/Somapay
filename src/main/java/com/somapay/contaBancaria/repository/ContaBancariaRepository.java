package com.somapay.contaBancaria.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.somapay.contaBancaria.dto.SaldoContaBancariaDto;
import com.somapay.contaBancaria.model.ContaBancaria;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long>{
	public boolean existsByEmpresaId(long idEmpresa);
	public boolean existsByFuncionarioId(long idFuncionario);
	public SaldoContaBancariaDto findByEmpresaId(long idEmpresa);
	public SaldoContaBancariaDto findByFuncionarioId(long idFuncionario);
}
