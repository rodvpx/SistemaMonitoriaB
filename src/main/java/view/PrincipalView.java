package view;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.*;

public class PrincipalView extends BasePanel {

    private StyleButton botaoLogin;
    private StyleButton botaoCadastro;
    private StyleButton botaoSair;

    public PrincipalView() {
        criarPainelPrincipal();
    }

    private void criarPainelPrincipal() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurações iniciais do GridBagConstraints
        gbc.insets = new Insets(10, 200, 10, 200);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.weightx = 1.0;

        // Label de título
        JLabel tituloLabel = new JLabel("SISTEMA DE MONITORIA");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        add(tituloLabel, gbc);

        // Botão Login
        botaoLogin = new StyleButton("Login"); // Atribui ao campo da classe
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(botaoLogin, gbc);

        // Botão Cadastro
        botaoCadastro = new StyleButton("Cadastro"); // Atribui ao campo da classe
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(botaoCadastro, gbc);

        // Botão Sair
        botaoSair = new StyleButton("Sair"); // Atribui ao campo da classe
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(botaoSair, gbc);
    }

    public StyleButton getBotaoLogin() {
        return botaoLogin;
    }

    public StyleButton getBotaoCadastro() {
        return botaoCadastro;
    }

    public StyleButton getBotaoSair() {
        return botaoSair;
    }
}
