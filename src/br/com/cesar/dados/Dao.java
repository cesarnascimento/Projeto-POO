package br.com.cesar.dados;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import br.com.cesar.interfaces.IDao;

public class Dao<T> implements IDao<T> {
	private File arquivo;

	public Dao(Class tipo) {
		arquivo = new File(tipo.getSimpleName() + ".file");
		
		try {
			validarArquivo();
		} catch (IOException e) {
			System.out.println("Falha na leitura dos dados.");
		}
	}

	@Override
	public void escrever(List<T> list) throws IOException {
		FileOutputStream fos = new FileOutputStream(arquivo);
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		
		oos.writeObject(list);
		
		oos.flush();
		fos.flush();
		
		oos.close();
		fos.close();
	}

	@Override
	public List<T> ler() throws IOException, ClassNotFoundException {
		List<T> list = null;
		
		if(isArquivoVazio()) {
			list = new ArrayList<>();
		} else {
			FileInputStream fis = new FileInputStream(arquivo);
			ObjectInputStream ois = new ObjectInputStream(fis);
			
			list = (ArrayList<T>) ois.readObject();
			
			ois.close();
			fis.close();
		}
		
		return list;
	}
	
	private boolean isArquivoVazio() throws IOException {
		boolean flag = true;
		
		FileReader reader = new FileReader(arquivo);
		BufferedReader bufferedReader = new BufferedReader(reader);
		
		String linha = bufferedReader.readLine();
		
		bufferedReader.close();
		reader.close();
		
		if(linha != null) {
			if(!linha.equals("")) {
				flag = false;
			}
		}
		
		return flag;
	}
	
	private void validarArquivo() throws IOException {
		if (!arquivo.exists()) {
			arquivo.createNewFile();
		}
	}

}
