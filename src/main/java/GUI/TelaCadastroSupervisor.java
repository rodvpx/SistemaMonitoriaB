package GUI;

import javax.swing.*;
import java.awt.*;

public class TelaCadastroSupervisor {

    private ScreenManager screenManager;

    public TelaCadastroSupervisor(ScreenManager screenManager) {
        this.screenManager = screenManager;
    }

    public JPanel criarPainelCadastroSupervisor() {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel tituloLabel = new JLabel("CADASTRO DE SUPERVISOR");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 20, 10); // Margens
        painel.add(tituloLabel, gbc);

        JLabel cpfLabel = new JLabel("CPF:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 10, 5, 5); // Margens
        painel.add(cpfLabel, gbc);

        JTextField cpfInput = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 10); // Margens
        painel.add(cpfInput, gbc);

        JLabel nomeLabel = new JLabel("Nome:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 10, 5, 5); // Margens
        painel.add(nomeLabel, gbc);

        JTextField nomeInput = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 10); // Margens
        painel.add(nomeInput, gbc);

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 10, 5, 5); // Margens
        painel.add(emailLabel, gbc);

        JTextField emailInput = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 10); // Margens
        painel.add(emailInput, gbc);

        JLabel senhaLabel = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 10, 5, 5); // Margens
        painel.add(senhaLabel, gbc);

        JPasswordField senhaInput = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 10); // Margens
        painel.add(senhaInput, gbc);

        JButton botaoCadastrar = new JButton("Cadastrar");
        gbc.gridx = 1;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 5, 0, 10); // Margens
        painel.add(botaoCadastrar, gbc);

        JButton botaoVoltar = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 0, 5); // Margens
        painel.add(botaoVoltar, gbc);

        botaoCadastrar.addActionListener(e -> {
            String cpf = cpfInput.getText();
            String nome = nomeInput.getText();
            String email = emailInput.getText();
            String senha = new String(senhaInput.getPassword());

            // Lógica para cadastrar supervisor
            // Exemplo: supervisorService.cadastrarSupervisor(cpf, nome, email, senha);
            JOptionPane.showMessageDialog(null, "Supervisor cadastrado com sucesso!", "Cadastro de Supervisor", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoVoltar.addActionListener(e -> {
            screenManager.showScreen(new TelaCadastro(screenManager).criarPainelCadastro()); // Fecha a janela de login ao clicar em voltar
        });

        return painel;
    }
}
