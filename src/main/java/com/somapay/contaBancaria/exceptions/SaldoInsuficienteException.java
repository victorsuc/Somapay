package com.somapay.contaBancaria.exceptions;

public class SaldoInsuficienteException extends IllegalArgumentException{
	public SaldoInsuficienteException(String mensagemDeErro) {
		super(mensagemDeErro);
	}
	
	public SaldoInsuficienteException(String mensagemDeErro, Throwable erro) {
		super(mensagemDeErro, erro);
	}
}
