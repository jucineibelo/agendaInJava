package br.com.aplicacao;

import java.util.Date;
import br.com.agenda.dao.ContatoDAO;
import br.com.agenda.model.Contato;

public class Main {

	public static void main(String[] args) {

		//Salvando um contato 
		ContatoDAO contatoDao = new ContatoDAO();
		Contato contato = new Contato();
		contato.setNome("Jorge");
		contato.setIdade(25);
		contato.setDataCadastro(new Date());
		contatoDao.save(contato);
		
		//Fazendo update de um contato
		Contato c1 = new Contato();
		c1.setNome("Joaquim");
		c1.setIdade(30);
		c1.setDataCadastro(new Date());
		c1.setId(2);
		contatoDao.update(c1);
		
		//Utilizando a função para deletar
		contatoDao.deleteById(12);
		
		
		//Visualização dos registros do banco de dados
		for(Contato c : contatoDao.getContatos()) {
			System.out.println("Contato: " + c.getNome());
		
		}

	}

}
