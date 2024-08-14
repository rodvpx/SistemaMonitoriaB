package controller;

import dao.UsuarioDao;
import model.*;
import view.LoginView;
import view.PrincipalView;
import view.CadastroView;

import javax.swing.*;
import java.sql.SQLException;

public class PrincipalController {

    private PrincipalView principalView;
    private JFrame principalFrame;

    public PrincipalController(PrincipalView principalView, JFrame principalFrame) {
        this.principalView = principalView;
        this.principalFrame = principalFrame;
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
                    // Abrir a tela do aluno
                    break;
                case "M":
                    Monitor monitor = (Monitor) usuario;
                    // Abrir a tela do monitor
                    break;
            }
        } else {
            JOptionPane.showMessageDialog(null, "Credenciais inválidas", "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }


    private void abrirCadastroView() {
        CadastroView cadastroView = new CadastroView();
        CadastroController cadastroController = new CadastroController(cadastroView); // Adiciona o controlador

        JFrame cadastroFrame = new JFrame("Cadastro de Usuário");
        cadastroFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        util.IconUtil.setIcon(cadastroFrame);
        cadastroFrame.add(cadastroView);
        cadastroFrame.pack();
        cadastroFrame.setLocationRelativeTo(null); // Centraliza a janela
        cadastroFrame.setVisible(true);
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
