package controller;

import dao.*;
import model.*;
import view.SupervisorView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

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
        util.IconUtil.setIcon(frame);
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
        view.mostrarMonitorias(MonitoriaDao.buscarTodasMonitorias());
    }

    public void excluirMonitoria(Monitoria monitoria) throws SQLException {
       MonitoriaDao.excluirMonitoria(monitoria);
        view.mostrarMonitorias(MonitoriaDao.buscarTodasMonitorias());
    }

    public void promoverAluno(String matricula) throws SQLException {
       Integer idAluno = AlunoDao.obterIdAluno(matricula);
        if(AlunoDao.promoverAluno(idAluno)){
            JOptionPane.showMessageDialog(view, "Aluno promovido com sucesso!");
            view.mostrarMonitores(MonitorDao.mostrarMonitores());
        }else{
            JOptionPane.showMessageDialog(view, "Erro ao promover aluno!");
        }

    }

    public void excluirMonitor(String matricula) throws SQLException {
        if(MonitorDao.excluirMonitor(matricula)){
            JOptionPane.showMessageDialog(view, "Monitor excluido com sucesso!");
            view.mostrarMonitores(MonitorDao.mostrarMonitores());
        }else{
            JOptionPane.showMessageDialog(view, "Erro ao excluir monitor!");
        }

    }

    public void adicionarMonitoria() {
        view.adicionarMonitoria();
    }

    public void adicionarDisciplina(Disciplina disciplina) throws SQLException {

        if(DisciplinaDao.verificarCodigoExistente(disciplina.getCodigo())){
            JOptionPane.showMessageDialog(view, "Erro codigo já existente");
            view.mostrarDisciplinas(DisciplinaDao.mostrarDisciplina());
        } else {
            DisciplinaDao.adicionarDisciplina(disciplina);
            view.mostrarDisciplinas(DisciplinaDao.mostrarDisciplina());

        }

    }

    public void excluirDisciplina(String codigo) throws SQLException {

        if(DisciplinaDao.excluirDisciplina(codigo)){
            JOptionPane.showMessageDialog(view, "Disciplina excluido com sucesso!");
            view.mostrarDisciplinas(DisciplinaDao.mostrarDisciplina());
        }else {
            JOptionPane.showMessageDialog(view, "Erro ao excluir disciplina!");
        }
    }

    public void adicionarLocal(Local local) throws SQLException {

        if(LocalDao.verificarLocalExiste(local.getSala())){
            JOptionPane.showMessageDialog(view, "Erro local já existente");
            view.mostrarLocais(LocalDao.mostrarLocal());
        }else {
            LocalDao.adicionaLocal(local);
            view.mostrarLocais(LocalDao.mostrarLocal());
        }
    }

    public void excluirLocal(int id) throws SQLException {

        if(LocalDao.excluirLocal(id)){
            JOptionPane.showMessageDialog(view, "Local excluido com sucesso!");
            view.mostrarLocais(LocalDao.mostrarLocal());
        }else {
            JOptionPane.showMessageDialog(view, "Erro ao excluir local!");
        }
    }
}
