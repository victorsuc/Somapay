package com.somapay.contaBancaria.exceptions;

public class RegistroJaExistenteException extends IllegalArgumentException{
	public RegistroJaExistenteException(String mensagemDeErro) {
		super(mensagemDeErro);
	}
	
	public RegistroJaExistenteException(String mensagemDeErro, Throwable erro) {
		super(mensagemDeErro, erro);
	}
}
