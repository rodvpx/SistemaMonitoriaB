package GUI;

import javax.swing.*;

public class AppGUI extends JFrame {
    private ScreenManager screenManager;

    public AppGUI() {
        setTitle("Sistema de Monitoria");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        screenManager = new ScreenManager(this);

        // Mostrar a tela inicial
        TelaInicial telaInicial = new TelaInicial(screenManager);
        screenManager.showScreen(telaInicial.criarPainelInicial());

        setVisible(true);
    }

    public static void main(String[] args) {
        // Criação da janela na thread de evento da GUI
        javax.swing.SwingUtilities.invokeLater(() -> new AppGUI());
    }

    public static void mostrarMensagem(String mensagem, String titulo, int tipo) {
        JOptionPane.showMessageDialog(null, mensagem, titulo, tipo);
    }
}
