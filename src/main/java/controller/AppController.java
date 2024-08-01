package controller;

import view.PrincipalView;

import javax.swing.*;

public class AppController {

    public static void main(String[] args) {
        PrincipalView principalView = new PrincipalView();
        PrincipalController principalController = new PrincipalController(principalView);

        // Configura a janela principal
        JFrame mainFrame = new JFrame("Sistema Monitoria");
        mainFrame.setTitle("Sistema Monitoria"); // Configura o título da janela
        mainFrame.setSize(800, 600); // Configura o tamanho da janela
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação
        mainFrame.setLocationRelativeTo(null); // Centraliza a janela
        mainFrame.add(principalView);
        mainFrame.setVisible(true);
    }
}
