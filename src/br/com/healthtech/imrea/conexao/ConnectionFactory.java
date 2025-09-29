package br.com.healthtech.imrea.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConnectionFactory {
    public static Connection getConnection(){
        Connection conn = null;
        try {
            Class.forName(Credenciais.driver);
            conn = DriverManager.getConnection(Credenciais.url, Credenciais.user, Credenciais.password);;
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Falha na conexão com o banco de dados.");
            // Lançar a exceção é importante para parar a execução se a conexão falhar.
            throw new RuntimeException("Não foi possível conectar ao banco de dados.", e);
        }
    }
}
