package controller;

import dao.AlunoDao;
import dao.MonitoriaDao;
import model.Monitor;
import model.Monitoria;
import view.MonitorView;
import view.StyleButton;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class MonitorController implements ActionListener {

    private Monitor monitor;
    private PrincipalController principalController;
    private MonitorView view;
    private JButton botaoSelecionado;

    public MonitorController(Monitor monitor, PrincipalController principalController) {
        this.monitor = monitor;
        this.principalController = principalController;
        this.view = new MonitorView(monitor, this);
    }

    public void mostrarView() {

        JFrame frame = new JFrame("SISTEMA MONITORIA - Painel do Monitor");
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
                int idMonitor = AlunoDao.obterIdAluno(monitor.getMatricula());
                try {
                    view.mostrarMinhasMonitorias(AlunoDao.buscarMinhasMonitorias(idMonitor));
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

        int monitorId = AlunoDao.obterIdAluno(monitor.getMatricula());

        // Verificar se o aluno já está inscrito na monitoria
        if (AlunoDao.isAlunoInscrito(monitorId, monitoria.getId())) {
            JOptionPane.showMessageDialog(view, "Você já está inscrito nesta monitoria.");
        } else {
            // Agora inscreva o aluno na monitoria selecionada
            if (AlunoDao.inscreverMonitoria(monitorId, monitoria.getId())) {
                JOptionPane.showMessageDialog(view, "Inscrição feita com sucesso");
                view.mostrarMonitorias(MonitoriaDao.buscarTodasMonitorias());
            } else {
                JOptionPane.showMessageDialog(view, "Erro ao fazer inscrição");
            }
        }
    }

    public void cancelarInscricao(Monitoria monitoria) throws SQLException {

        if (AlunoDao.cancelarInscricao(monitoria.getId(), AlunoDao.obterIdAluno(monitor.getMatricula()))) {

            JOptionPane.showMessageDialog(view, "Cancelamento da inscrição feita com sucesso");
            int idMonitor = AlunoDao.obterIdAluno(monitor.getMatricula());
            view.mostrarMinhasMonitorias(AlunoDao.buscarMinhasMonitorias(idMonitor));

        } else {
            JOptionPane.showMessageDialog(view, "Erro ao cancelar inscricao");
        }
    }

    public void atualizarMonitoria(Monitoria monitoria) throws SQLException {

        if (MonitoriaDao.editarMonitoria(monitoria)) {
            JOptionPane.showMessageDialog(view, "Monitoria atualizada com sucesso!");
            view.mostrarMonitorias(MonitoriaDao.buscarTodasMonitorias());
        } else {
            JOptionPane.showMessageDialog(view, "Erro ao atualizar monitoria!");
        }
    }
}

