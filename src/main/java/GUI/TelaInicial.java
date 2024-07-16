package GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaInicial {
    private ScreenManager screenManager;

    public TelaInicial(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public JPanel criarPainelInicial() {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        // Configurações iniciais do GridBagConstraints
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.CENTER;

        // Label de título
        JLabel tituloLabel = new JLabel("SISTEMA DE MONITORIA");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        painel.add(tituloLabel, gbc);

        // Botão Login
        JButton botaoLogin = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        painel.add(botaoLogin, gbc);

        // Botão Cadastro
        JButton botaoCadastro = new JButton("Cadastro");
        gbc.gridx = 1;
        gbc.gridy = 1;
        painel.add(botaoCadastro, gbc);

        // Botão Sair
        JButton botaoSair = new JButton("Sair");
        gbc.gridx = 2;
        gbc.gridy = 1;
        painel.add(botaoSair, gbc);

        // Adiciona ações aos botões
        botaoLogin.addActionListener(e -> screenManager.showScreen(new TelaLogin(screenManager).criarPainelLogin()));
        botaoCadastro.addActionListener(e -> screenManager.showScreen(new TelaCadastro(screenManager).criarPainelCadastro()));
        botaoSair.addActionListener(e -> System.exit(0));

        return painel;
    }
}
