package controller;

import view.CadastroView;

public class CadastroController {

    private CadastroView cadastroView;

    public CadastroController(CadastroView cadastroView) {
        this.cadastroView = cadastroView;
        inicializar();
    }

    private void inicializar() {
        cadastroView.getBotaoCadastrar().addActionListener(e -> cadastrar());
        cadastroView.getBotaoVoltar().addActionListener(e -> voltar());
    }

    private void cadastrar() {
        String nome = cadastroView.getNome();
        String email = cadastroView.getEmail();
        String senha = cadastroView.getSenha();
        String tipo = cadastroView.getTipo();

        // Definir tipo
        String tipoUsuario = tipo.equals("Aluno") ? "A" : "S";

        // Lógica para cadastrar aluno ou supervisor com tipoUsuario
        // Por exemplo: alunoDAO.cadastrar(new Aluno(nome, email, senha, tipoUsuario));
        // ou supervisorDAO.cadastrar(new Supervisor(nome, email, senha, tipoUsuario));
    }

    private void voltar() {
        // Lógica para voltar à tela anterior
    }
}

