package controller;

import dao.*;
import model.Disciplina;
import model.Horario;
import model.Local;
import view.SupervisorView;
import model.Supervisor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SupervisorController implements ActionListener {

    private PrincipalController principalController;
    private SupervisorView view;
    private Supervisor supervisor;

    public SupervisorController(SupervisorView view, PrincipalController principalController) {
        this.view = view;
        this.principalController = principalController; // Corrigido aqui
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        switch (actionCommand) {
            case "Monitorias":
                view.mostrarMonitorias(MonitoriaDao.buscarTodasMonitorias());
                break;
            case "Monitores":
                view.mostrarMonitores(MonitorDao.mostrarMonitores());
                break;
            case "Disciplinas":
                try {
                    view.mostrarDisciplinas(DisciplinaDao.mostrarDisciplina());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Locais":
                try {
                    view.mostrarLocais(LocalDao.mostrarLocal());
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                break;
            case "Sair":
                if (principalController != null) {
                    principalController.mostrarPrincipalView();
                }
                Window window = SwingUtilities.getWindowAncestor(view);
                if (window instanceof JFrame) {
                    ((JFrame) window).dispose();
                }
                break;

            default:
                JOptionPane.showMessageDialog(view, "Ação não reconhecida: " + actionCommand, "Erro", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public void criarMonitoria(String disciplinaNome, String dia, String horarioStr, String salaNome, String monitorNome) throws SQLException {
        String codDisciplina = Integer.toString(DisciplinaDao.obterCodigoDisciplina(disciplinaNome));
        Local local = LocalDao.obterLocal(salaNome);
        int idSupervisor = SupervisorDao.obterIdSupervisor(supervisor.getMatricula());
        int idMonitor = MonitorDao.obterIdMonitor(monitorNome);

        Disciplina disciplina = new Disciplina(disciplinaNome, codDisciplina);
        Horario horario = new Horario(dia, horarioStr);

        MonitoriaDao.criarMonitoria(disciplina, horario, local, idMonitor, idSupervisor);
    }

    public void excluirMonitoria() {
        // Implementar lógica para excluir monitoria
    }

    public void promoverMonitor() {
        // Implementar lógica para promover monitor
    }

    public void excluirMonitor() {
        // Implementar lógica para excluir monitor
    }

    public void adicionarMonitoria() {
        view.adicionarMonitoria();
    }
}
