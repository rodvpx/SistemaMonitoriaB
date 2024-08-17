package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static factory.conexao.getConexao;

public class SupervisorDao {

    public static int obterIdSupervisor(String matricula) {

        String sql = "SELECT id FROM usuario WHERE matricula = ? AND tipo = 'S'";
        int idSupervisor = 0;

        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {

            sta.setString(1, matricula);

            try (ResultSet rs = sta.executeQuery()) {
                if (rs.next()) {
                    idSupervisor = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return idSupervisor;
    }

}
