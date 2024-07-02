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

    protected abstract boolean login();


		/*
		//antigo metodo cadastrar usuario
		if (!validar()) {
			System.out.println("Erro: Informações inválidas.");
			return;
		}
	
		// Verifica se o e-mail ou matrícula já estão sendo usados
		for (Usuario usuario : usuarios) {
			if (usuario.getEmail().equals(this.email)) {
				System.out.println("Erro: Este e-mail já está cadastrado.");
				return;
			}
			if (usuario.getMatricula().equals(this.matricula)) {
				System.out.println("Erro: Esta matrícula já está cadastrada.");
				return;
			}
		}
	
		// Se passou pelas verificações, adiciona o novo usuário
		usuarios.add(this);
		System.out.println("Usuário cadastrado com sucesso.");*/


		/*
		//antigo metodo de login
		if (this.email.equals(email) && this.senha.equals(senha)) {
			System.out.println("Login realizado com sucesso.");
			return true;
		} else {
			System.out.println("Email ou senha incorretos. Tente novamente.");
			return false;
		}*/


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
