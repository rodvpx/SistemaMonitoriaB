package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends BasePanel {

    private JTextField emailField;
    private JPasswordField senhaField;
    private JButton botaoLogin;
    private JButton botaoVoltar;

    public LoginView() {
        criarPainelLogin();
    }

    public void criarPainelLogin() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        ImageIcon originalIcon = new ImageIcon(getClass().getResource("/Img/user-286.png"));
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
        gbc.fill = GridBagConstraints.LINE_END; // Ancorar à direita
        gbc.insets = new Insets(0, 5, 5, 0);
        add(emailLabel, gbc);

        emailField = new JTextField(15);
        emailField.setFont(new Font("Arial", Font.PLAIN, 20));
        emailField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.LINE_START; // Ancorar à esquerda
        gbc.insets = new Insets(0, 0, 5, 0); // Ajuste as margens conforme necessário
        add(emailField, gbc);

        JLabel senhaLabel = new JLabel("Senha:");
        senhaLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(0, 5, 5, 0);
        add(senhaLabel, gbc);

        senhaField = new JPasswordField(15);
        senhaField.setFont(new Font("Arial", Font.PLAIN, 20));
        senhaField.setPreferredSize(new Dimension(200, 30));
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.LINE_START; // Ancorar à esquerda
        gbc.insets = new Insets(0, 0, 5, 0);
        add(senhaField, gbc);

        botaoLogin = new StyleButton("Login"); // Associando a variável de instância
        botaoLogin.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoLogin.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(botaoLogin, gbc);

        botaoVoltar = new StyleButton("Voltar"); // Associando a variável de instância
        botaoVoltar.setFont(new Font("Arial", Font.PLAIN, 20));
        botaoVoltar.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0);
        add(botaoVoltar, gbc);
    }

    public JTextField getEmailField() {
        return emailField;
    }

    public JPasswordField getSenhaField() {
        return senhaField;
    }

    public JButton getBotaoLogin() {
        return botaoLogin;
    }

    public JButton getBotaoVoltar() {
        return botaoVoltar;
    }
}
