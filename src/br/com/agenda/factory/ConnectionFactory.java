package br.com.agenda.factory;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	// Nome do usuário do MySQL
	private static final String USERNAME = "root";

	// Senha do banco
	private static final String PASSWORD = "root";

	// Caminho do banco de dados, porta e nome do banco de dados
	private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/agenda";

	// Conexão com o banco de dados
	public static Connection createConnectionToMySQL() throws Exception {
		// Não é mais necessário carregar manualmente a classe do driver
		// Class.forName("com.mysql.jdbc.Driver");

		// Cria a conexão com o banco de dados
		Connection connection = DriverManager.getConnection(DATABASE_URL, USERNAME, PASSWORD);

		return connection;
	}

	public static void main(String[] args) throws Exception {
		// Recuperar uma conexão com o banco de dados
		Connection conn = createConnectionToMySQL();

		// Testar se a conexão é nula
		if (conn != null) {
			System.out.println("Conexão obtida com sucesso.");
			conn.close();
		}
	}
}
