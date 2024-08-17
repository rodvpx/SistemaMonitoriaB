package view;

import controller.AlunoController;
import controller.MonitorController;
import dao.DisciplinaDao;
import dao.MonitorDao;
import dao.MonitoriaDao;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

import static dao.LocalDao.getSalas;
import static view.SupervisorView.addButton;

public class MonitorView extends BasePanel {

    private JPanel rightPanel;
    private Monitor monitor;
    private MonitorController controller;
    private JPanel selectedCard;
    private Monitoria[] selectedMonitoria = new Monitoria[1];

    public MonitorView(Monitor monitor, MonitorController controller) {
        this.monitor = monitor;
        this.controller = controller;
        criarPainelMonitor();
    }

    private void criarPainelMonitor() {
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JPanel imageNamePanel = new JPanel();
        imageNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel imageLabel = new JLabel(SupervisorView.redimensionarImagem("user-286.png", 60, 60));
        JLabel nameLabel = new JLabel(monitor.getNome());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        imageNamePanel.add(imageLabel);
        imageNamePanel.add(nameLabel);

        leftPanel.add(imageNamePanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        String[] buttonLabels = {"Monitorias", "Minhas Monitorias", "Sair"};
        for (String label : buttonLabels) {
            StyleButton button = new StyleButton(label);
            button.setMaximumSize(new Dimension(Integer.MAX_VALUE, button.getPreferredSize().height));
            button.setActionCommand(label);
            button.addActionListener(controller);
            buttonsPanel.add(button);
            buttonsPanel.add(Box.createVerticalStrut(10));
        }

        leftPanel.add(buttonsPanel, BorderLayout.CENTER);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    public void mostrarMonitorias(List<Monitoria> monitorias) {

        final Monitoria[] selectedMonitoria = new Monitoria[1];
        SwingUtilities.invokeLater(() -> {
            rightPanel.removeAll();

            // Painel principal com GridBagLayout para exibir as monitorias
            JPanel monitoriaPanel = new JPanel(new GridBagLayout());
            monitoriaPanel.setBackground(Color.WHITE);

            GridBagConstraints gbc = new GridBagConstraints();
            gbc.insets = new Insets(10, 10, 10, 10); // Espaçamento entre as monitorias
            gbc.fill = GridBagConstraints.BOTH; // Permite que os componentes se expandam se necessário
            gbc.weightx = 1.0; // Expande horizontalmente para preencher a coluna
            gbc.weighty = 0; // Não expande verticalmente

            int column = 0;
            int row = 0;
            for (Monitoria monitoria : monitorias) {
                // Criando um painel para cada monitoria
                JPanel monitoriaCard = new JPanel();
                monitoriaCard.setBorder(BorderFactory.createLineBorder(Color.decode("#C6E2FF"), 5));
                monitoriaCard.setPreferredSize(new Dimension(200, 120)); // Tamanho fixo para os quadrados
                monitoriaCard.setLayout(new BoxLayout(monitoriaCard, BoxLayout.Y_AXIS)); // Organiza os labels verticalmente

                // Obter o número de inscritos
                int numeroInscritos;
                try {
                    numeroInscritos = MonitoriaDao.contarInscricoes(monitoria.getId());
                } catch (SQLException e) {
                    numeroInscritos = 0; // Defina um valor padrão ou exiba uma mensagem de erro
                    e.printStackTrace(); // Para debug
                }
                // Adiciona as informações da monitoria como labels no painel
                monitoriaCard.add(new JLabel("Disciplina: " + monitoria.getDisciplina().getNome()));
                monitoriaCard.add(new JLabel("Sala: " + monitoria.getLocal().getSala()));
                monitoriaCard.add(new JLabel("Dia: " + monitoria.getHorario().getDiaSemana()));
                monitoriaCard.add(new JLabel("Horário: " + monitoria.getHorario().getHoras()));
                monitoriaCard.add(new JLabel("Inscritos: " + numeroInscritos));
                monitoriaCard.add(new JLabel("Capacidade: " + monitoria.getLocal().getCapacidade()));
                monitoriaCard.add(new JLabel("Monitor(a): " + MonitorDao.getMonitorId(monitoria.getIdMonitor())));

                monitoriaCard.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        // Deseleciona o cartão previamente selecionado, se existir
                        if (selectedCard != null) {
                            selectedCard.setBorder(BorderFactory.createLineBorder(Color.decode("#C6E2FF"), 5));
                            selectedCard.setBackground(Color.WHITE);
                        }

                        // Seleciona o cartão clicado
                        monitoriaCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                        monitoriaCard.setBackground(Color.decode("#C6E2FF"));

                        // Armazena a monitoria e o cartão selecionados
                        selectedMonitoria[0] = monitoria;
                        selectedCard = monitoriaCard;
                    }
                });

                // Adiciona o painel da monitoria ao painel principal
                gbc.gridx = column;
                gbc.gridy = row;
                monitoriaPanel.add(monitoriaCard, gbc);

                column++;
                if (column >= 4) { // Limita a 4 colunas
                    column = 0;
                    row++;
                }
            }

            // Adiciona o painel com as monitorias ao painel principal
            JScrollPane scrollPane = new JScrollPane(monitoriaPanel);
            scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

            rightPanel.add(scrollPane, BorderLayout.CENTER);

            // Painel inferior para os botões
            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            addButton(bottomPanel, "Inscrever-se", e -> {
                if (selectedMonitoria[0] != null) {
                    try {
                        controller.inscreverMonitoria(selectedMonitoria[0]);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            addButton(bottomPanel, "Editar Monitoria", e -> {
                if (selectedMonitoria[0] != null) {
                    editarMonitoria(selectedMonitoria[0]);
                } else {
                    JOptionPane.showMessageDialog(rightPanel, "Nenhuma monitoria selecionada.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            });

            rightPanel.add(bottomPanel, BorderLayout.SOUTH);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }

    public void editarMonitoria(Monitoria monitoria) {
        JFrame frame = new JFrame("Editar Monitoria");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento ao redor

        JLabel disciplinaLabel = new JLabel("Disciplina:");
        JComboBox<Disciplina> disciplinaComboBox = new JComboBox<>(DisciplinaDao.getDisciplinas());
        disciplinaComboBox.setSelectedItem(monitoria.getDisciplina());
        disciplinaComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel salaLabel = new JLabel("Sala:");
        JComboBox<Local> salaComboBox = new JComboBox<>(getSalas().toArray(new Local[0]));
        salaComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Local local = (Local) value;
                return super.getListCellRendererComponent(list, local.getSala(), index, isSelected, cellHasFocus);
            }
        });

        // Ajuste para selecionar o item correto
        salaComboBox.setSelectedItem(monitoria.getLocal());
        salaComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel monitorLabel = new JLabel("Monitor:");
        JComboBox<String> monitorComboBox = new JComboBox<>(MonitorDao.getMonitores());
        monitorComboBox.setSelectedItem(MonitorDao.getMonitorId(monitoria.getIdMonitor()));
        monitorComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel diaLabel = new JLabel("Dia da Semana:");
        String[] diasSemana = {"Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira"};
        JComboBox<String> diaComboBox = new JComboBox<>(diasSemana);
        diaComboBox.setSelectedItem(monitoria.getHorario().getDiaSemana());
        diaComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel horarioLabel = new JLabel("Horário:");
        JTextField horarioField = new JTextField(monitoria.getHorario().getHoras());
        horarioField.setPreferredSize(new Dimension(200, 30));

        Font labelFont = new Font("SansSerif", Font.BOLD, 14);
        disciplinaLabel.setFont(labelFont);
        salaLabel.setFont(labelFont);
        monitorLabel.setFont(labelFont);
        diaLabel.setFont(labelFont);
        horarioLabel.setFont(labelFont);

        formPanel.add(disciplinaLabel);
        formPanel.add(disciplinaComboBox);
        formPanel.add(salaLabel);
        formPanel.add(salaComboBox); // Corrigido para adicionar `salaComboBox`
        formPanel.add(monitorLabel);
        formPanel.add(monitorComboBox);
        formPanel.add(diaLabel);
        formPanel.add(diaComboBox);
        formPanel.add(horarioLabel);
        formPanel.add(horarioField);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton salvarButton = new JButton("Salvar");
        salvarButton.setPreferredSize(new Dimension(150, 30));
        salvarButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setPreferredSize(new Dimension(150, 30));
        cancelarButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        buttonPanel.add(salvarButton);
        buttonPanel.add(cancelarButton);

        salvarButton.addActionListener(e -> {
            Disciplina disciplinaSelecionada = (Disciplina) disciplinaComboBox.getSelectedItem();
            Local localSelecionado = (Local) salaComboBox.getSelectedItem();
            String monitorSelecionado = (String) monitorComboBox.getSelectedItem();
            String diaSelecionado = (String) diaComboBox.getSelectedItem();
            String horarioSelecionado = (String) horarioField.getText();

            if (disciplinaSelecionada == null || localSelecionado == null || monitorSelecionado.isEmpty()
                    || diaSelecionado.isEmpty() || horarioSelecionado.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                monitoria.setDisciplina(disciplinaSelecionada);
                monitoria.setLocal(localSelecionado); // Atualize o `Local` completo
                monitoria.setIdMonitor(MonitorDao.obterIdMonitor(monitorSelecionado)); // Atualize conforme sua implementação
                monitoria.getHorario().setDiaSemana(diaSelecionado);
                monitoria.getHorario().setHoras(horarioSelecionado);

                try {
                    controller.atualizarMonitoria(monitoria);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                frame.dispose(); // Fecha a janela após salvar
            }
        });

        cancelarButton.addActionListener(e -> frame.dispose());

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void mostrarMinhasMonitorias(List<Monitoria> monitorias) {
        SwingUtilities.invokeLater(() -> {
            rightPanel.removeAll();

            if (monitorias == null || monitorias.isEmpty()) {
                rightPanel.add(new JLabel("Nenhuma monitoria encontrada."), BorderLayout.CENTER);
            } else {
                JPanel monitoriaPanel = new JPanel(new GridBagLayout());
                monitoriaPanel.setBackground(Color.WHITE);

                GridBagConstraints gbc = new GridBagConstraints();
                gbc.insets = new Insets(10, 10, 10, 10);
                gbc.fill = GridBagConstraints.BOTH;
                gbc.weightx = 1.0;
                gbc.weighty = 0;

                int column = 0;
                int row = 0;
                for (Monitoria monitoria : monitorias) {
                    JPanel monitoriaCard = new JPanel();
                    monitoriaCard.setBorder(BorderFactory.createLineBorder(Color.decode("#C6E2FF"), 5));
                    monitoriaCard.setPreferredSize(new Dimension(200, 120));
                    monitoriaCard.setLayout(new BoxLayout(monitoriaCard, BoxLayout.Y_AXIS));

                    // Obter o número de inscritos
                    int numeroInscritos;
                    try {
                        numeroInscritos = MonitoriaDao.contarInscricoes(monitoria.getId());
                    } catch (SQLException e) {
                        numeroInscritos = 0; // Defina um valor padrão ou exiba uma mensagem de erro
                        e.printStackTrace(); // Para debug
                    }

                    monitoriaCard.add(new JLabel("Disciplina: " + monitoria.getDisciplina().getNome()));
                    monitoriaCard.add(new JLabel("Sala: " + monitoria.getLocal().getSala()));
                    monitoriaCard.add(new JLabel("Dia: " + monitoria.getHorario().getDiaSemana()));
                    monitoriaCard.add(new JLabel("Horário: " + monitoria.getHorario().getHoras()));
                    monitoriaCard.add(new JLabel("Inscritos: " + numeroInscritos));
                    monitoriaCard.add(new JLabel("Capacidade: " + monitoria.getLocal().getCapacidade()));
                    monitoriaCard.add(new JLabel("Monitor(a): " + MonitorDao.getMonitorId(monitoria.getIdMonitor())));

                    monitoriaCard.addMouseListener(new MouseAdapter() {
                        @Override
                        public void mouseClicked(MouseEvent e) {
                            if (selectedCard != null) {
                                selectedCard.setBorder(BorderFactory.createLineBorder(Color.decode("#C6E2FF"), 5));
                                selectedCard.setBackground(Color.WHITE);
                            }

                            monitoriaCard.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
                            monitoriaCard.setBackground(Color.decode("#C6E2FF"));

                            selectedMonitoria[0] = monitoria;
                            selectedCard = monitoriaCard;
                        }
                    });

                    gbc.gridx = column;
                    gbc.gridy = row;
                    monitoriaPanel.add(monitoriaCard, gbc);

                    column++;
                    if (column >= 4) {
                        column = 0;
                        row++;
                    }
                }

                JScrollPane scrollPane = new JScrollPane(monitoriaPanel);
                scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

                rightPanel.add(scrollPane, BorderLayout.CENTER);
            }

            JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
            addButton(bottomPanel, "Cancelar Inscrição", e -> {
                try {
                    controller.cancelarInscricao(selectedMonitoria[0]);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });

            rightPanel.add(bottomPanel, BorderLayout.SOUTH);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }

}