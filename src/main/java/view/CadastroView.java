package view;

import javax.swing.*;
import java.awt.*;

public class CadastroView extends BasePanel {
    private JTextField nomeField;
    private JTextField emailField;
    private JTextField matriculaField;
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

        // Configurações gerais
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // Título
        JLabel tituloLabel = new JLabel("CADASTRO DE USUÁRIO");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 20, 10);
        add(tituloLabel, gbc);

        // Tipo
        JLabel tipoLabel = new JLabel("Tipo:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.EAST;
        add(tipoLabel, gbc);

        tipoComboBox = new JComboBox<>(new String[] {"Aluno", "Supervisor"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(tipoComboBox, gbc);

        // Matrícula
        JLabel matriculaLabel = new JLabel("Matrícula:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(matriculaLabel, gbc);

        matriculaField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        add(matriculaField, gbc);

        // Nome
        JLabel nomeLabel = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(nomeLabel, gbc);

        nomeField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        add(nomeField, gbc);

        // E-mail
        JLabel emailLabel = new JLabel("E-mail:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(emailLabel, gbc);

        emailField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.WEST;
        add(emailField, gbc);

        // Senha
        JLabel senhaLabel = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        add(senhaLabel, gbc);

        senhaField = new JTextField(20);
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.WEST;
        add(senhaField, gbc);

        // Botão Cadastrar
        botaoCadastrar = new StyleButton("Cadastrar");
        botaoCadastrar.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 5, 10); // Ajuste a margem inferior
        add(botaoCadastrar, gbc);

        // Botão Voltar
        botaoVoltar = new StyleButton("Voltar");
        botaoVoltar.setPreferredSize(new Dimension(150, 40));
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 10, 10, 10); // Ajuste a margem inferior
        add(botaoVoltar, gbc);
    }

    public String getMatricula(){
        return matriculaField.getText();
    }

    public String getNome() {
        return nomeField.getText();
    }

    public String getEmail() {
        return emailField.getText();
    }

    public String getTipo() {
        if (tipoComboBox.getSelectedItem().toString().equals("Aluno")) {
            return "A";
        } else if (tipoComboBox.getSelectedItem().toString().equals("Supervisor")) {
            return "S";
        } else {
            throw new IllegalStateException("Tipo de usuário inválido selecionado.");
        }
    }


    public String getSenha() {
        return senhaField.getText();
    }

    public JButton getBotaoCadastrar() {
        return botaoCadastrar;
    }

    public JButton getBotaoVoltar() {
        return botaoVoltar;
    }
}
