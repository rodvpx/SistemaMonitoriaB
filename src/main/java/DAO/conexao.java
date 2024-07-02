package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class conexao {

    private static String url = "jdbc:mysql://localhost:3306/monitoria";
    private static String user = "root";
    private static String password = "";

    private static Connection conexao;

    public static Connection getConexao() throws SQLException {
        if (conexao == null || conexao.isClosed()) {
            conexao = DriverManager.getConnection(url, user, password);
        }
        return conexao; //oi
    }
}