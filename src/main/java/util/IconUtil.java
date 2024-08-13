package util;

import javax.swing.*;
import java.awt.*;

public class IconUtil {
    private static final ImageIcon ICON = new ImageIcon(IconUtil.class.getResource("/Img/icon.png"));

    public static void setIcon(JFrame frame) {
        if (ICON.getImageLoadStatus() == MediaTracker.COMPLETE) {
            Image image = ICON.getImage();
            frame.setIconImage(image);
        } else {
            System.err.println("Erro ao carregar a imagem do ícone.");
        }
    }
}
