package controller;

import dao.UsuarioDao;
import model.*;
import view.*;

import javax.swing.*;
import java.sql.SQLException;

import static dao.UsuarioDao.validacao;

public class PrincipalController {

    private PrincipalView principalView;
    private JFrame principalFrame;

    public PrincipalController(PrincipalView principalView, JFrame principalFrame) {
        this.principalView = principalView;
        this.principalFrame = principalFrame;
        inicializar();
    }

    public PrincipalController(PrincipalView principalView) {
        this.principalView = principalView;
        inicializar();
    }

    private void inicializar() {
        principalView.getBotaoLogin().addActionListener(e -> {
            try {
                abrirLoginView();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        principalView.getBotaoCadastro().addActionListener(e -> abrirCadastroView());
        principalView.getBotaoSair().addActionListener(e -> System.exit(0));
    }

    private void abrirLoginView() throws SQLException {
        LoginView loginView = new LoginView();
        JFrame loginFrame = new JFrame("Login");
        loginFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        loginFrame.add(loginView);
        loginFrame.pack();
        loginFrame.setLocationRelativeTo(null); // Centraliza a janela
        loginFrame.setVisible(true);

        loginView.getBotaoLogin().addActionListener(e -> {
            try {
                String email = loginView.getEmailField().getText();
                String senha = new String(loginView.getSenhaField().getPassword());
                validarLogin(email, senha);
                loginFrame.dispose();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        loginView.getBotaoVoltar().addActionListener(e -> loginFrame.dispose());
    }

    private void validarLogin(String email, String senha) throws SQLException {
        LoginResult result = UsuarioDao.login(email, senha);
        if (result != null) {
            Usuario usuario = result.getUsuario();
            String tipo = result.getTipo();

            switch (tipo) {
                case "S":
                    Supervisor supervisor = (Supervisor) usuario;
                    SupervisorController supController = new SupervisorController(supervisor, this);
                    supController.mostrarView();
                    fecharTelaPrincipal();
                    break;
                case "A":
                    Aluno aluno = (Aluno) usuario;
                    AlunoController controllerAluno = new AlunoController(aluno, this);
                    controllerAluno.mostrarView();
                    fecharTelaPrincipal();
                    break;
                case "M":
                    Monitor monitor = (Monitor) usuario;
                    MonitorController controller = new MonitorController(monitor, this);
                    controller.mostrarView();
                    fecharTelaPrincipal();
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Credenciais inválidas", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void abrirCadastroView() {
        CadastroView cadastroView = new CadastroView();

        JFrame cadastroFrame = new JFrame("Cadastro de Usuário");
        cadastroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        util.IconUtil.setIcon(cadastroFrame);
        cadastroFrame.add(cadastroView);
        cadastroFrame.pack();
        cadastroFrame.setLocationRelativeTo(null); // Centraliza a janela
        cadastroFrame.setVisible(true);

        cadastroView.getBotaoCadastrar().addActionListener(e -> {
            try {
                cadastrar(cadastroView);
                cadastroFrame.dispose(); // Fechar a janela de cadastro após o cadastro bem-sucedido
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });

        cadastroView.getBotaoVoltar().addActionListener(e -> cadastroFrame.dispose());
    }

    private void cadastrar(CadastroView cadastroView) throws SQLException {
        String matricula = cadastroView.getMatricula();
        String nome = cadastroView.getNome();
        String email = cadastroView.getEmail();
        String tipo = cadastroView.getTipo();
        String senha = cadastroView.getSenha();

        // Validar dados
        boolean dadosValidos = validacao(matricula, email, tipo);
        if (!dadosValidos) {
            JOptionPane.showMessageDialog(null, "Dados inválidos. Verifique matrícula e e-mail.", "Erro", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Se os dados forem válidos, continue com o cadastro
        if (UsuarioDao.cadastrarNovoUsuario(matricula, nome, email, tipo, senha)) {
            JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

            try {
                if (tipo.equals("A")) {
                    Aluno alu = new Aluno(matricula, nome, email, senha);
                    AlunoController controllerAluno = new AlunoController(alu, this);
                    controllerAluno.mostrarView();
                    fecharTelaPrincipal();
                } else if (tipo.equals("S")) {
                    Supervisor sup = new Supervisor(matricula, nome, email, senha);
                    SupervisorController controller = new SupervisorController(sup, this);
                    controller.mostrarView();
                    fecharTelaPrincipal();
                }
                fecharTelaPrincipal();
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Ocorreu um erro ao tentar abrir a tela: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public void mostrarPrincipalView() {
        if (principalFrame != null) {
            principalFrame.setVisible(true); // Torna a tela principal visível
        }
    }

    private void fecharTelaPrincipal() {
        if (principalFrame != null) {
            principalFrame.dispose();
        }
    }
}
