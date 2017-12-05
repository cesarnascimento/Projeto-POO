package br.com.cesar.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Cliente extends Pessoa implements Serializable {
	private String login, senha;

	public Cliente() {
	}
	
	public Cliente(int id, String nomeCompleto, Calendar nascimento, String login, String senha) {
		super(id, nomeCompleto, nascimento);
		this.login = login;
		this.senha = senha;
	}
	
	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
