package view;

import dao.*;
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

import static dao.LocalDao.getSalas;

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
        String[] buttonLabels = {"Monitorias", "Monitores", "Disciplinas", "Locais", "Inscritos", "Sair"};
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
            addButton(bottomPanel, "Criar Monitoria", e -> controller.adicionarMonitoria());
            addButton(bottomPanel, "Excluir Monitoria", e -> {
                controller.excluirMonitoria(selectedMonitoria[0]);
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
            Local localSelecionado = (Local) salaComboBox.getSelectedItem();
            String monitorSelecionado = (String) monitorComboBox.getSelectedItem();
            String diaSelecionado = (String) diaComboBox.getSelectedItem();
            String horarioSelecionado = (String) horarioField.getText();

            if (disciplinaSelecionada == null || localSelecionado == null || monitorSelecionado.isEmpty()
                    || diaSelecionado.isEmpty() || horarioSelecionado.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                monitoria.setDisciplina(disciplinaSelecionada);
                monitoria.setLocal(localSelecionado);
                monitoria.setIdMonitor(MonitorDao.obterIdMonitor(monitorSelecionado));
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
        JComboBox<Local> salaComboBox = new JComboBox<>(getSalas().toArray(new Local[0]));
        salaComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Local local = (Local) value;
                return super.getListCellRendererComponent(list, local.getSala(), index, isSelected, cellHasFocus);
            }
        });
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
            Local localSelecionado = (Local) salaComboBox.getSelectedItem();
            String monitorSelecionado = (String) monitorComboBox.getSelectedItem();
            String diaSelecionado = (String) diaComboBox.getSelectedItem();
            String horarioSelecionado = (String) horarioField.getText();

            if (disciplinaSelecionada == null || localSelecionado == null || monitorSelecionado.isEmpty()
                    || diaSelecionado.isEmpty() || horarioSelecionado.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                try {
                    controller.criarMonitoriaCon(disciplinaSelecionada, diaSelecionado, horarioSelecionado, localSelecionado.getSala(), monitorSelecionado);
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

            // Botão Promover Monitor
            addButton(bottomPanel, "Promover Monitor", e -> {
                List<Aluno> alunos = AlunoDao.getAlunos4Monitor(); // Este método deve retornar a lista de alunos que podem ser promovidos
                abrirPromocaoMonitorView(alunos);
            });

            // Botão Excluir Monitor
            addButton(bottomPanel, "Excluir Monitor", e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String matriculaMonitor = (String) tableModel.getValueAt(selectedRow, 0); // Obtém a matrícula da coluna 0
                    try {
                        controller.excluirMonitor(matriculaMonitor);
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    JOptionPane.showMessageDialog(rightPanel, "Por favor, selecione um monitor para excluir.");
                }
            });

            rightPanel.add(bottomPanel, BorderLayout.SOUTH);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }

    private void abrirPromocaoMonitorView(List<Aluno> alunos) {
        // Criar a janela
        JFrame frame = new JFrame("Promover Aluno a Monitor");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(400, 400);
        frame.setLocationRelativeTo(null);

        // Criar o modelo da tabela para listar os alunos
        DefaultTableModel tableModel = new DefaultTableModel(new String[]{"Matrícula", "Nome"}, 0);

        for (Aluno aluno : alunos) {
            String matricula = aluno.getMatricula();
            String nome = aluno.getNome();
            tableModel.addRow(new Object[]{matricula, nome});
        }

        // Criar a tabela e a área de rolagem
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Criar o botão de promover
        JButton promoverButton = new JButton("Promover");
        promoverButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0) {
                String matriculaAluno = (String) tableModel.getValueAt(selectedRow, 0); // Pega a matrícula da coluna 1
                try {
                    controller.promoverAluno(matriculaAluno); // Passa a matrícula para promoverAluno
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frame.dispose(); // Fecha a janela após a promoção
            } else {
                JOptionPane.showMessageDialog(frame, "Por favor, selecione um aluno para promover.");
            }
        });

        frame.setVisible(true);

        // Adicionar o botão à parte inferior da janela
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(promoverButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Mostrar a janela
        frame.setVisible(true);
    }

    public void mostrarDisciplinas(List<Disciplina> disciplinas) {

        String[] columnNames = {"Código", "Disciplina"};
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

            // Botão "Adicionar Disciplina" com uma janela de entrada
            addButton(bottomPanel, "Adicionar Disciplina", e -> {
                JTextField codigoField = new JTextField(10);
                JTextField nomeField = new JTextField(10);

                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.add(new JLabel("Código:"));
                panel.add(codigoField);
                panel.add(new JLabel("Nome:"));
                panel.add(nomeField);

                int result = JOptionPane.showConfirmDialog(
                        rightPanel, panel, "Adicionar Disciplina", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    String codigo = codigoField.getText().trim();
                    String nome = nomeField.getText().trim();

                    if (!codigo.isEmpty() && !nome.isEmpty()) {
                        Disciplina novaDisciplina = new Disciplina(nome, codigo);
                        try {
                            controller.adicionarDisciplina(novaDisciplina);
                        } catch (SQLException ex) {
                            throw new RuntimeException(ex);
                        }

                        // Atualiza a tabela
                        disciplinas.add(novaDisciplina);
                        tableModel.addRow(new Object[]{codigo, nome});
                    } else {
                        JOptionPane.showMessageDialog(rightPanel, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            // Botão "Excluir Disciplina"
            addButton(bottomPanel, "Excluir Disciplina", e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {
                    String codigoDisciplina = (String) tableModel.getValueAt(selectedRow, 0); // Obtém o código da disciplina da coluna 0
                    try {
                        controller.excluirDisciplina(codigoDisciplina); // Passe o código para exclusão
                    } catch (SQLException ex) {
                        throw new RuntimeException(ex);
                    }

                    // Atualiza a tabela
                    tableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(rightPanel, "Por favor, selecione uma disciplina para excluir.");
                }
            });

            rightPanel.add(bottomPanel, BorderLayout.SOUTH);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }

    public void mostrarLocais(List<Local> locais) {

        String[] columnNames = {"Código", "Sala", "Capacidade"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Local local : locais) {
            Object[] row = {
                    local.getId(),
                    local.getSala(),
                    local.getCapacidade(),
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

            // Botão "Adicionar Local" com uma janela de entrada
            addButton(bottomPanel, "Adicionar Local", e -> {
                JTextField salaField = new JTextField(10);
                JTextField capacidadeField = new JTextField(10);

                JPanel panel = new JPanel(new GridLayout(2, 2));
                panel.add(new JLabel("Sala:"));
                panel.add(salaField);
                panel.add(new JLabel("Capacidade:"));
                panel.add(capacidadeField);

                int result = JOptionPane.showConfirmDialog(
                        rightPanel, panel, "Adicionar Local", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE
                );

                if (result == JOptionPane.OK_OPTION) {
                    String sala = salaField.getText().trim();
                    String capacidadeStr = capacidadeField.getText().trim();

                    if (!sala.isEmpty() && !capacidadeStr.isEmpty()) {
                        try {
                            int capacidade = Integer.parseInt(capacidadeStr);
                            Local novoLocal = new Local(sala, capacidade);
                            controller.adicionarLocal(novoLocal);

                            // Atualiza a tabela
                            locais.add(novoLocal);
                            tableModel.addRow(new Object[]{novoLocal.getId(), sala, capacidade, 0});
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(rightPanel, "Capacidade deve ser um número inteiro.", "Erro", JOptionPane.ERROR_MESSAGE);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(rightPanel, "Erro ao adicionar local: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rightPanel, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            });

            addButton(bottomPanel, "Excluir Local", e -> {
                int selectedRow = table.getSelectedRow();
                if (selectedRow >= 0) {

                    Object idObject = tableModel.getValueAt(selectedRow, 0); // Obtém o valor da coluna do código

                    if (idObject instanceof Integer) {
                        Integer id = (Integer) idObject; // Converte para Integer
                        try {
                            controller.excluirLocal(id);
                            // Remove a linha da tabela
                            tableModel.removeRow(selectedRow);
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(rightPanel, "Erro ao excluir local: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(rightPanel, "Erro ao obter ID do local.");
                    }
                } else {
                    JOptionPane.showMessageDialog(rightPanel, "Por favor, selecione um local para excluir.");
                }
            });

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

    public static void addButton(JPanel panel, String label, ActionListener listener) {
        StyleButton button = new StyleButton(label);
        button.addActionListener(listener);
        button.setFont(new Font("Arial", Font.PLAIN, 15));
        button.setPreferredSize(new Dimension(200, 50));
        panel.add(button);
    }
}

