package dao;

import model.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static factory.conexao.getConexao;

public class UsuarioDao {

    public static boolean validacao(String matricula, String email, String tipo) throws SQLException {
        // Verificar comprimento da matrícula de acordo com o tipo
        if (tipo.equals("A") && matricula.length() != 10) {
            return false; // Matrícula de aluno deve ter 10 caracteres
        }
        if (tipo.equals("S") && matricula.length() != 12) {
            return false; // Matrícula de supervisor deve ter 12 caracteres
        }

        // Verificar unicidade da matrícula e do e-mail
        boolean matriculaUnica = isMatriculaUnica(matricula);
        boolean emailUnico = isEmailUnico(email);

        return matriculaUnica && emailUnico;
    }


    public static boolean cadastrarNovoUsuario(String matricula, String nome, String email, String tipo, String senha) throws SQLException {

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

    public static LoginResult login(String email, String senha) throws SQLException {

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

                Usuario usuario;
                if ("S".equals(tipo)) {
                    usuario = new Supervisor(matricula, nome, email, senha);
                } else if ("A".equals(tipo)) {
                    usuario = new Aluno(matricula, nome, email, senha);
                } else if ("M".equals(tipo)) {
                    usuario = new Monitor(matricula, nome, email, senha);
                } else {
                    return null; // Tipo desconhecido
                }

                return new LoginResult(usuario, tipo);
            } else {
                System.out.println("Usuário não encontrado ou senha incorreta.");
                return null;
            }
        }
    }

    public static boolean isMatriculaUnica(String matricula) throws SQLException {

        String query = "SELECT COUNT(*) FROM usuario WHERE matricula = ?";

        try (Connection conn = getConexao(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, matricula);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
            return false;
        }
    }

    public static boolean isEmailUnico(String email) throws SQLException {

        String query = "SELECT COUNT(*) FROM usuario WHERE email = ?";

        try (Connection conn = getConexao(); PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) == 0;
            }
            return false;
        }
    }
}
