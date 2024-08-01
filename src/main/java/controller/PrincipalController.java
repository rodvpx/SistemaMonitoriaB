package controller;

import view.LoginView;
import view.PrincipalView;
import view.CadastroView;

import javax.swing.*;

public class PrincipalController {

    private PrincipalView principalView;

    public PrincipalController(PrincipalView principalView) {
        this.principalView = principalView;
        inicializar();
    }

    private void inicializar() {
        principalView.getBotaoLogin().addActionListener(e -> abrirLoginView());
        principalView.getBotaoCadastro().addActionListener(e -> abrirCadastroView());
        principalView.getBotaoSair().addActionListener(e -> System.exit(0)); // Corrigido
    }

    private void abrirLoginView() {
        LoginView loginView = new LoginView();
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Corrigido
        loginFrame.add(loginView);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null); // Centraliza a janela
        loginFrame.setVisible(true);
    }

    private void abrirCadastroView() {
        CadastroView cadastroView = new CadastroView();
        JFrame cadastroFrame = new JFrame("Cadastro de Usuário");
        cadastroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        cadastroFrame.add(cadastroView);
        cadastroFrame.pack();
        cadastroFrame.setLocationRelativeTo(null); // Centraliza a janela
        cadastroFrame.setVisible(true);
    }
}
