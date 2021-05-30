package com.somapay.contaBancaria.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "FUNCIONARIO", schema = "SOMAPAY")
public class Funcionario {
	@Id
	@GeneratedValue
	private long id;
	private String nome;
	private BigDecimal salario;
	@Column(name = "DATA_NASCIMENTO")
	private Date dataNascimento;
	@Column(name = "DATA_ADMISSAO")
	private Date dataAdmissao;
	private String cargo;
	
	@JsonIgnore
    @ManyToOne(cascade = CascadeType.DETACH, fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPRESA_FK")
    private Empresa empresa;
	
}
