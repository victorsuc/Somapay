package com.somapay.contaBancaria.exceptions;

public class RegistroNaoEncontradoException extends IllegalArgumentException{
	public RegistroNaoEncontradoException(String mensagemDeErro) {
		super(mensagemDeErro);
	}
	
	public RegistroNaoEncontradoException(String mensagemDeErro, Throwable erro) {
		super(mensagemDeErro, erro);
	}
}
