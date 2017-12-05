package br.com.cesar.entidades;

import java.io.Serializable;
import java.util.Calendar;

public abstract class Pessoa implements Serializable {
	private int id;
	private String nomeCompleto;
	private Calendar nascimento;
	
	public Pessoa() {
		
	}
	
	public Pessoa(int id, String nomeCompleto, Calendar nascimento) {
		this.id = id;
		this.nomeCompleto = nomeCompleto;
		this.nascimento = nascimento;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNomeCompleto() {
		return nomeCompleto;
	}
	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}
	public Calendar getNascimento() {
		return nascimento;
	}
	public void setNascimento(Calendar nascimento) {
		this.nascimento = nascimento;
	}
}
