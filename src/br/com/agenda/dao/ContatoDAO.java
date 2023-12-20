package br.com.agenda.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import br.com.agenda.factory.ConnectionFactory;
import br.com.agenda.model.Contato;

// CRUD
public class ContatoDAO {

	// Metodo para salvar no banco de dados
	public void save(Contato contato) {
		String sql = "INSERT INTO contatos (nome, idade, dataCadastro) VALUES (?, ?, ?)";
		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar uma conexão com o banco de dados
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criar uma PreparedStatement para executar a query
			pstm = conn.prepareStatement(sql);

			// Adicionar os parâmetros que são esperados pela query
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// Executar query
			pstm.execute();

			System.out.println("Contato salvo com sucesso.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	// Metodo para buscar todos os contatos em uma lista
	public List<Contato> getContatos() {
		String sql = "SELECT * FROM contatos";
		List<Contato> contatos = new ArrayList<>();
		Connection conn = null;
		PreparedStatement pstm = null;
		// Classe que vai recuperar os dados do banco. ***SELECT***
		ResultSet rset = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			rset = pstm.executeQuery();

			while (rset.next()) {
				Contato contato = new Contato();
				// Recuperar o ID
				contato.setId(rset.getInt("id"));
				// Recuperar o nome
				contato.setNome(rset.getString("nome"));
				// Recuperar idade
				contato.setIdade(rset.getInt("idade"));
				// Recuperar Data de cadastro
				contato.setDataCadastro(rset.getDate("dataCadastro"));

				// adicionar a lista
				contatos.add(contato);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (rset != null) {
					rset.close();
				}
				if (pstm != null) {
					pstm.close();
				}
				if (conn != null) {
					conn.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return contatos;
	}

	// Metodo para fazer update em um registro
	public void update(Contato contato) {

		String sql = "UPDATE contatos SET nome = ?, idade = ?, datacadastro = ? " + "WHERE id = ?";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			// Criar conexão com o banco
			conn = ConnectionFactory.createConnectionToMySQL();

			// Criar a classe para executar a query
			pstm = conn.prepareStatement(sql);

			// Adicionar os valores para atualizar
			pstm.setString(1, contato.getNome());
			pstm.setInt(2, contato.getIdade());
			pstm.setDate(3, new Date(contato.getDataCadastro().getTime()));

			// Qual o id do registro que deseja atualizar? no caso é o WHERE do sql
			pstm.setInt(4, contato.getId());

			// Exectar a query
			pstm.execute();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (pstm != null) {
					pstm.close();
				}

				if (conn != null) {
					conn.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}

	// Metodo para deletar um registro
	public void deleteById(int id) {
		String sql = "DELETE FROM contatos WHERE ID = ? ";

		Connection conn = null;
		PreparedStatement pstm = null;

		try {
			conn = ConnectionFactory.createConnectionToMySQL();
			pstm = conn.prepareStatement(sql);

			// registro a ser deletado 1 é o parametro 1
			pstm.setInt(1, id);
			// executar a query
			pstm.execute();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
				if (pstm != null) {
					pstm.close();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
