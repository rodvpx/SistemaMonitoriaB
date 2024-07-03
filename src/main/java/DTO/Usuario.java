package DTO;

import java.sql.SQLException;

public abstract class Usuario {

    protected String nome;
    protected String email;
    protected String senha;
    protected String matricula;

    public Usuario(String matricula, String nome, String email, String senha) {
        this.matricula = matricula;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    protected abstract boolean validar();

    protected abstract boolean cadastrarNovoUsuario() throws SQLException, SQLException;

    protected abstract boolean login(String email, String senha) throws  SQLException;

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

}
