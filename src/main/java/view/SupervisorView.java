package view;

import model.*;

import javax.swing.*;

import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static factory.conexao.getConexao;

public class SupervisorView extends BasePanel {

    private JPanel rightPanel;

    public SupervisorView() {
        criarPainelSupervisor();
    }

    public void criarPainelSupervisor() {
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout());

        JPanel imageNamePanel = new JPanel();
        imageNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));

        JLabel imageLabel = new JLabel(redimensionarImagem("Img/user-286.png", 60, 60));
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
            button.addActionListener(createButtonActionListener(label));
            buttonsPanel.add(button);
            buttonsPanel.add(Box.createVerticalStrut(10));
        }

        leftPanel.add(buttonsPanel, BorderLayout.CENTER);

        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    private ImageIcon redimensionarImagem(String caminhoImagem, int largura, int altura) {
        ImageIcon icon = new ImageIcon(caminhoImagem);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    private ActionListener createButtonActionListener(String actionCommand) {
        return e -> {
            try {
                switch (actionCommand) {
                    case "Monitorias":
                        mostrarMonitorias();
                        break;
                    case "Monitores":
                        mostrarMonitores();
                        break;
                    case "Disciplinas":
                        mostrarDisciplina();
                        break;
                    case "Locais":
                        mostrarLocal();
                        break;
                    case "Sair":
                        screenManager.showScreen(new PrincipalView(screenManager));
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao buscar dados: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        };
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

    public void mostrarMonitorias(List<Monitoria> monitorias) {
        // Criar o modelo da tabela
        String[] columnNames = {"Disciplina", "Sala", "Inscritos", "Capacidade", "Dia", "Horário"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Monitoria monitoria : monitorias) {
            Object[] row = {
                    monitoria.getDisciplina().getNome(),
                    monitoria.getLocal().getSala(),
                    monitoria.getLocal().getInscritos(),
                    monitoria.getLocal().getCapacidade(),
                    monitoria.getHorario().getDiaSemana(),
                    monitoria.getHorario().getHoras()
            };
            tableModel.addRow(row);
        }

        // Criar e configurar a tabela
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setBorder(BorderFactory.createLineBorder(Color.decode("#3176FB"), 2));

        // Configurar a fonte da tabela para 18 pontos
        Font tableFont = new Font("SansSerif", Font.PLAIN, 18);
        table.setFont(tableFont);
        table.getTableHeader().setFont(tableFont);

        // Centralizar todas as colunas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Criar e configurar o JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona algum espaçamento

        // Atualizar o painel direito na EDT
        SwingUtilities.invokeLater(() -> {
            rightPanel.removeAll();
            rightPanel.add(scrollPane, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();

            // Adicionar o painel inferior com os botões
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            rightPanel.add(bottomPanel, BorderLayout.SOUTH);

            StyleButton adicionarButton = new StyleButton("Criar Monitoria");
            adicionarButton.setPreferredSize(new Dimension(180, 50));
            adicionarButton.setFont(new Font("SansSerif", Font.PLAIN, 17));
            adicionarButton.addActionListener(e -> adicionarMonitoria());
            bottomPanel.add(adicionarButton);

            StyleButton excluirButton = new StyleButton("Excluir Monitoria");
            excluirButton.setPreferredSize(new Dimension(180, 50));
            excluirButton.setFont(new Font("SansSerif", Font.PLAIN, 17));
            excluirButton.addActionListener(e -> excluirMonitoria());
            bottomPanel.add(excluirButton);

            rightPanel.add(bottomPanel, BorderLayout.SOUTH);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }

}
    private void excluirMonitoria() {
    }

    private void adicionarMonitoria() {
        JFrame frame = new JFrame("Adicionar Monitoria");
        frame.setSize(400, 350);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLocationRelativeTo(null); // Centraliza a janela na tela

        // Painel do formulário
        JPanel formPanel = new JPanel(new GridLayout(5, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Espaçamento ao redor

        // Componentes para seleção de disciplina
        JLabel disciplinaLabel = new JLabel("Disciplina:");
        JComboBox<String> disciplinaComboBox = new JComboBox<>(getDisciplinas());
        disciplinaComboBox.setPreferredSize(new Dimension(200, 30));

        // Componentes para seleção de sala
        JLabel salaLabel = new JLabel("Sala:");
        JComboBox<String> salaComboBox = new JComboBox<>(getSalas());
        salaComboBox.setPreferredSize(new Dimension(200, 30));

        // Componentes para seleção de monitor
        JLabel monitorLabel = new JLabel("Monitor:");
        JComboBox<String> monitorComboBox = new JComboBox<>(getMonitores());
        monitorComboBox.setPreferredSize(new Dimension(200, 30));

        // Componentes para seleção do dia da semana
        JLabel diaLabel = new JLabel("Dia da Semana:");
        String[] diasSemana = {"Segunda-feira", "Terça-feira", "Quarta-feira", "Quinta-feira", "Sexta-feira"};
        JComboBox<String> diaComboBox = new JComboBox<>(diasSemana);
        diaComboBox.setPreferredSize(new Dimension(200, 30));

        // Componentes para seleção de horário
        JLabel horarioLabel = new JLabel("Horário:");
        String[] horarios = {"08:00", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
        JComboBox<String> horarioComboBox = new JComboBox<>(horarios);
        horarioComboBox.setPreferredSize(new Dimension(200, 30));

        // Configuração das fontes
        Font labelFont = new Font("SansSerif", Font.BOLD, 14);
        disciplinaLabel.setFont(labelFont);
        salaLabel.setFont(labelFont);
        monitorLabel.setFont(labelFont);
        diaLabel.setFont(labelFont);
        horarioLabel.setFont(labelFont);

        // Adicionando componentes ao painel do formulário
        formPanel.add(disciplinaLabel);
        formPanel.add(disciplinaComboBox);
        formPanel.add(salaLabel);
        formPanel.add(salaComboBox);
        formPanel.add(monitorLabel);
        formPanel.add(monitorComboBox);
        formPanel.add(diaLabel);
        formPanel.add(diaComboBox);
        formPanel.add(horarioLabel);
        formPanel.add(horarioComboBox);

        // Painel dos botões
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JButton salvarButton = new JButton("Salvar");
        salvarButton.setPreferredSize(new Dimension(150, 30));
        salvarButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        JButton cancelarButton = new JButton("Cancelar");
        cancelarButton.setPreferredSize(new Dimension(150, 30));
        cancelarButton.setFont(new Font("SansSerif", Font.BOLD, 14));

        buttonPanel.add(salvarButton);
        buttonPanel.add(cancelarButton);

        // Adicionando ActionListener para o botão salvar
        salvarButton.addActionListener(e -> {
            String disciplinaSelecionada = (String) disciplinaComboBox.getSelectedItem();
            String salaSelecionada = (String) salaComboBox.getSelectedItem();
            String monitorSelecionado = (String) monitorComboBox.getSelectedItem();
            String diaSelecionado = (String) diaComboBox.getSelectedItem();
            String horarioSelecionado = (String) horarioComboBox.getSelectedItem();

            if (disciplinaSelecionada.isEmpty() || salaSelecionada.isEmpty() || monitorSelecionado.isEmpty()
                    || diaSelecionado.isEmpty() || horarioSelecionado.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Por favor, preencha todos os campos.", "Erro", JOptionPane.ERROR_MESSAGE);
            } else {
                String codDisciplina = Integer.toString(obterCodigoDisciplina(disciplinaSelecionada));
                Local local = obterLocal(salaSelecionada);
                int idSupervisor = Integer.parseInt(supervisor.getMatricula());
                int idMonitor = obterIdMonitor(monitorSelecionado);

                Disciplina disciplina = new Disciplina(disciplinaSelecionada, codDisciplina);
                Horario horario = new Horario(diaSelecionado, horarioSelecionado);
                try {
                    supervisor.criarMonitoria(disciplina, horario, local, idMonitor, idSupervisor);
                    JOptionPane.showMessageDialog(frame, "Monitoria adicionada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    frame.dispose(); // Fecha a janela após salvar
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(frame, "Erro ao adicionar monitoria: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        // ActionListener para o botão cancelar
        cancelarButton.addActionListener(e -> frame.dispose());

        // Adicionando painéis ao frame
        frame.add(formPanel, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        // Tornando a janela visível
        frame.setVisible(true);
    }

    private int obterIdMonitor(String nomeMonitor) {
        String sql = "SELECT id FROM usuario WHERE nome = ?";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeMonitor);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return -1;
    }

    private Local obterLocal(String nomeSala) {
        String sql = "SELECT id, sala, capacidade, inscritos FROM local WHERE sala = ?";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeSala);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String sala = rs.getString("sala");
                int capacidade = rs.getInt("capacidade");
                int inscritos = rs.getInt("inscritos");
                return new Local(id, sala, capacidade, inscritos);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static int obterCodigoDisciplina(String nomeDisciplina) {
        String sql = "SELECT codigo FROM disciplina WHERE nome = ?";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nomeDisciplina);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("codigo");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private String[] getMonitores() {
        List<String> monitores = new ArrayList<>();
        String sql = "SELECT nome FROM usuario WHERE tipo = 'M' ORDER BY nome ASC";

        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                monitores.add(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return monitores.toArray(new String[0]); // Convertendo a lista para um array de Strings
    }

    private String[] getSalas() {
        List<String> salas = new ArrayList<>();

        String sqlSalas = "SELECT sala FROM local ORDER BY sala";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sqlSalas);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                salas.add(rs.getString("sala"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return salas.toArray(new String[0]); // Convertendo a lista para um array de Strings
    }

    private String[] getDisciplinas() {
        List<String> disciplinas = new ArrayList<>();

        String sqlDisciplinas = "SELECT nome FROM disciplina ORDER BY nome";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sqlDisciplinas);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                disciplinas.add(rs.getString("nome"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return disciplinas.toArray(new String[0]); // Convertendo a lista para um array de Strings
    }


    private void mostrarMonitores() {
        List<Monitor> monitores = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE tipo = 'M' ORDER BY nome ASC";
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql);
             ResultSet rs = sta.executeQuery()) {

            while (rs.next()) {
                String matricula = rs.getString("matricula");
                String nome = rs.getString("nome");
                String email = rs.getString("email");

                Monitor monitor = new Monitor(matricula, nome, email, null, null, null, null);
                monitores.add(monitor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Criar o modelo da tabela
        String[] columnNames = {"Matrícula", "Nome", "E-mail"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Monitor monitor : monitores) {
            Object[] row = {monitor.getMatricula(), monitor.getNome(), monitor.getEmail()};
            tableModel.addRow(row);
        }

        // Criar e configurar a tabela
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setBorder(BorderFactory.createLineBorder(Color.decode("#3176FB"), 2));

        // Configurar a fonte da tabela para 18 pontos
        Font tableFont = new Font("SansSerif", Font.PLAIN, 18);
        table.setFont(tableFont);
        table.getTableHeader().setFont(tableFont);

        // Centralizar todas as colunas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Criar e configurar o JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona algum espaçamento

        // Atualizar o painel direito na EDT
        SwingUtilities.invokeLater(() -> {
            rightPanel.removeAll();
            rightPanel.add(scrollPane, BorderLayout.CENTER);
            rightPanel.revalidate();
            rightPanel.repaint();

            // Adiciona o painel inferior com os botões
            JPanel bottomPanel = new JPanel();
            bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
            rightPanel.add(bottomPanel, BorderLayout.SOUTH);


            StyleButton adicionarButton = new StyleButton("Promover Monitor");
            adicionarButton.setPreferredSize(new Dimension(180, 50));
            adicionarButton.setFont(new Font("SansSerif", Font.PLAIN, 17));
            adicionarButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    adicionarNovoMonitor();
                }
            });
            bottomPanel.add(adicionarButton);

            StyleButton excluirButton = new StyleButton("Excluir Monitor");
            excluirButton.setPreferredSize(new Dimension(180, 50));
            excluirButton.setFont(new Font("SansSerif", Font.PLAIN, 17));
            excluirButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    excluirMonitor();
                }
            });
            bottomPanel.add(excluirButton);

            rightPanel.add(bottomPanel, BorderLayout.SOUTH);

            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }

    private void excluirMonitor() {
        JTable table = (JTable) ((JScrollPane) rightPanel.getComponent(0)).getViewport().getView();
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String matricula = (String) table.getValueAt(selectedRow, 0);
            supervisor.excluirMonitor(matricula);
            mostrarMonitores(); // Atualiza a lista após excluir
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um monitor para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void adicionarNovoMonitor() {
        List<Aluno> alunos = new ArrayList<>();
        String sql = "SELECT * FROM usuario WHERE tipo = 'A' ORDER BY nome ASC";

        // Consultar os usuários do tipo 'A' do banco de dados
        try (Connection conn = getConexao();
             PreparedStatement sta = conn.prepareStatement(sql);
             ResultSet rs = sta.executeQuery()) {

            while (rs.next()) {
                String matricula = rs.getString("matricula");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                Aluno aluno = new Aluno(matricula, nome, email, null);
                alunos.add(aluno);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Criar o modelo da tabela
        String[] columnNames = {"Matrícula", "Nome", "E-mail"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Aluno aluno : alunos) {
            Object[] row = {aluno.getMatricula(), aluno.getNome(), aluno.getEmail()};
            tableModel.addRow(row);
        }

        // Criar e configurar a tabela
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setBorder(BorderFactory.createLineBorder(Color.decode("#3176FB"), 2));

        // Configurar a fonte da tabela para 18 pontos
        Font tableFont = new Font("SansSerif", Font.PLAIN, 18);
        table.setFont(tableFont);
        table.getTableHeader().setFont(tableFont);

        // Centralizar todas as colunas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        // Criar e configurar o JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona algum espaçamento

        // Criar um botão para promover o aluno selecionado
        StyleButton promoverButton = new StyleButton("Promover para Monitor");
        promoverButton.setPreferredSize(new Dimension(200, 50));
        promoverButton.setFont(new Font("SansSerif", Font.PLAIN, 15));
        promoverButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String matricula = (String) table.getValueAt(selectedRow, 0);
                Aluno alunoSelecionado = alunos.stream().filter(a -> a.getMatricula().equals(matricula)).findFirst().orElse(null);
                if (alunoSelecionado != null) {
                    supervisor.promoverAlunoParaMonitor(alunoSelecionado);
                    JOptionPane.showMessageDialog(null, "Aluno promovido a Monitor com sucesso!");
                    // Atualizar a tabela removendo o aluno promovido
                    tableModel.removeRow(selectedRow);
                }
            }
        });

        // Criar um botão para voltar
        StyleButton voltarButton = new StyleButton("Voltar");
        voltarButton.setPreferredSize(new Dimension(180, 50));
        voltarButton.setFont(new Font("SansSerif", Font.PLAIN, 17));
        voltarButton.addActionListener(e -> {
            // Lógica para voltar à tela anterior
            // Exemplo: rightPanel.removeAll();
            rightPanel.removeAll();
            mostrarMonitores(); // Chamar o método para mostrar monitores
            rightPanel.revalidate();
            rightPanel.repaint();
        });


        // Criar um painel para os botões
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        buttonPanel.add(promoverButton);
        buttonPanel.add(voltarButton);

        // Atualizar o painel direito na EDT
        SwingUtilities.invokeLater(() -> {
            rightPanel.removeAll();
            rightPanel.setLayout(new BorderLayout());
            rightPanel.add(scrollPane, BorderLayout.CENTER);
            rightPanel.add(buttonPanel, BorderLayout.SOUTH);
            rightPanel.revalidate();
            rightPanel.repaint();
        });
    }


    // METODOS DISCIPLINA
    private void mostrarDisciplina() throws SQLException {
        // Consultar as disciplinas do banco de dados
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT codigo, nome FROM disciplina ORDER BY nome ASC";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Disciplina disciplina = new Disciplina();
                disciplina.setCodigo(rs.getString("codigo"));
                disciplina.setNome(rs.getString("nome"));
                disciplinas.add(disciplina);
            }
        }

        // Criar o modelo da tabela
        String[] columnNames = {"Código", "Disciplina"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);

        for (Disciplina disciplina : disciplinas) {
            Object[] row = {disciplina.getCodigo(), disciplina.getNome()};
            tableModel.addRow(row);
        }

// Criar e configurar a tabela
        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setBorder(BorderFactory.createLineBorder(Color.decode("#3176FB"), 2));

// Configurar a fonte da tabela para 18 pontos
        Font tableFont = new Font("SansSerif", Font.PLAIN, 18);
        table.setFont(tableFont);
        table.getTableHeader().setFont(tableFont);

// Centralizar todas as colunas
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

// Criar e configurar o JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona algum espaçamento


        // Atualizar o painel direito
        rightPanel.removeAll();
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // Adiciona o painel inferior com os botões
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));

        StyleButton adicionarButton = new StyleButton("Adicionar Disciplina");
        adicionarButton.setPreferredSize(new Dimension(180, 50));
        adicionarButton.setFont(new Font("SansSerif", Font.PLAIN, 17));

        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarNovaDisciplina();
            }
        });
        bottomPanel.add(adicionarButton);

        StyleButton excluirButton = new StyleButton("Excluir Disciplina");
        excluirButton.setPreferredSize(new Dimension(180, 50));
        excluirButton.setFont(new Font("SansSerif", Font.PLAIN, 17));
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirDisciplinaSelecionada();
            }
        });
        bottomPanel.add(excluirButton);

        rightPanel.add(bottomPanel, BorderLayout.SOUTH);

        rightPanel.revalidate();
        rightPanel.repaint();
    }

    private void adicionarNovaDisciplina() {
        // Solicitar ao usuário o código e o nome da nova disciplina
        String codigo = JOptionPane.showInputDialog("Digite o código da nova disciplina:");
        String nome = JOptionPane.showInputDialog("Digite o nome da nova disciplina:");

        if (codigo != null && nome != null && !codigo.trim().isEmpty() && !nome.trim().isEmpty()) {
            try {
                // Verificar se o código da disciplina já existe
                if (verificarCodigoExistente(codigo)) {
                    JOptionPane.showMessageDialog(this, "Código de disciplina já existe. Escolha um código diferente.", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    // Adicionar nova disciplina
                    supervisor.adicionarDisciplina(codigo, nome);
                    mostrarDisciplina(); // Atualiza a lista após adicionar
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar disciplina: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Código e nome não podem estar vazios.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean verificarCodigoExistente(String codigo) throws SQLException {
        String sql = "SELECT COUNT(*) FROM disciplina WHERE codigo = ?";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0; // Retorna true se o código já existir
            }
        }
        return false; // Retorna false se o código não existir
    }

    private void excluirDisciplinaSelecionada() {
        // Implementar o código para excluir uma disciplina selecionada
        JTable table = (JTable) ((JScrollPane) rightPanel.getComponent(0)).getViewport().getView();
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String codigo = (String) table.getValueAt(selectedRow, 0);
            try {
                supervisor.excluirDisciplina(codigo);
                mostrarDisciplina(); // Atualiza a lista após excluir
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir disciplina: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma disciplina para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }


    // METODOS LOCAL
    private void mostrarLocal() throws SQLException {

        List<Local> locais = new ArrayList<>();

        String sql = "SELECT id, sala, capacidade FROM local order by sala asc ";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Local local = new Local();
                local.setId(rs.getInt("id"));
                local.setSala(rs.getString("sala"));
                local.setCapacidade(rs.getInt("capacidade"));
                locais.add(local);
            }
        }

        //Criar modelo da tabela
        String[] columnNames = {"Id", "Sala", "Capacidade"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        //percorrendo a lista da tabela e adicionando na tabela
        for (Local local : locais) {
            Object[] row = {local.getId(), local.getSala(), local.getCapacidade(),};
            tableModel.addRow(row);
        }

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        table.setBorder(BorderFactory.createLineBorder(Color.decode("#3176FB"), 2));

        Font tableFont = new Font("SansSerif", Font.PLAIN, 18);
        table.setFont(tableFont);
        table.getTableHeader().setFont(tableFont);

        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

        // Centralizar todas as colunas
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        for (int i = 0; i < table.getColumnCount(); i++) {
            table.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
        }

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Atualizar o painel direito
        rightPanel.removeAll();
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // Adiciona o painel inferior com os botões
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        StyleButton adicionarButton = new StyleButton("Adicionar Local");
        adicionarButton.setPreferredSize(new Dimension(180, 50));
        adicionarButton.setFont(new Font("SansSerif", Font.PLAIN, 17));
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarNovoLocal();
            }
        });
        bottomPanel.add(adicionarButton);

        StyleButton excluirButton = new StyleButton("Excluir Local");
        excluirButton.setPreferredSize(new Dimension(180, 50));
        excluirButton.setFont(new Font("SansSerif", Font.PLAIN, 17));
        excluirButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                excluirLocalSelecionada();
            }
        });
        bottomPanel.add(excluirButton);

        rightPanel.add(bottomPanel, BorderLayout.SOUTH);

        rightPanel.revalidate();
        rightPanel.repaint();

    }

    private void adicionarNovoLocal() {

        String sala = JOptionPane.showInputDialog("Digite a sala:");
        String capacidade = JOptionPane.showInputDialog("Digite a capacidade:");

        if (sala != null && capacidade != null && !sala.trim().isEmpty() && !capacidade.trim().isEmpty()) {
            try {
                if (verificarLocalExiste(sala)) {
                    JOptionPane.showMessageDialog(this, "Sala já existente", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    int cap = Integer.parseInt(capacidade);
                    supervisor.adicionarLocal(sala, cap);
                    mostrarLocal();
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao adicionar local: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Sala e capacidade não podem estar vazios.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private boolean verificarLocalExiste(String sala) throws SQLException {
        String sql = "Select count(*) from local where sala = ?";
        try (Connection conn = getConexao();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sala);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int count = rs.getInt(1);
                return count > 0;
            }
        }
        return false;

    }

    private void excluirLocalSelecionada() {
        JTable table = (JTable) ((JScrollPane) rightPanel.getComponent(0)).getViewport().getView();
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            Integer localId = (Integer) table.getValueAt(selectedRow, 0);

            try {
                supervisor.excluirLocal(localId);
                mostrarLocal();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir local: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um local para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }
