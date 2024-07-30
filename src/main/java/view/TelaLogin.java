package view;

import controller.Aluno;
import controller.Supervisor;
import controller.Usuario;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

import static view.AppGUI.mostrarMensagem;

public class TelaLogin extends BasePanel {
    private ScreenManager screenManager;
    private static Aluno alunoLogado; // Para armazenar o aluno logado

    public TelaLogin(ScreenManager screenManager) {
        super();
        this.screenManager = screenManager;
        setLayout(new GridBagLayout());
        criarPainelLogin();
    }

    public void criarPainelLogin() {
        GridBagConstraints gbc = new GridBagConstraints();

        ImageIcon originalIcon = new ImageIcon("Img/user-286.png");
        Image originalImage = originalIcon.getImage();
        Image scaledImage = originalImage.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);
        JLabel userLabel = new JLabel(scaledIcon);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.NORTH;
        add(userLabel, gbc);

        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1; // Resetar a largura da coluna
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.LINE_END;// Ancorar à direita
        gbc.insets = new Insets(0, 5, 5, 0);
        add(emailLabel, gbc);

        JTextField emailInput = new JTextField(15);
        emailInput.setFont(new Font("Arial", Font.PLAIN, 20));
        emailInput.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(0, -30, 5, 0);// Ancorar à esquerda
        add(emailInput, gbc);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 5, 5, 0);
        add(senhaLabel, gbc);

        JPasswordField senhaInput = new JPasswordField(15);
        senhaInput.setFont(new Font("Arial", Font.PLAIN, 20));
        senhaInput.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.LINE_START;//
        gbc.insets = new Insets(0, -30, 5, 0);
        add(senhaInput, gbc);

        StyleButton botaoLogin = new StyleButton("Login");
        botaoLogin.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoLogin.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(botaoLogin, gbc);

        StyleButton botaoVoltar = new StyleButton("Voltar");
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(botaoVoltar, gbc);

        botaoLogin.addActionListener(e -> {
            String email = emailInput.getText();
            String senha = new String(senhaInput.getPassword());

            Usuario user = new Aluno(null, null, email, senha);

            try {
                Supervisor supervisor = user.login(email, senha);

                if (supervisor != null) {

                    if ("S".equals(supervisor.getTipo())) { // Assumindo que há um método getTipo() no Supervisor
                        screenManager.showScreen(new TelaSupervisor(screenManager, supervisor));
                    } else {
                        Aluno aluno = new Aluno(null, null, email, senha);
                        screenManager.showScreen(new TelaAluno(screenManager));
                    }
                } else {
                    mostrarMensagem("Falha no login", "Erro:", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                mostrarMensagem("Erro ao tentar realizar o login.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoVoltar.addActionListener(e -> {
            screenManager.showScreen(new TelaInicial(screenManager));
        });
    }
}