package controller;

import view.PrincipalView;

import javax.swing.*;

public class AppController {

    public static void main(String[] args) {

        PrincipalView principalView = new PrincipalView();
        JFrame mainFrame = new JFrame("Sistema Monitoria");
        mainFrame.setTitle("Sistema Monitoria");
        mainFrame.setSize(1080, 720);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.add(principalView);

        PrincipalController principalController = new PrincipalController(principalView, mainFrame);

        mainFrame.setVisible(true);
    }
}

