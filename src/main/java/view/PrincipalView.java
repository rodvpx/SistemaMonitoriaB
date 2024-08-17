package view;

import java.awt.*;
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

        JLabel tituloLabel = new JLabel("SISTEMA DE MONITORIA");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        tituloLabel.setHorizontalAlignment(SwingConstants.CENTER);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.PAGE_START; // Centraliza horizontalmente
        add(tituloLabel, gbc);

        // Carregar e redimensionar a imagem
        ImageIcon imagemIcon = new ImageIcon(getClass().getResource("/Img/icon.png")); // Ajuste o caminho para sua imagem
        Image imagem = imagemIcon.getImage(); // Obtém a imagem
        Image imagemRedimensionada = imagem.getScaledInstance(185, 185, Image.SCALE_SMOOTH); // Redimensiona a imagem
        imagemIcon = new ImageIcon(imagemRedimensionada); // Cria um novo ImageIcon com a imagem redimensionada

        JLabel imagemLabel = new JLabel(imagemIcon);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 3;
        gbc.anchor = GridBagConstraints.CENTER; // Centraliza horizontalmente
        add(imagemLabel, gbc);

        // Botão Login
        botaoLogin = new StyleButton("Login"); // Atribui ao campo da classe
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        add(botaoLogin, gbc);

        // Botão Cadastro
        botaoCadastro = new StyleButton("Cadastro"); // Atribui ao campo da classe
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(botaoCadastro, gbc);

        // Botão Sair
        botaoSair = new StyleButton("Sair"); // Atribui ao campo da classe
        gbc.gridx = 0;
        gbc.gridy = 4;
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
