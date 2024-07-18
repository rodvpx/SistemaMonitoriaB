package GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class TelaInicial extends BasePanel {
    private ScreenManager screenManager;

    public TelaInicial(ScreenManager screenManager) {
        super();
        this.screenManager = screenManager;
        setLayout(new GridBagLayout());
        criarPainelInicial();
    }

    public void criarPainelInicial() {
        //JPanel painel = new JPanel(new GridBagLayout());
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
        StyleButton botaoLogin = new StyleButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(botaoLogin, gbc);

        // Botão Cadastro
        StyleButton botaoCadastro = new StyleButton("Cadastro");
        gbc.gridx = 0;
        gbc.gridy = 2;
        add(botaoCadastro, gbc);

        // Botão Sair
        StyleButton botaoSair = new StyleButton("Sair");
        gbc.gridx = 0;
        gbc.gridy = 3;
        add(botaoSair, gbc);

        // Adiciona ações aos botões
        botaoLogin.addActionListener(e -> { screenManager.showScreen(new TelaLogin(screenManager));});
        botaoCadastro.addActionListener(e -> screenManager.showScreen(new TelaCadastro(screenManager).criarPainelCadastro()));
        botaoSair.addActionListener(e -> System.exit(0));

        //return painel;
    }
}
