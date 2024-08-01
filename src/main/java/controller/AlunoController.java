package controller;

import view.AlunoView;

import javax.swing.*;

public class AlunoController {

    private AlunoView alunoView;

    public AlunoController(AlunoView alunoView) {
        this.alunoView = alunoView;
        inicializar();
    }

    private void inicializar() {
        alunoView.getBotaoMonitorias().addActionListener(e -> abrirMonitoriaView());
        alunoView.getBotaoDisciplinas();
        alunoView.getBotaoSolicitarMonitoria();
        alunoView.getBotaoVoltar();
    }

    private void abrirMonitoriaView() {
        AlunoView alunoView = new AlunoView();
        JFrame alunoFrame = new JFrame("Painel do Aluno");
        alunoFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        alunoFrame.add(alunoView);
        alunoFrame.pack();
        alunoFrame.setLocationRelativeTo(null);
        alunoFrame.setVisible(true);
    }
}
