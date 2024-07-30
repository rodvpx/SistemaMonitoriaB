package view;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ScreenManager {
    private JFrame frame;
    private JPanel currentScreen;

    public ScreenManager(JFrame frame) {
        this.frame = frame;
    }

    public void showScreen(JPanel newScreen) {
        if (currentScreen != null) {
            frame.remove(currentScreen);
        }
        currentScreen = newScreen;
        frame.add(newScreen);
        frame.revalidate();
        frame.repaint();
    }
}
