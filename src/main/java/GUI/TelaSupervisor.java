package GUI;

import DTO.Disciplina;
import DTO.Supervisor;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static DAO.conexao.getConexao;

public class TelaSupervisor extends BasePanel {
    private ScreenManager screenManager;
    private Supervisor supervisor;
    private JPanel rightPanel;

    public TelaSupervisor(ScreenManager screenManager, Supervisor supervisor) {
        super();
        this.screenManager = screenManager;
        this.supervisor = supervisor;
        setLayout(new BorderLayout());
        criarPainelSupervisor();
    }

    public void criarPainelSupervisor() {
        // Painel esquerdo
        JPanel leftPanel = new JPanel();
        leftPanel.setLayout(new BorderLayout()); // Usar BorderLayout para facilitar o posicionamento

        // Painel para imagem e nome
        JPanel imageNamePanel = new JPanel();
        imageNamePanel.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10)); // Ajustar espaçamento

        // Adicionando a imagem e nome do supervisor
        JLabel imageLabel = new JLabel(redimensionarImagem("Img/user-286.png", 60, 60)); // Aumentar tamanho da imagem
        JLabel nameLabel = new JLabel(supervisor.getNome());
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18)); // Aumentar tamanho da fonte

        imageNamePanel.add(imageLabel);
        imageNamePanel.add(nameLabel);

        // Adicionando o painel de imagem e nome ao painel esquerdo
        leftPanel.add(imageNamePanel, BorderLayout.NORTH);

        // Painel para os botões
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.Y_AXIS)); // BoxLayout para lista vertical

// Adicionando os botões
        StyleButton monitoriasButton = new StyleButton("Monitorias");
        StyleButton monitoresButton = new StyleButton("Monitores");
        StyleButton disciplinasButton = new StyleButton("Disciplinas");
        StyleButton horariosButton = new StyleButton("Horários");

// Configurar o tamanho dos botões para preencher a largura
        monitoriasButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, monitoriasButton.getPreferredSize().height));
        monitoresButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, monitoresButton.getPreferredSize().height));
        disciplinasButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, disciplinasButton.getPreferredSize().height));
        horariosButton.setMaximumSize(new Dimension(Integer.MAX_VALUE, horariosButton.getPreferredSize().height));

// Adicionando botões ao painel
        buttonsPanel.add(monitoriasButton);
        buttonsPanel.add(Box.createVerticalStrut(10)); // Espaçamento entre os botões
        buttonsPanel.add(monitoresButton);
        buttonsPanel.add(Box.createVerticalStrut(10)); // Espaçamento entre os botões
        buttonsPanel.add(disciplinasButton);
        buttonsPanel.add(Box.createVerticalStrut(10)); // Espaçamento entre os botões
        buttonsPanel.add(horariosButton);

// Adicionando o painel de botões ao painel esquerdo
        leftPanel.add(buttonsPanel, BorderLayout.CENTER);


        // Configurar ação dos botões
        monitoriasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMonitorias();
            }
        });

        monitoresButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarMonitores();
            }
        });

        disciplinasButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    atualizarDisciplinas();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        horariosButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarHorarios();
            }
        });

        // Adicionando componentes ao painel de botões
        buttonsPanel.add(monitoriasButton);
        buttonsPanel.add(Box.createVerticalStrut(10)); // Espaçamento
        buttonsPanel.add(monitoresButton);
        buttonsPanel.add(Box.createVerticalStrut(10)); // Espaçamento
        buttonsPanel.add(disciplinasButton);
        buttonsPanel.add(Box.createVerticalStrut(10)); // Espaçamento
        buttonsPanel.add(horariosButton);

        // Adicionando o painel de botões ao painel esquerdo
        leftPanel.add(buttonsPanel, BorderLayout.CENTER);

        // Painel direito
        rightPanel = new JPanel();
        rightPanel.setLayout(new BorderLayout());

        // Adicionar o painel direito ao painel principal
        add(leftPanel, BorderLayout.WEST);
        add(rightPanel, BorderLayout.CENTER);
    }

    private ImageIcon redimensionarImagem(String caminhoImagem, int largura, int altura) {
        ImageIcon icon = new ImageIcon(caminhoImagem);
        Image image = icon.getImage();
        Image newImage = image.getScaledInstance(largura, altura, Image.SCALE_SMOOTH);
        return new ImageIcon(newImage);
    }

    private void mostrarMonitorias() {
        // Implementar o código para mostrar monitorias
    }

    private void mostrarMonitores() {
        // Implementar o código para mostrar monitores
    }

    private void atualizarDisciplinas() throws SQLException {
        // Consultar as disciplinas do banco de dados
        List<Disciplina> disciplinas = new ArrayList<>();
        String sql = "SELECT codigo, nome FROM disciplina";
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

// Centralizar o texto apenas na coluna "Código" e ajustar a largura da coluna
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

// Aplicar o renderer apenas na coluna "Código"
        table.getColumnModel().getColumn(0).setCellRenderer(centerRenderer);

// Ajustar a largura da coluna "Código" para se adequar ao conteúdo máximo
        table.getColumnModel().getColumn(0).setPreferredWidth(5); // Ajuste o valor conforme necessário

// Manter o alinhamento padrão para a coluna "Disciplina"
        DefaultTableCellRenderer leftRenderer = new DefaultTableCellRenderer();
        leftRenderer.setHorizontalAlignment(SwingConstants.LEFT);
        table.getColumnModel().getColumn(1).setCellRenderer(leftRenderer);

// Criar e configurar o JScrollPane
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Adiciona algum espaçamento


        // Atualizar o painel direito
        rightPanel.removeAll();
        rightPanel.add(scrollPane, BorderLayout.CENTER);

        // Adiciona o painel inferior com os botões
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout(FlowLayout.RIGHT));

        JButton adicionarButton = new JButton("Adicionar Disciplina");
        adicionarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                adicionarNovaDisciplina();
            }
        });
        bottomPanel.add(adicionarButton);

        JButton excluirButton = new JButton("Excluir Disciplina");
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
                    atualizarDisciplinas(); // Atualiza a lista após adicionar
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
                atualizarDisciplinas(); // Atualiza a lista após excluir
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir disciplina: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione uma disciplina para excluir.", "Aviso", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void mostrarHorarios() {
        // Implementar o código para mostrar horários
    }

//    // Método principal para testes
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Tela do Supervisor");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(800, 600);
//        frame.setLayout(new BorderLayout());
//
//        // Supondo que você tenha um objeto Supervisor
//        Supervisor supervisor = new Supervisor("12345", "João da Silva", "joao@example.com", "senha123");
//
//        TelaSupervisor telaSupervisor = new TelaSupervisor(null, supervisor);
//        frame.add(telaSupervisor, BorderLayout.CENTER);
//
//        frame.setVisible(true);
//    }
}
