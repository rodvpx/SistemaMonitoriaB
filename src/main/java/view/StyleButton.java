package view;

import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class StyleButton extends JButton {

    private static final int BORDER_THICKNESS = 3; // Espessura da borda
    private static final int BORDER_RADIUS = 32; // Raio das bordas arredondadas
    private Color normalColor = Color.WHITE;
    private Color hoverColor = Color.decode("#C6E2FF");

    public StyleButton(String txt) {
        super(txt);
        configurarEstilo();
        adicionarEfeitosMouse();
    }

    private void configurarEstilo() {
        setBackground(Color.WHITE);
        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.PLAIN, 20));
        setFocusPainted(false);
        setPreferredSize(new Dimension(150, 50));
        setContentAreaFilled(false);
        setBorderPainted(false);
    }

    private void adicionarEfeitosMouse() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverColor);
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(normalColor);
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Pintar o fundo do botão
        if (getModel().isArmed()) {
            g2.setColor(getBackground().darker());
        } else {
            g2.setColor(getBackground());
        }
        g2.fillRoundRect(0, 0, width - 1, height - 1, BORDER_RADIUS, BORDER_RADIUS);

        // Desenhar a borda
        g2.setColor(Color.decode("#3176FB")); // Cor da borda
        g2.setStroke(new BasicStroke(BORDER_THICKNESS)); // Define a espessura da borda
        g2.drawRoundRect(BORDER_THICKNESS / 3, BORDER_THICKNESS / 3, width - BORDER_THICKNESS, height - BORDER_THICKNESS, BORDER_RADIUS, BORDER_RADIUS);

        g2.dispose();

        super.paintComponent(g);
    }

}
