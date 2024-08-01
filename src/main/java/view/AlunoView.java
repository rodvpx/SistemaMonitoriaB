package view;

import javax.swing.*;
import java.awt.*;

public class AlunoView extends BasePanel {

    private JButton botaoHorarios;
    private JButton botaoMonitores;
    private JButton botaoDisciplinas;
    private JButton botaoMonitorias;
    private JButton botaoSolicitarMonitoria;
    private JButton botaoVoltar;

    public AlunoView() {
        criarPainelALuno();
    }

    private void criarPainelALuno() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JLabel tituloLabel = new JLabel("PAINEL DO ALUNO");
        tituloLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(10, 10, 20, 10); // Margens
        add(tituloLabel, gbc);

        botaoHorarios = new JButton("Horários");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 5); // Margens
        add(botaoHorarios, gbc);

        botaoMonitores = new JButton("Monitores");
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 10, 10); // Margens
        add(botaoMonitores, gbc);

        botaoDisciplinas = new JButton("Disciplinas");
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 10, 10, 5); // Margens
        add(botaoDisciplinas, gbc);

        botaoMonitorias = new JButton("Monitorias");
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0, 5, 10, 10); // Margens
        add(botaoMonitorias, gbc);

        botaoSolicitarMonitoria = new JButton("Solicitar Monitoria");
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 0, 10); // Margens
        add(botaoSolicitarMonitoria, gbc);

        botaoVoltar = new JButton("Sair");
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10); // Margens
        add(botaoVoltar, gbc);
    }

    // Métodos getter para acessar os botões
    public JButton getBotaoHorarios() {
        return botaoHorarios;
    }

    public JButton getBotaoMonitores() {
        return botaoMonitores;
    }

    public JButton getBotaoDisciplinas() {
        return botaoDisciplinas;
    }

    public JButton getBotaoMonitorias() {
        return botaoMonitorias;
    }

    public JButton getBotaoSolicitarMonitoria() {
        return botaoSolicitarMonitoria;
    }

    public JButton getBotaoVoltar() {
        return botaoVoltar;
    }
}
