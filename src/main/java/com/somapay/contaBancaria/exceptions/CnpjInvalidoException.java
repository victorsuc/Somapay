package com.somapay.contaBancaria.exceptions;

public class CnpjInvalidoException extends IllegalArgumentException {
	public CnpjInvalidoException(String mensagemDeErro) {
		super(mensagemDeErro);
	}
	
	public CnpjInvalidoException(String mensagemDeErro, Throwable erro) {
		super(mensagemDeErro, erro);
	}
}
