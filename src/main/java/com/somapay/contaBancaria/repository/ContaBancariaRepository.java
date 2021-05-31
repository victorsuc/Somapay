package com.somapay.contaBancaria.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.somapay.contaBancaria.dto.SaldoContaBancariaDto;
import com.somapay.contaBancaria.model.ContaBancaria;

@Repository
public interface ContaBancariaRepository extends JpaRepository<ContaBancaria, Long>{
	public boolean existsByEmpresaId(long idEmpresa);
	public boolean existsByFuncionarioId(long idFuncionario);
	public SaldoContaBancariaDto findByEmpresaId(long idEmpresa);
	public SaldoContaBancariaDto findByFuncionarioId(long idFuncionario);
	
	@Transactional
	@Modifying
	@Query("update ContaBancaria c set c.saldo = :saldo where c.id = :id")
	public void alterarSaldoContaBancaria(Long id, BigDecimal saldo);
}
