package br.com.cesar.interfaces;

import java.io.IOException;

import br.com.cesar.entidades.Chamado;
import br.com.cesar.excecoes.ChamadoNaoExistente;

public interface INegocioChamado {
	void adicionarChamado(Chamado chamado) throws ClassNotFoundException, IOException;
	Chamado buscarChamado(int numeroChamado) throws ClassNotFoundException, IOException, ChamadoNaoExistente;
	void removerChamado(int numeroChamado) throws ClassNotFoundException, IOException, ChamadoNaoExistente;
	void alterarChamado(int numeroChamado, Chamado novoChamado) throws ClassNotFoundException, IOException, ChamadoNaoExistente;
}
