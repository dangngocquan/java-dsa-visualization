package src.components.base;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class Panel extends JPanel {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected String text;
    protected Color backgroundColor;
    protected ImageIcon backgroundImage;
    protected int shadowSize;
    protected Font font;
    protected int borderWidth;
    protected Color borderColor;

    public Panel(
            int x, int y, int width, int height,
            Color backgroundColor, ImageIcon backgroundImage,
            String text,
            int shadowSize) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.backgroundColor = backgroundColor;
        this.backgroundImage = backgroundImage;
        this.shadowSize = shadowSize;
        this.borderWidth = 0;
        this.borderColor = Color.BLACK;
        Border border = BorderFactory.createEmptyBorder(0, 0, shadowSize, shadowSize);
        this.setBorder(BorderFactory.createCompoundBorder(this.getBorder(), border));
        this.setLayout(new BorderLayout());

        setLayout(null);
        setSize(width + shadowSize, height + shadowSize);
        setBounds(x, y, width + shadowSize, height + shadowSize);
        setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
        setIgnoreRepaint(true);
        setBackground(new Color(0, 0, 0, 0));
        setVisible(true);
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    public int getWidthPanel() {
        return width;
    }

    public int getHeightPanel() {
        return height;
    }

    public String getText() {
        return text;
    }

    public Color getBackgroundColor() {
        return backgroundColor;
    }

    public ImageIcon getBackgroundImage() {
        return backgroundImage;
    }

    public void setX(int x) {
        this.x = x;
        setBounds(x, y, width + shadowSize, height + shadowSize);
    }

    public void setY(int y) {
        this.y = y;
        setBounds(x, y, width + shadowSize, height + shadowSize);
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(x, y, width + shadowSize, height + shadowSize);
    }

    public void setText(String text) {
        this.text = text;
        repaint();
    }

    public void setFont(Font font) {
        this.font = font;
        repaint();
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
        repaint();
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
        repaint();
    }

    public void setBackgroundColor(Color backgroundColor) {
        this.backgroundColor = backgroundColor;
        setBackground(backgroundColor);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        int shade = 0;
        int topOpacity = 80;

        for (int i = 0; i < shadowSize; i++) {
            g2d.setColor(new Color(shade, shade, shade, ((topOpacity / shadowSize) * (shadowSize - i))));
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                    RenderingHints.	VALUE_COLOR_RENDER_QUALITY);
//            g2d.drawRect(i, i, width, height);
            g2d.drawLine(i+width, i, i+width, i + height);
            g2d.drawLine(i, i + height, i+width, i + height);
        }

        if (borderWidth > 0) {
            g2d.setColor(borderColor);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                    RenderingHints.	VALUE_COLOR_RENDER_QUALITY);
            g2d.fillRect(0, 0, width, height);
        }

        if (backgroundColor != null) {
            g2d.setColor(backgroundColor);
            g2d.setRenderingHint(RenderingHints.KEY_COLOR_RENDERING,
                    RenderingHints.	VALUE_COLOR_RENDER_QUALITY);
            g2d.fillRect(borderWidth, borderWidth, width - 2*borderWidth, height - 2*borderWidth);
        }

        if (backgroundImage != null) {
            g2d.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
            g2d.drawImage(backgroundImage.getImage(), 1, 1, width-2, height-2, null);
        }

        g2d.setColor(new Color(50, 50, 50));
        g2d.setFont(font);
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        FontMetrics fontMetric = g2d.getFontMetrics();
        int x0 = (width - fontMetric.stringWidth(text)) / 2;
        int y0 = ((height - fontMetric.getHeight()) / 2) + fontMetric.getAscent();
        g2d.drawString(text, x0, y0);
        g2d.dispose();
    }
}
