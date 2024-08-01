package dao;

import model.Supervisor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static factory.conexao.getConexao;

public class UsuarioDao {

    protected boolean validar() {
        // Implement validation logic if needed
        return true;
    }

    public boolean cadastrarNovoUsuario() throws SQLException {
        String sql = "INSERT INTO usuario (nome, email, senha, matricula, tipo) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {

            sta.setString(1, nome);
            sta.setString(2, email);
            sta.setString(3, senha);
            sta.setString(4, matricula);
            sta.setString(5, tipo);
            sta.executeUpdate();

            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Supervisor login(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {
            sta.setString(1, email);
            sta.setString(2, senha);
            ResultSet rs = sta.executeQuery();
            if (rs.next()) {
                System.out.println("Usuário validado com sucesso.");
                String matricula = rs.getString("matricula");
                String nome = rs.getString("nome");
                String tipo = rs.getString("tipo");
                if("S".equals(tipo)) {
                    return new Supervisor(matricula, nome, email, senha);
                }
            } else {
                System.out.println("Usuário não encontrado ou senha incorreta.");
                return null;
            }
        }
        return null;
    }
}
