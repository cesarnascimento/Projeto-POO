package br.com.cesar.negocios;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import br.com.cesar.dados.Dao;
import br.com.cesar.entidades.Funcionario;
import br.com.cesar.excecoes.FuncionarioNaoExistente;
import br.com.cesar.interfaces.IDao;
import br.com.cesar.interfaces.INegocioFuncionario;

public class NegocioFuncionario implements INegocioFuncionario {
	private IDao<Funcionario> dao;

	public NegocioFuncionario() {
		dao = new Dao<>(Funcionario.class);
	}

	@Override
	public void adicionarFuncionario(Funcionario funcionario) throws ClassNotFoundException, IOException {
		List<Funcionario> funcionarios = dao.ler();
		funcionarios.add(funcionario);
		dao.escrever(funcionarios);
	}

	@Override
	public Funcionario buscarFuncionario(int id) throws ClassNotFoundException, IOException, FuncionarioNaoExistente {
		List<Funcionario> funcionarios = dao.ler();

		for (Funcionario f : funcionarios) {
			if (f.getId() == id)
				return f;
		}

		throw new FuncionarioNaoExistente("ID inserida inválida.");
	}

	public Funcionario buscarFuncionario(String login) throws ClassNotFoundException, IOException, FuncionarioNaoExistente {
		List<Funcionario> funcionarios = dao.ler();
		
		Iterator<Funcionario> iterator = funcionarios.iterator();

		Funcionario funcionario = null;
		while (iterator.hasNext()) {
			funcionario = iterator.next();

			if (funcionario.getLogin().equals(login))
				return funcionario;
		}

		throw new FuncionarioNaoExistente("ID inserida inválida.");
	}

	@Override
	public void removerFuncionario(int id) throws ClassNotFoundException, IOException, FuncionarioNaoExistente {
		List<Funcionario> funcionarios = dao.ler();
		funcionarios.remove(buscarFuncionario(id));
		dao.escrever(funcionarios);
	}

	@Override
	public void alterarFuncionario(int id, Funcionario novoFuncionario)
			throws ClassNotFoundException, IOException, FuncionarioNaoExistente {
		List<Funcionario> funcionarios = dao.ler();
		removerFuncionario(id);
		adicionarFuncionario(novoFuncionario);
		dao.escrever(funcionarios);
	}

	public boolean contemFuncionario(String login) throws ClassNotFoundException, IOException {
		List<Funcionario> funcionarios = dao.ler();

		Iterator<Funcionario> iterator = funcionarios.iterator();

		Funcionario funcionario = null;
		while (iterator.hasNext()) {
			funcionario = iterator.next();

			if (funcionario.getLogin().equals(login))
				return true;
		}

		return false;
	}

	public boolean contemId(int id) throws ClassNotFoundException, IOException {
		List<Funcionario> funcionarios = dao.ler();

		Iterator<Funcionario> iterator = funcionarios.iterator();

		Funcionario funcionario = null;
		while (iterator.hasNext()) {
			funcionario = iterator.next();

			if (funcionario.getId() == id)
				return true;
		}

		return false;
	}
}
