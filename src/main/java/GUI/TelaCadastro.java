package GUI;

import javax.swing.*;
import java.awt.*;

import static GUI.AppGUI.mostrarMensagem;

public class TelaCadastro {

    private ScreenManager screenManager;

    public TelaCadastro(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public JPanel criarPainelCadastro() {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel tituloLabel = new JLabel("CADASTRO DE USUÁRIO");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 20, 10); // Margens
        painel.add(tituloLabel, gbc);

        JButton botaoCadastrarAluno = new JButton("Cadastrar Aluno");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 5); // Margens
        painel.add(botaoCadastrarAluno, gbc);

        JButton botaoCadastrarSupervisor = new JButton("Cadastrar Supervisor");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 10, 10); // Margens
        painel.add(botaoCadastrarSupervisor, gbc);

        JButton botaoVoltar = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 0, 10); // Margens
        painel.add(botaoVoltar, gbc);

        botaoCadastrarAluno.addActionListener(e -> {
            screenManager.showScreen(new TelaCadastroAluno(screenManager).criarPainelCadastroAluno());
        });

        botaoCadastrarSupervisor.addActionListener(e -> {
            screenManager.showScreen(new TelaCadastroSupervisor(screenManager).criarPainelCadastroSupervisor());
        });

        botaoVoltar.addActionListener(e -> { screenManager.showScreen(new TelaInicial(screenManager));
        });

       return painel;
    }
}