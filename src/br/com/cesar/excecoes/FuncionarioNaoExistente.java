package br.com.cesar.excecoes;

public class FuncionarioNaoExistente extends Exception {
	public FuncionarioNaoExistente(String message) {
		super(message);
	}
}
