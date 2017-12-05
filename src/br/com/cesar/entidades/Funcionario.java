package br.com.cesar.entidades;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class Funcionario extends Pessoa implements Serializable {
	private String login, senha, cargo;
	private List<Chamado> chamadosConcluidos;
	
	public Funcionario() {
		
	}
	
	public Funcionario(int id, String nomeCompleto, Calendar nascimento, String login,
			String senha, String cargo, List<Chamado> chamadosConcluidos) {
		super(id, nomeCompleto, nascimento);
		this.login = login;
		this.senha = senha;
		this.cargo = cargo;
		this.chamadosConcluidos = chamadosConcluidos;
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
	public List<Chamado> getChamadosConcluidos() {
		return chamadosConcluidos;
	}
	public void setChamadosConcluidos(List<Chamado> chamadosConcluidos) {
		this.chamadosConcluidos = chamadosConcluidos;
	}
	public String getCargo() {
		return cargo;
	}
	public void setCargo(String cargo) {
		this.cargo = cargo;
	}
}
