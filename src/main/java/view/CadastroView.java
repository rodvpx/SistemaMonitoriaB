package view;

import javax.swing.*;
import java.awt.*;

public class CadastroView extends BasePanel {
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField senhaField;
    private JComboBox<String> tipoComboBox;
    private JButton botaoCadastrar;
    private JButton botaoVoltar;

    public CadastroView() {
        criarPainelCadastro();
    }

    private void criarPainelCadastro() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel tituloLabel = new JLabel("CADASTRO DE USUÁRIO");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 20, 10);
        add(tituloLabel, gbc);

        JLabel nomeLabel = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        gbc.insets = new Insets(5, 5, 5, 5);
        add(nomeLabel, gbc);

        nomeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(nomeField, gbc);

        JLabel emailLabel = new JLabel("E-mail:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        add(emailField, gbc);

        JLabel senhaLabel = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(senhaLabel, gbc);

        senhaField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        add(senhaField, gbc);

        JLabel tipoLabel = new JLabel("Tipo:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(tipoLabel, gbc);

        tipoComboBox = new JComboBox<>(new String[] {"Aluno", "Supervisor"});
        gbc.gridx = 1;
        gbc.gridy = 4;
        add(tipoComboBox, gbc);

        botaoCadastrar = new JButton("Cadastrar");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 0, 10);
        add(botaoCadastrar, gbc);

        botaoVoltar = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);
        add(botaoVoltar, gbc);

    }

    public String getNome() {
        return nomeField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getSenha() {
        return senhaField.getText();
    }

    public String getTipo() {
        return tipoComboBox.getSelectedItem().toString();
    }

    public JButton getBotaoCadastrar() {
        return botaoCadastrar;
    }

    public JButton getBotaoVoltar() {
        return botaoVoltar;
    }
}
