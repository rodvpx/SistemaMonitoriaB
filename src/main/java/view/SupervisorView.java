package view;

import dao.DisciplinaDao;
import dao.LocalDao;
import dao.MonitorDao;
import model.*;
import controller.SupervisorController;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;

public class SupervisorView extends BasePanel {

    private JPanel rightPanel;
    private Supervisor supervisor;
    private SupervisorController controller;
    private Monitoria[] selectedMonitoria = new Monitoria[1];
    private JPanel selectedCard;

    public SupervisorView(Supervisor supervisor, SupervisorController controller) {
        this.supervisor = supervisor;
        this.controller = controller;
        criarPainelSupervisor();
    }

    private void criarPainelSupervisor() {
        setLayout(new BorderLayout());

        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JPanel imageNamePanel = new JPanel();
        imageNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel imageLabel = new JLabel(SupervisorView.redimensionarImagem("user-286.png", 60, 60));
        JLabel nameLabel = new JLabel(supervisor.getNome());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));

        imageNamePanel.add(imageLabel);
        imageNamePanel.add(nameLabel);

        leftPanel.add(imageNamePanel, BorderLayout.NORTH);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS));

        // Create and add buttons
        String[] buttonLabels = {"Monitorias", "Monitores", "Disciplinas", "Locais", "Sair"};
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

    public static ImageIcon redimensionarImagem(String caminhoImagem, int largura, int altura) {
        // Carrega a imagem do classpath
        ImageIcon icon = new ImageIcon(SupervisorView.class.getResource("/Img/" + caminhoImagem));
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
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

                // Adiciona as informações da monitoria como labels no painel
                monitoriaCard.add(new JLabel("Disciplina: " + monitoria.getDisciplina().getNome()));
                monitoriaCard.add(new JLabel("Sala: " + monitoria.getLocal().getSala()));
                monitoriaCard.add(new JLabel("Dia: " + monitoria.getHorario().getDiaSemana()));
                monitoriaCard.add(new JLabel("Horário: " + monitoria.getHorario().getHoras()));
                monitoriaCard.add(new JLabel("Inscritos: " + monitoria.getLocal().getInscritos()));
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
            addButton(bottomPanel, "Criar Monitoria", e -> controller.adicionarMonitoria());
            addButton(bottomPanel, "Excluir Monitoria", e -> {
                try {
                    controller.excluirMonitoria(selectedMonitoria[0]);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            });

            rightPanel.add(bottomPanel, BorderLayout.SOUTH);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }



    public void adicionarMonitoria() {

        JFrame frame = new JFrame("Adicionar Monitoria");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento ao redor

        JLabel disciplinaLabel = new JLabel("Disciplina:");
        JComboBox<Disciplina> disciplinaComboBox = new JComboBox<>(DisciplinaDao.getDisciplinas());
        disciplinaComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel salaLabel = new JLabel("Sala:");
        JComboBox<String> salaComboBox = new JComboBox<>(LocalDao.getSalas());
        salaComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel monitorLabel = new JLabel("Monitor:");
        JComboBox<String> monitorComboBox = new JComboBox<>(MonitorDao.getMonitores());
        monitorComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel diaLabel = new JLabel("Dia da Semana:");
        String[] diasSemana = {"Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira"};
        JComboBox<String> diaComboBox = new JComboBox<>(diasSemana);
        diaComboBox.setPreferredSize(new Dimension(200, 30));

        JLabel horarioLabel = new JLabel("Horário:");
        JTextField horarioField = new JTextField();
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
        formPanel.add(salaComboBox);
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
            String salaSelecionada = (String) salaComboBox.getSelectedItem();
            String monitorSelecionado = (String) monitorComboBox.getSelectedItem();
            String diaSelecionado = (String) diaComboBox.getSelectedItem();
            String horarioSelecionado = (String) horarioField.getText();

            if (disciplinaSelecionada == null || salaSelecionada.isEmpty() || monitorSelecionado.isEmpty()
                    || diaSelecionado.isEmpty() || horarioSelecionado.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                   Disciplina disciplina = new Disciplina(disciplinaSelecionada.getNome(), disciplinaSelecionada.getCodigo());
                    controller.criarMonitoriaCon(disciplina, diaSelecionado, horarioSelecionado, salaSelecionada, monitorSelecionado);
                    JOptionPane.showMessageDialog(frame, "Monitoria adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); // Fecha a janela após salvar
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar monitoria: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        cancelarButton.addActionListener(e -> frame.dispose());

        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    public void mostrarMonitores(List<Monitor> monitores) {
        String[] columnNames = {"Matrícula", "Nome", "E-mail"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Monitor monitor : monitores) {
            Object[] row = {monitor.getMatricula(), monitor.getNome(), monitor.getEmail()};
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        configureTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        SwingUtilities.invokeLater(() -> {
            rightPanel.removeAll();
            rightPanel.add(scrollPane, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            rightPanel.add(bottomPanel, BorderLayout.SOUTH);

            addButton(bottomPanel, "Promover Monitor", e -> controller.promoverMonitor());
            addButton(bottomPanel, "Excluir Monitor", e -> controller.excluirMonitor());

            rightPanel.add(bottomPanel, BorderLayout.SOUTH);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }

    public void mostrarDisciplinas(List<Disciplina> disciplinas) {

        String[] columnNames = {"Código", "Disciplína"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Disciplina disciplina : disciplinas) {
            Object[] row = {
                    disciplina.getCodigo(),
                    disciplina.getNome()
            };
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        configureTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        SwingUtilities.invokeLater(() -> {
            rightPanel.removeAll();
            rightPanel.add(scrollPane, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            rightPanel.add(bottomPanel, BorderLayout.SOUTH);

            addButton(bottomPanel, "Adicionar Disciplina", e -> controller.adicionarMonitoria());
            /*addButton(bottomPanel, "Excluir Disciplína");*/

            rightPanel.add(bottomPanel, BorderLayout.SOUTH);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }

    public void mostrarLocais(List<Local> locais) {

        String[] columnNames = {"Código", "Sala", "Capacidade", "Inscritos"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Local disciplina : locais) {
            Object[] row = {
                    disciplina.getId(),
                    disciplina.getSala(),
                    disciplina.getCapacidade(),
                    disciplina.getInscritos()
            };
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        configureTable(table);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        SwingUtilities.invokeLater(() -> {
            rightPanel.removeAll();
            rightPanel.add(scrollPane, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();

            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            rightPanel.add(bottomPanel, BorderLayout.SOUTH);

            addButton(bottomPanel, "Adicionar Local", e -> controller.adicionarMonitoria());
            /*addButton(bottomPanel, "Excluir Local", e -> controller.excluirMonitoria());*/

            rightPanel.add(bottomPanel, BorderLayout.SOUTH);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }

    private void configureTable(JTable table) {
        table.setFillsViewportHeight(true);
        table.setBorder(BorderFactory.createLineBorder(Color.decode("#3176FB"), 2));
        Font tableFont = new Font("SansSerif", Font.PLAIN, 18);
        table.setFont(tableFont);
        table.getTableHeader().setFont(tableFont);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }
    }

    private void addButton(JPanel panel, String label, ActionListener listener) {
        StyleButton button = new StyleButton(label);
        button.addActionListener(listener);
        panel.add(button);
    }

}
