package br.com.cesar.entidades;

import java.io.Serializable;

public class Chamado implements Serializable {
	private String titulo, categoria, setor, descricao;
	private int prioridade, numeroChamado;  // 1 - baixa, 2 - média e 3 - alta
	private Cliente cliente;

	public Chamado() {
		
	}
	
	public Chamado(int numeroChamado, String titulo, String categoria, String setor, String descricao, int prioridade) {
		this.titulo = titulo;
		this.categoria = categoria;
		this.setor = setor;
		this.descricao = descricao;
		this.prioridade = prioridade;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(int prioridade) {
		this.prioridade = prioridade;
	}

	public int getNumeroChamado() {
		return numeroChamado;
	}

	public void setNumeroChamado(int numeroChamado) {
		this.numeroChamado = numeroChamado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	@Override
	public String toString() {
		return "\nCriador do chamado: " + cliente.getNomeCompleto() +
				"\nChamado de número: " + numeroChamado +
				"\nTítulo do chamado: " + titulo.substring(0, 1).toUpperCase() + titulo.substring(1) +
				"\nCategoria do chamado: " + categoria.substring(0, 1).toUpperCase() + categoria.substring(1) +
				"\nSetor do chamado: " + setor.substring(0, 1).toUpperCase() + setor.substring(1) +
				"\nPrioridade do chamado:" + ((prioridade == 1) ? "Baixa": ((prioridade == 2) ? "Média" : "Alta")) +
				"\nDescrição do chamado: " + descricao.substring(0, 1).toUpperCase() + descricao.substring(1);
	}
}
