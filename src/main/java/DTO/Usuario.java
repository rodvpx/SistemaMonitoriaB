package DTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static DAO.conexao.getConexao;

public abstract class Usuario {

    protected String nome;
    protected String email;
    protected String senha;
    protected String matricula;
    protected String tipo;

    public Usuario(String matricula, String nome, String email, String senha, String tipo) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.tipo = tipo;
    }

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

    public String login(String email, String senha) throws SQLException {
        String sql = "SELECT * FROM usuario WHERE email = ? AND senha = ?";
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql)) {
            sta.setString(1, email);
            sta.setString(2, senha);
            ResultSet rs = sta.executeQuery();
            if (rs.next()) {
                System.out.println("Usuário validado com sucesso.");
                return rs.getString("tipo");
            } else {
                System.out.println("Usuário não encontrado ou senha incorreta.");
                return null;
            }
        }
    }

    // Getters e Setters

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
