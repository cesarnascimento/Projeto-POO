package br.com.cesar.interfaces;

import java.io.IOException;
import java.util.List;

public interface IDao<T> {
	void escrever(List<T> list) throws IOException;
	List<T> ler() throws IOException, ClassNotFoundException;
}
