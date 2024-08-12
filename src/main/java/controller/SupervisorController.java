package controller;

import dao.*;
import model.Disciplina;
import model.Horario;
import model.Local;
import view.SupervisorView;
import model.Supervisor;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class SupervisorController implements ActionListener {

    private PrincipalController principalController;
    private SupervisorView view;
    private Supervisor supervisor;

    public SupervisorController(Supervisor supervisor, PrincipalController principalController) {
        this.supervisor = supervisor;
        this.principalController = principalController;
        this.view = new SupervisorView(supervisor, this); // Cria a view passando o controller
    }

    public void mostrarView() {
        JFrame frame = new JFrame("Supervisor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.add(view);
        frame.setSize(1080, 720);
        frame.setLocationRelativeTo(null); // Centraliza a janela
        frame.setVisible(true);
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
                    principalController.mostrarPrincipalView(); // Mostra a tela principal
                }
                JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(view);
                if (frame != null) {
                    frame.dispose(); // Fecha a tela do supervisor
                }
                break;

            default:
                JOptionPane.showMessageDialog(view, "Ação não reconhecida: " + actionCommand, "Erro", JOptionPane.ERROR_MESSAGE);
                break;
        }
    }

    public void criarMonitoriaCon(Disciplina disciplina, String dia, String horarioStr, String salaNome, String monitorNome) throws SQLException {

        Local local = LocalDao.obterLocal(salaNome);
        int idSupervisor = SupervisorDao.obterIdSupervisor(supervisor.getMatricula());
        int idMonitor = MonitorDao.obterIdMonitor(monitorNome);

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
