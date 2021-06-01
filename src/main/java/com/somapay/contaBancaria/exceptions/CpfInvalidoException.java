package com.somapay.contaBancaria.exceptions;

public class CpfInvalidoException extends IllegalArgumentException {
	public CpfInvalidoException(String mensagemDeErro) {
		super(mensagemDeErro);
	}
	
	public CpfInvalidoException(String mensagemDeErro, Throwable erro) {
		super(mensagemDeErro, erro);
	}
}
