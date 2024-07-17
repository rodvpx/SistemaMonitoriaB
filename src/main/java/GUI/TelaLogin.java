package GUI;

import DTO.Aluno;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static GUI.AppGUI.mostrarMensagem;

public class TelaLogin {

    private ScreenManager screenManager;
    private static Aluno alunoLogado; // Para armazenar o aluno logado

    public TelaLogin(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public JPanel criarPainelLogin() {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(10, 10, 5, 5); // Margens
        painel.add(emailLabel, gbc);

        JTextField emailInput = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 5, 5, 10); // Margens
        painel.add(emailInput, gbc);

        JLabel senhaLabel = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 10, 10, 5); // Margens
        painel.add(senhaLabel, gbc);

        JPasswordField senhaInput = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 10, 10); // Margens
        painel.add(senhaInput, gbc);

        JButton botaoLogin = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0); // Margens
        painel.add(botaoLogin, gbc);

        JButton botaoVoltar = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 0); // Margens
        painel.add(botaoVoltar, gbc);

        botaoLogin.addActionListener(e -> {
            String email = emailInput.getText();
            String senha = new String(senhaInput.getPassword());

            Aluno aluno = new Aluno(null, null, email, senha);

            try {
                String resultadoLogin = aluno.login(email, senha);

                // Verifica o resultado do login
                if ("sucesso".equals(resultadoLogin)) {
                    alunoLogado = aluno; // Armazena o aluno logado
                    screenManager.showScreen(new TelaAluno(screenManager).criarPainelALuno()); // Mostra a tela principal do aluno
                } else if ("falha".equals(resultadoLogin)) {
                    mostrarMensagem("Falha no login. Verifique suas credenciais.", "Login", JOptionPane.ERROR_MESSAGE);
                } else {
                    mostrarMensagem("Erro desconhecido ao tentar realizar o login.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                mostrarMensagem("Erro ao tentar realizar o login.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoVoltar.addActionListener(e -> { screenManager.showScreen(new TelaInicial(screenManager));});

        return painel;
    }
}
