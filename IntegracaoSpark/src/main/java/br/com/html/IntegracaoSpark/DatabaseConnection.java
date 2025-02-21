package br.com.html.IntegracaoSpark;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:postgresql://localhost:5432/Projeto3";
    private static final String USER = "postgres";
    private static final String PASSWORD = "VINGADORES";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}