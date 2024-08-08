package controller;

import model.Supervisor;
import view.PrincipalView;
import view.SupervisorView;

import javax.swing.*;

public class AppController {

    public static void main(String[] args) {
        PrincipalView principalView = new PrincipalView();
        PrincipalController principalController = new PrincipalController(principalView);
        //estrutura de testes para tela de supervisor
//        Supervisor Alex = new Supervisor("789456123100", "Alex", "alex@gmail.com", "123456");
//        PrincipalController pcontroller = new PrincipalController(new PrincipalView());
//        SupervisorView supervisorView = new SupervisorView(Alex);
//        SupervisorController supervisorController = new SupervisorController(supervisorView, pcontroller );
// -----------------------------------------------------------
        // Configura a janela principal
        JFrame mainFrame = new JFrame("Sistema Monitoria");
        mainFrame.setTitle("Sistema Monitoria"); // Configura o título da janela
        mainFrame.setSize(1080, 720); // Configura o tamanho da janela
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Fecha a aplicação
        mainFrame.setLocationRelativeTo(null);// Centraliza a janela
//        mainFrame.add(supervisorView);
        mainFrame.add(principalView);
        mainFrame.setVisible(true);
    }
}
