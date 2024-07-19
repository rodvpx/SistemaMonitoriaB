package GUI;

import javax.swing.*;
import java.awt.*;

import static GUI.AppGUI.mostrarMensagem;

public class TelaAluno extends BasePanel{

    private ScreenManager screenManager;

    public TelaAluno(ScreenManager screenManager) {
        super();
        this.screenManager = screenManager;
        setLayout(new GridBagLayout());
        criarPainelALuno();
    }

    public void criarPainelALuno() {
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel tituloLabel = new JLabel("PAINEL DO ALUNO");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 20, 10); // Margens
        add(tituloLabel, gbc);

        JButton botaoHorarios = new JButton("Horários");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 5); // Margens
        add(botaoHorarios, gbc);

        JButton botaoMonitores = new JButton("Monitores");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 10, 10); // Margens
        add(botaoMonitores, gbc);

        JButton botaoDisciplinas = new JButton("Disciplinas");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 5); // Margens
        add(botaoDisciplinas, gbc);

        JButton botaoMonitorias = new JButton("Monitorias");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 10, 10); // Margens
        add(botaoMonitorias, gbc);

        JButton botaoSolicitarMonitoria = new JButton("Solicitar Monitoria");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 0, 10); // Margens
        add(botaoSolicitarMonitoria, gbc);

        JButton botaoVoltar = new JButton("Sair");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Margens
        add(botaoVoltar, gbc);

        botaoHorarios.addActionListener(e -> {
            // Implemente a lógica para mostrar os horários do aluno
            mostrarMensagem("Mostrar horários do aluno", "Horários", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoMonitores.addActionListener(e -> {
            // Implemente a lógica para mostrar os monitores disponíveis
            mostrarMensagem("Mostrar monitores disponíveis", "Monitores", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoDisciplinas.addActionListener(e -> {
            // Implemente a lógica para mostrar as disciplinas do aluno
            mostrarMensagem("Mostrar disciplinas do aluno", "Disciplinas", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoMonitorias.addActionListener(e -> {
            // Implemente a lógica para mostrar as monitorias do aluno
            mostrarMensagem("Mostrar monitorias do aluno", "Monitorias", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoSolicitarMonitoria.addActionListener(e -> {
            // Implemente a lógica para solicitar monitoria
            mostrarMensagem("Solicitar monitoria", "Solicitar Monitoria", JOptionPane.INFORMATION_MESSAGE);
        });

        botaoVoltar.addActionListener(e -> { screenManager.showScreen(new TelaInicial(screenManager));});

    }
}
