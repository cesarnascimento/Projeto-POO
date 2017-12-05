package br.com.cesar.utils;

import java.io.IOException;
import java.util.Random;

import br.com.cesar.negocios.NegocioCliente;
import br.com.cesar.negocios.NegocioFuncionario;

public class Utils {
	private static NegocioCliente negocioCliente = new NegocioCliente();
	private static NegocioFuncionario negocioFuncionario = new NegocioFuncionario();

	public static int gerarIdCliente() throws ClassNotFoundException, IOException {
		Random rand = new Random();
		int id = rand.nextInt(999) + 1; // 1 até 1000

		while (negocioCliente.contemId(id)) {
			id = rand.nextInt(999) + 1; // 1 até 1000
		}

		return id;
	}

	public static int gerarIdFuncionario() throws ClassNotFoundException, IOException {
		Random rand = new Random();
		int id = rand.nextInt(999) + 1; // 1 até 1000

		while (negocioFuncionario.contemId(id)) {
			id = rand.nextInt(999) + 1; // 1 até 1000
		}

		return id;
	}
}
