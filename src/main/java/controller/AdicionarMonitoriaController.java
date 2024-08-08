package controller;
import dao.DisciplinaDao;
import dao.LocalDao;
import dao.MonitorDao;
import dao.MonitoriaDao;
import model.Disciplina;
import model.Horario;
import model.Local;
import model.Supervisor;
import view.AdicionarMonitoriaView;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AdicionarMonitoriaController {
    private Supervisor supervisor;
    private AdicionarMonitoriaView view;

    public AdicionarMonitoriaController(Supervisor supervisor, AdicionarMonitoriaView view) {
        this.supervisor = supervisor;
        this.view = view;
        initController();
    }

    private void initController() {
        view.getSalvarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                salvarMonitoria();
            }
        });

        view.getCancelarButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                view.dispose();
            }
        });
    }

    private void salvarMonitoria() {
        String disciplinaSelecionada = (String) view.getDisciplinaComboBox().getSelectedItem();
        String salaSelecionada = (String) view.getSalaComboBox().getSelectedItem();
        String monitorSelecionado = (String) view.getMonitorComboBox().getSelectedItem();
        String diaSelecionado = (String) view.getDiaComboBox().getSelectedItem();
        String horarioSelecionado = (String) view.getHorarioComboBox().getSelectedItem();

        if (disciplinaSelecionada.isEmpty() || salaSelecionada.isEmpty() || monitorSelecionado.isEmpty()
                || diaSelecionado.isEmpty() || horarioSelecionado.isEmpty()) {
            JOptionPane.showMessageDialog(view, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
        } else {
            String codDisciplina = Integer.toString(obterCodigoDisciplina(disciplinaSelecionada));
            Local local = obterLocal(salaSelecionada);
            int idSupervisor = Integer.parseInt(supervisor.getMatricula());
            int idMonitor = obterIdMonitor(monitorSelecionado);

            Disciplina disciplina = new Disciplina(disciplinaSelecionada, codDisciplina);
            Horario horario = new Horario(diaSelecionado, horarioSelecionado);
            try {
                MonitoriaDao.criarMonitoria(disciplina, horario, local, idMonitor, idSupervisor);
                JOptionPane.showMessageDialog(view, "Monitoria adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                view.dispose(); // Fecha a janela após salvar
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(view, "Erro ao adicionar monitoria: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        }
    }

    // Métodos utilitários podem ser movidos para uma classe separada se necessário
    private int obterCodigoDisciplina(String disciplinaSelecionada) {
        DisciplinaDao.obterCodigoDisciplina(disciplinaSelecionada);
        return 0;
    }

    private Local obterLocal(String salaSelecionada) {
        LocalDao.obterLocal(salaSelecionada);
        return new Local();
    }

    private int obterIdMonitor(String monitorSelecionado) {
        MonitorDao.obterIdMonitor(monitorSelecionado);
        return 0;
    }
}
