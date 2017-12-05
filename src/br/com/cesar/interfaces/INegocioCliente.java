package br.com.cesar.interfaces;

import java.io.IOException;

import br.com.cesar.entidades.Cliente;
import br.com.cesar.excecoes.ClienteNaoExistente;

public interface INegocioCliente {
	void adicionarCliente(Cliente cliente) throws ClassNotFoundException, IOException;
	Cliente buscarCliente(int id) throws ClassNotFoundException, IOException, ClienteNaoExistente;
	void removerCliente(int id) throws ClassNotFoundException, IOException, ClienteNaoExistente;
	void alterarCliente(int id, Cliente novoCliente) throws ClassNotFoundException, IOException, ClienteNaoExistente;
}
