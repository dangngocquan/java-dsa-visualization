package src.component.base;

import src.Config;

import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    private int x;
    private int y;
    private int width;
    private int height;
    private String text;
    private Color backgroundColor;

    public Panel(int x, int y, int width, int height, Color backgroundColor, String text) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.backgroundColor = backgroundColor;

        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);
        setFont(Config.ARIAL_BOLD_12);

        setVisible(true);
    }

    public Panel(int x, int y, int width, int height, Color backgroundColor) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = "";
        this.backgroundColor = backgroundColor;

        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);
        setFont(Config.ARIAL_BOLD_12);

        setVisible(true);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setColor(backgroundColor);
        g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                RenderingHints.	VALUE_COLOR_RENDER_QUALITY);
        g2d.fillRect(0, 0, getWidth(), getHeight());


        g2d.setColor(Config.COLOR_TEXT);
        g2d.setFont(Config.ARIAL_BOLD_12);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        FontMetrics fontMetric = g2d.getFontMetrics();
        int x0 = (getWidth() - fontMetric.stringWidth(text)) / 2;
        int y0 = ((getHeight() - fontMetric.getHeight()) / 2) + fontMetric.getAscent();
        g2d.drawString(text, x0, y0);
        g2d.dispose();
    }
}
