package br.com.cesar.negocios;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import br.com.cesar.dados.Dao;
import br.com.cesar.entidades.Cliente;
import br.com.cesar.excecoes.ClienteNaoExistente;
import br.com.cesar.interfaces.IDao;
import br.com.cesar.interfaces.INegocioCliente;

public class NegocioCliente implements INegocioCliente {

	private IDao<Cliente> dao;

	public NegocioCliente() {
		dao = new Dao<>(Cliente.class);
	}

	@Override
	public void adicionarCliente(Cliente cliente) throws ClassNotFoundException, IOException {
		List<Cliente> clientes = dao.ler();
		clientes.add(cliente);
		dao.escrever(clientes);
	}

	@Override
	public Cliente buscarCliente(int id) throws ClassNotFoundException, IOException, ClienteNaoExistente {
		List<Cliente> clientes = dao.ler();

		Iterator<Cliente> iterator = clientes.iterator();

		Cliente cliente = null;
		while (iterator.hasNext()) {
			cliente = iterator.next();

			if (cliente.getId() == id)
				return cliente;
		}

		throw new ClienteNaoExistente("ID inserido inválido.");
	}

	public Cliente buscarCliente(String login) throws ClassNotFoundException, IOException, ClienteNaoExistente {
		List<Cliente> clientes = dao.ler();

		Iterator<Cliente> iterator = clientes.iterator();
		
		Cliente cliente = null;
		while (iterator.hasNext()) {
			cliente = iterator.next();

			if (cliente.getLogin().equals(login))
				return cliente;
		}

		throw new ClienteNaoExistente("ID inserido inválido.");
	}

	@Override
	public void removerCliente(int id) throws ClassNotFoundException, IOException, ClienteNaoExistente {
		List<Cliente> clientes = dao.ler();
		clientes.remove(buscarCliente(id));
		dao.escrever(clientes);
	}

	@Override
	public void alterarCliente(int id, Cliente novoCliente) throws ClassNotFoundException, IOException, ClienteNaoExistente {
		List<Cliente> clientes = dao.ler();
		removerCliente(id);
		adicionarCliente(novoCliente);
		dao.escrever(clientes);
	}

	public boolean contemCliente(String login) throws ClassNotFoundException, IOException, ClienteNaoExistente {
		List<Cliente> clientes = dao.ler();

		Iterator<Cliente> iterator = clientes.iterator();

		Cliente cliente = null;
		while (iterator.hasNext()) {
			cliente = iterator.next();

			if (cliente.getLogin().equals(login))
				return true;
		}

		return false;
	}

	public boolean contemId(int id) throws ClassNotFoundException, IOException {
		List<Cliente> clientes = dao.ler();

		Iterator<Cliente> iterator = clientes.iterator();

		Cliente cliente = null;
		while (iterator.hasNext()) {
			cliente = iterator.next();

			if (cliente.getId() == id)
				return true;
		}

		return false;
	}
}
