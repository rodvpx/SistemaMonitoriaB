package controller;

import view.PrincipalView;

import javax.swing.*;
import java.awt.*;

public class AppController {

    public static void main(String[] args) {

        // Cria a visualização principal
        PrincipalView principalView = new PrincipalView();

        // Cria a janela principal
        JFrame mainFrame = new JFrame("Sistema Monitoria");
        mainFrame.setSize(1080, 720);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);

        // Carrega a imagem do ícone do classpath
        ImageIcon icon = new ImageIcon(AppController.class.getResource("/Img/icon.png"));
            Image image = icon.getImage();
            mainFrame.setIconImage(image);
            
        // Adiciona a visualização principal à janela
        mainFrame.add(principalView);

        // Configura o controlador
        PrincipalController principalController = new PrincipalController(principalView, mainFrame);

        // Exibe a janela
        mainFrame.setVisible(true);
    }
}
