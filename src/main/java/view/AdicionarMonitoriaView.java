package view;
import javax.swing.*;
import java.awt.*;

public class AdicionarMonitoriaView extends JFrame {

    private JComboBox<String> disciplinaComboBox;
    private JComboBox<String> salaComboBox;
    private JComboBox<String> monitorComboBox;
    private JComboBox<String> diaComboBox;
    private JComboBox<String> horarioComboBox;
    private JButton salvarButton;
    private JButton cancelarButton;

    public AdicionarMonitoriaView(String[] disciplinas, String[] salas, String[] monitores, String[] diasSemana, String[] horarios) {
        setTitle("Adicionar Monitoria");
        setSize(400, 350);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento ao redor

        disciplinaComboBox = criarComboBox(disciplinas);
        salaComboBox = criarComboBox(salas);
        monitorComboBox = criarComboBox(monitores);
        diaComboBox = criarComboBox(diasSemana);
        horarioComboBox = criarComboBox(horarios);

        Font labelFont = new Font("SansSerif", Font.BOLD, 14);

        formPanel.add(criarLabel("Disciplina:", labelFont));
        formPanel.add(disciplinaComboBox);
        formPanel.add(criarLabel("Sala:", labelFont));
        formPanel.add(salaComboBox);
        formPanel.add(criarLabel("Monitor:", labelFont));
        formPanel.add(monitorComboBox);
        formPanel.add(criarLabel("Dia da Semana:", labelFont));
        formPanel.add(diaComboBox);
        formPanel.add(criarLabel("Horário:", labelFont));
        formPanel.add(horarioComboBox);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        salvarButton = criarButton("Salvar");
        cancelarButton = criarButton("Cancelar");

        buttonPanel.add(salvarButton);
        buttonPanel.add(cancelarButton);

        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    private JLabel criarLabel(String texto, Font font) {
        JLabel label = new JLabel(texto);
        label.setFont(font);
        return label;
    }

    private JComboBox<String> criarComboBox(String[] items) {
        JComboBox<String> comboBox = new JComboBox<>(items);
        comboBox.setPreferredSize(new Dimension(200, 30));
        return comboBox;
    }

    private JButton criarButton(String texto) {
        JButton button = new JButton(texto);
        button.setPreferredSize(new Dimension(150, 30));
        button.setFont(new Font("SansSerif", Font.BOLD, 14));
        return button;
    }

    public JComboBox<String> getDisciplinaComboBox() {
        return disciplinaComboBox;
    }

    public JComboBox<String> getSalaComboBox() {
        return salaComboBox;
    }

    public JComboBox<String> getMonitorComboBox() {
        return monitorComboBox;
    }

    public JComboBox<String> getDiaComboBox() {
        return diaComboBox;
    }

    public JComboBox<String> getHorarioComboBox() {
        return horarioComboBox;
    }

    public JButton getSalvarButton() {
        return salvarButton;
    }

    public JButton getCancelarButton() {
        return cancelarButton;
    }
}

