package GUI;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import DTO.Aluno;

public class AppGUI extends JFrame {

    private Aluno alunoLogado; // Para armazenar o aluno logado

    public AppGUI() {
        setTitle("Sistema de Monitoria");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        inicializarTelaPrincipal();
    }

    private void inicializarTelaPrincipal() {
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel tituloLabel = new JLabel("SISTEMA DE MONITORIA");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 3;
        gbc.insets = new Insets(10, 10, 20, 10); // Margens
        painel.add(tituloLabel, gbc);

        JButton botaoLogin = new JButton("Login");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 5); // Margens
        painel.add(botaoLogin, gbc);

        JButton botaoCadastro = new JButton("Cadastro");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 10, 10); // Margens
        painel.add(botaoCadastro, gbc);

        JButton botaoSair = new JButton("Sair");
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 10); // Margens
        painel.add(botaoSair, gbc);

        botaoLogin.addActionListener(e -> mostrarTelaLogin());
        botaoCadastro.addActionListener(e -> mostrarTelaCadastro());
        botaoSair.addActionListener(e -> System.exit(0));

        add(painel);
        setVisible(true);
    }

    private void mostrarTelaLogin() {
        JFrame telaLogin = new JFrame("Login");
        telaLogin.setSize(300, 200);
        telaLogin.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaLogin.setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel emailLabel = new JLabel("Email:");
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(10, 10, 5, 5); // Margens
        painel.add(emailLabel, gbc);

        JTextField emailInput = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(10, 5, 5, 10); // Margens
        painel.add(emailInput, gbc);

        JLabel senhaLabel = new JLabel("Senha:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.insets = new Insets(5, 10, 10, 5); // Margens
        painel.add(senhaLabel, gbc);

        JPasswordField senhaInput = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.insets = new Insets(5, 5, 10, 10); // Margens
        painel.add(senhaInput, gbc);

        JButton botaoLogin = new JButton("Login");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 0, 0, 0); // Margens
        painel.add(botaoLogin, gbc);

        JButton botaoVoltar = new JButton("Voltar");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 0, 0); // Margens
        painel.add(botaoVoltar, gbc);

        botaoLogin.addActionListener(e -> {
            String email = emailInput.getText();
            String senha = new String(senhaInput.getPassword());

            Aluno aluno = new Aluno(null, null, email, senha);

            try {
                String resultadoLogin = aluno.login(email, senha);

                // Verifica o resultado do login
                if ("sucesso".equals(resultadoLogin)) {
                    alunoLogado = aluno; // Armazena o aluno logado
                    mostrarTelaPrincipalAluno(); // Mostra a tela principal do aluno
                    telaLogin.dispose(); // Fecha a janela de login após o login bem-sucedido
                } else if ("falha".equals(resultadoLogin)) {
                    mostrarMensagem("Falha no login. Verifique suas credenciais.", "Login", JOptionPane.ERROR_MESSAGE);
                } else {
                    mostrarMensagem("Erro desconhecido ao tentar realizar o login.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                mostrarMensagem("Erro ao tentar realizar o login.", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        botaoVoltar.addActionListener(e -> {
            telaLogin.dispose(); // Fecha a janela de login ao clicar em voltar
        });

        telaLogin.add(painel);
        telaLogin.setVisible(true);
    }

    private void mostrarTelaCadastro() {
        JFrame telaCadastro = new JFrame("Cadastro");
        telaCadastro.setSize(400, 300);
        telaCadastro.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaCadastro.setLocationRelativeTo(null);

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

        botaoCadastrarAluno.addActionListener(e -> mostrarTelaCadastroAluno());
        botaoCadastrarSupervisor.addActionListener(e -> mostrarTelaCadastroSupervisor());

        botaoVoltar.addActionListener(e -> {
            telaCadastro.dispose(); // Fecha a janela de cadastro ao clicar em voltar
        });

        telaCadastro.add(painel);
        telaCadastro.setVisible(true);
    }

    private void mostrarTelaCadastroAluno() {
        JFrame telaCadastroAluno = new JFrame("Cadastro de Aluno");
        telaCadastroAluno.setSize(400, 300);
        telaCadastroAluno.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaCadastroAluno.setLocationRelativeTo(null);

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
            telaCadastroAluno.dispose(); // Fecha a janela de cadastro de aluno ao clicar em voltar
        });

        telaCadastroAluno.add(painel);
        telaCadastroAluno.setVisible(true);
    }

    private void mostrarTelaCadastroSupervisor() {
        JFrame telaCadastroSupervisor = new JFrame("Cadastro de Supervisor");
        telaCadastroSupervisor.setSize(400, 300);
        telaCadastroSupervisor.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaCadastroSupervisor.setLocationRelativeTo(null);

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
            String cpf = cpfInput.getText();
            String nome = nomeInput.getText();

            // Lógica para cadastrar supervisor
            // Exemplo: supervisorService.cadastrarSupervisor(cpf, nome);
            mostrarMensagem("Supervisor cadastrado com sucesso!", "Cadastro de Supervisor", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoVoltar.addActionListener(e -> {
            telaCadastroSupervisor.dispose(); // Fecha a janela de cadastro de supervisor ao clicar em voltar
        });

        telaCadastroSupervisor.add(painel);
        telaCadastroSupervisor.setVisible(true);
    }

    private void mostrarTelaPrincipalAluno() {
        JFrame telaPrincipal = new JFrame("Painel do Aluno");
        telaPrincipal.setSize(600, 400);
        telaPrincipal.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        telaPrincipal.setLocationRelativeTo(null);

        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel tituloLabel = new JLabel("PAINEL DO ALUNO");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 20, 10); // Margens
        painel.add(tituloLabel, gbc);

        JButton botaoHorarios = new JButton("Horários");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 5); // Margens
        painel.add(botaoHorarios, gbc);

        JButton botaoMonitores = new JButton("Monitores");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 10, 10); // Margens
        painel.add(botaoMonitores, gbc);

        JButton botaoDisciplinas = new JButton("Disciplinas");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 5); // Margens
        painel.add(botaoDisciplinas, gbc);

        JButton botaoMonitorias = new JButton("Monitorias");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 10, 10); // Margens
        painel.add(botaoMonitorias, gbc);

        JButton botaoSolicitarMonitoria = new JButton("Solicitar Monitoria");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 0, 10); // Margens
        painel.add(botaoSolicitarMonitoria, gbc);

        JButton botaoSair = new JButton("Sair");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Margens
        painel.add(botaoSair, gbc);

        botaoHorarios.addActionListener(e -> {
            // Implemente a lógica para mostrar os horários do aluno
            mostrarMensagem("Mostrar horários do aluno", "Horários", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoMonitores.addActionListener(e -> {
            // Implemente a lógica para mostrar os monitores disponíveis
            mostrarMensagem("Mostrar monitores disponíveis", "Monitores", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoDisciplinas.addActionListener(e -> {
            // Implemente a lógica para mostrar as disciplinas do aluno
            mostrarMensagem("Mostrar disciplinas do aluno", "Disciplinas", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoMonitorias.addActionListener(e -> {
            // Implemente a lógica para mostrar as monitorias do aluno
            mostrarMensagem("Mostrar monitorias do aluno", "Monitorias", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoSolicitarMonitoria.addActionListener(e -> {
            // Implemente a lógica para solicitar monitoria
            mostrarMensagem("Solicitar monitoria", "Solicitar Monitoria", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoSair.addActionListener(e -> {
            telaPrincipal.dispose(); // Fecha a janela do painel do aluno ao clicar em sair
        });

        telaPrincipal.add(painel);
        telaPrincipal.setVisible(true);
    }

    private void mostrarMensagem(String mensagem, String titulo, int tipo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, tipo);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            new AppGUI();
        });
    }
}