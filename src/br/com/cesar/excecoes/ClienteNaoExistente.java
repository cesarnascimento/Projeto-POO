package br.com.cesar.excecoes;

public class ClienteNaoExistente extends Exception {

	public ClienteNaoExistente(String message) {
		super(message);
	}
}
