package br.com.cesar.interfaces;

import java.io.IOException;

import br.com.cesar.entidades.Funcionario;
import br.com.cesar.excecoes.FuncionarioNaoExistente;

public interface INegocioFuncionario {
	void adicionarFuncionario(Funcionario funcionario) throws ClassNotFoundException, IOException;
	Funcionario buscarFuncionario(int id) throws ClassNotFoundException, IOException, FuncionarioNaoExistente;
	void removerFuncionario(int id) throws ClassNotFoundException, IOException, FuncionarioNaoExistente;
	void alterarFuncionario(int id, Funcionario novoFuncionario) throws ClassNotFoundException, IOException, FuncionarioNaoExistente;
}
