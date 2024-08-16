package controller;

import dao.AlunoDao;
import dao.MonitoriaDao;
import model.Aluno;
import model.Monitoria;
import view.AlunoView;
import view.StyleButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class AlunoController implements ActionListener {

    private Aluno aluno;
    private PrincipalController principalController;
    private AlunoView view;
    private JButton botaoSelecionado;

    public AlunoController(Aluno aluno, PrincipalController principalcontroller) {
        this.aluno = aluno;
        this.principalController = principalcontroller;
        this.view = new AlunoView(aluno, this);
    }

    public void mostrarView() {
        JFrame frame = new JFrame("SISTEMA MONITORIA - Painel do Aluno");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        util.IconUtil.setIcon(frame);
        frame.add(view);
        frame.setSize(1080, 720);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Reverter o estilo do botão selecionado, se houver
        if (botaoSelecionado != null) {
            ((StyleButton) botaoSelecionado).setSelected(false);
        }

        String actionCommand = e.getActionCommand();

        switch (actionCommand) {
            case "Monitorias":
                view.mostrarMonitorias(MonitoriaDao.buscarTodasMonitorias());
                break;
            case "Minhas Monitorias":
                int idAluno = AlunoDao.obterIdAluno(aluno.getMatricula());
                try {
                    view.mostrarMinhasMonitorias(AlunoDao.buscarMinhasMonitorias(idAluno));
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Sair":
                if (principalController != null) {
                    principalController.mostrarPrincipalView(); // Mostra a tela principal
                }
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(view);
                if (frame != null) {
                    frame.dispose(); // Fecha a tela do aluno
                }
                // Reverter o estilo do botão selecionado ao fechar a tela
                if (botaoSelecionado != null) {
                    ((StyleButton) botaoSelecionado).setSelected(false);
                }
                break;

            default:
                JOptionPane.showMessageDialog(view, "Ação não reconhecida: " + actionCommand, "Erro", JOptionPane.ERROR_MESSAGE);
                break;
        }

        // Destacar o botão clicado
        JButton botao = (JButton) e.getSource();
        botaoSelecionado = botao;
        ((StyleButton) botaoSelecionado).setSelected(true);
    }

    public void inscreverMonitoria(Monitoria monitoria) throws SQLException {
        // Obter o ID do aluno
        int alunoId = AlunoDao.obterIdAluno(aluno.getMatricula()); // Supondo que o objeto aluno já está disponível

        // Verificar se o aluno já está inscrito na monitoria
        if (AlunoDao.isAlunoInscrito(alunoId, monitoria.getId())) {
            JOptionPane.showMessageDialog(view, "Você já está inscrito nesta monitoria.");
        } else {
            // Agora inscreva o aluno na monitoria selecionada
            if (AlunoDao.inscreverMonitoria(alunoId, monitoria.getId())) {
                JOptionPane.showMessageDialog(view, "Inscrição feita com sucesso");
                view.mostrarMonitorias(MonitoriaDao.buscarTodasMonitorias());
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao fazer inscrição");
            }
        }
    }


    public void cancelarInscricao(Monitoria monitoria) throws SQLException {
        if(AlunoDao.cancelarInscricao(monitoria.getId(), AlunoDao.obterIdAluno(aluno.getMatricula()))){
            JOptionPane.showMessageDialog(view, "Cancelamento da inscrição feita com sucesso");
            int idAluno = AlunoDao.obterIdAluno(aluno.getMatricula());
            view.mostrarMinhasMonitorias(AlunoDao.buscarMinhasMonitorias(idAluno));
        }else{
            JOptionPane.showMessageDialog(view, "Erro ao cancelar inscricao");
        }
    }
}
