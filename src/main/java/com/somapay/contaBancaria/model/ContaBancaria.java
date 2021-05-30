package com.somapay.contaBancaria.model;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "CONTA_BANCARIA", schema = "SOMAPAY")
public class ContaBancaria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private BigDecimal saldo;

	@OneToOne
	@JoinColumn(name = "EMPRESA_FK")
	private Empresa empresa;
	
	@OneToOne
	@JoinColumn(name = "FUNCIONARIO_FK")
	private Funcionario funcionario;
}
