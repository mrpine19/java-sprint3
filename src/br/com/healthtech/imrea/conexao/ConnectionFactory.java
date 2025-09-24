package br.com.healthtech.imrea.conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {
    public static Connection getConnection() throws SQLException {
        String url = "jdbc:oracle:thin:@localhost:1521:xe";
        String user = "seu_usuario"; // Usuário do Oracle
        String password = "sua_senha"; // Senha do Oracle
        return DriverManager.getConnection(url, user, password);
    }
}
