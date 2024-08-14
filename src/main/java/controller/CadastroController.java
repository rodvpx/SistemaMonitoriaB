package controller;

import dao.UsuarioDao;
import view.CadastroView;

import javax.swing.*;
import java.sql.SQLException;

import static dao.UsuarioDao.validacao;

public class CadastroController {

    private CadastroView cadastroView;

    public CadastroController(CadastroView cadastroView) {
        this.cadastroView = cadastroView;
        inicializar();
    }

    private void inicializar() {
        cadastroView.getBotaoCadastrar().addActionListener(e -> {
            try {
                cadastrar();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
        });
        cadastroView.getBotaoVoltar().addActionListener(e -> System.exit(0));
    }

    private void cadastrar() throws SQLException {
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
        UsuarioDao.cadastrarNovoUsuario(matricula, nome, email, tipo, senha);
        JOptionPane.showMessageDialog(null, "Cadastro realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
    }


}

