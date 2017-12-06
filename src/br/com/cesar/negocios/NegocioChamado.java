package br.com.cesar.negocios;

import java.io.IOException;
import java.util.List;

import br.com.cesar.dados.Dao;
import br.com.cesar.entidades.Chamado;
import br.com.cesar.excecoes.ChamadoNaoExistente;
import br.com.cesar.interfaces.IDao;
import br.com.cesar.interfaces.INegocioChamado;

public class NegocioChamado implements INegocioChamado {
	private IDao<Chamado> dao;
	
	public NegocioChamado() {
		dao = new Dao<>(Chamado.class);
	}
	
	@Override
	public void adicionarChamado(Chamado chamado) throws ClassNotFoundException, IOException {
		List<Chamado> chamados = dao.ler();
		chamados.add(chamado);
		dao.escrever(chamados);
	}

	@Override
	public Chamado buscarChamado(int numeroChamado) throws ClassNotFoundException, IOException, ChamadoNaoExistente {
		List<Chamado> chamados = dao.ler();
		
		for(Chamado c : chamados) {
			if(c.getNumeroChamado() == numeroChamado)
				return c;
		}
		
		throw new ChamadoNaoExistente("Número inserido inválido.");
	}

	@Override
	public void removerChamado(int numeroChamado) throws ClassNotFoundException, IOException, ChamadoNaoExistente {
		List<Chamado> chamados = dao.ler();
		chamados.remove(numeroChamado);
		dao.escrever(chamados);
	}

	@Override
	public void alterarChamado(int numeroChamado, Chamado novoChamado) throws ClassNotFoundException, IOException, ChamadoNaoExistente {
		List<Chamado> chamados = dao.ler();
		removerChamado(numeroChamado);
		adicionarChamado(novoChamado);
		dao.escrever(chamados);
	}
	
	public List<Chamado> listarTodosChamados() throws ClassNotFoundException, IOException {
		return dao.ler();
	}
}
