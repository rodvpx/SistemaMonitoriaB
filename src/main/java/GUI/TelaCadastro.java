package GUI;

import javax.swing.*;
import java.awt.*;

import static GUI.AppGUI.mostrarMensagem;

public class TelaCadastro {

    private ScreenManager screenManager;
    private JPanel painelPrincipal;
    private CardLayout cardLayout;

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
            cardLayout.show(painelPrincipal, "PainelCadastroAluno");
        });

        botaoCadastrarSupervisor.addActionListener(e -> {
            cardLayout.show(painelPrincipal, "PainelCadastroSupervisor");
        });

        botaoVoltar.addActionListener(e -> {
            screenManager.showScreen(new TelaInicial(screenManager).criarPainelInicial());
        });

        return painel;
    }

        public JPanel criarPainelCadastroAluno() {
            JPanel painel = new JPanel(new GridBagLayout());
            GridBagConstraints gbc = new GridBagConstraints();

        JLabel tituloLabel = new JLabel("CADASTRO DE ALUNO");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 20, 10); // Margens
        painel.add(tituloLabel, gbc);

        JLabel matriculaLabel = new JLabel("Matrícula:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 10, 5, 5); // Margens
        painel.add(matriculaLabel, gbc);

        JTextField matriculaInput = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 5, 10); // Margens
        painel.add(matriculaInput, gbc);

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

        JButton botaoCadastrar = new JButton("Cadastrar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 0, 0); // Margens
        painel.add(botaoCadastrar, gbc);

        JButton botaoVoltar = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 0, 0); // Margens
        painel.add(botaoVoltar, gbc);

        botaoCadastrar.addActionListener(e -> {
            String matricula = matriculaInput.getText();
            String nome = nomeInput.getText();

            // Lógica para cadastrar aluno
            // Exemplo: alunoService.cadastrarAluno(matricula, nome);
            mostrarMensagem("Aluno cadastrado com sucesso!", "Cadastro de Aluno", JOptionPane.INFORMATION_MESSAGE);
        });

            botaoVoltar.addActionListener(e -> {
                screenManager.showScreen(new TelaInicial(screenManager).criarPainelInicial());
            });

        return painel;
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

        JButton botaoCadastrar = new JButton("Cadastrar");
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 0, 0, 0); // Margens
        painel.add(botaoCadastrar, gbc);

        JButton botaoVoltar = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(20, 10, 0, 0); // Margens
        painel.add(botaoVoltar, gbc);


        botaoCadastrar.addActionListener(e -> {
            // Lógica para cadastrar supervisor
            // Exemplo: supervisorService.cadastrarSupervisor(cpfInput.getText(), nomeInput.getText());
            mostrarMensagem("Supervisor cadastrado com sucesso!", "Cadastro de Supervisor", JOptionPane.INFORMATION_MESSAGE);
        });
        botaoVoltar.addActionListener(e -> {
            screenManager.showScreen(new TelaInicial(screenManager).criarPainelInicial());
        });

        return painel;
    }
}
