package GUI;

import DTO.Supervisor;

import javax.swing.*;

import java.awt.*;

import static GUI.AppGUI.mostrarMensagem;

public class TelaSupervisor extends BasePanel{

    private ScreenManager screenManager;
    public TelaSupervisor(ScreenManager screenManager) {
        super();
        this.screenManager = screenManager;
        setLayout(new GridBagLayout());
        supervisoraqui();
    }

    public void supervisoraqui(){
        mostrarMensagem("Tela supervisor","", 0);
    }
}
