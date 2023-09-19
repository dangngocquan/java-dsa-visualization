package src.component.base;

import src.Config;

import javax.swing.*;

public class Button extends JButton {
    private int x;
    private int y;
    private int width;
    private int height;

    public Button(int x, int y, int width, int height, String text) {
        super(text);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

        setLayout(null);
        setSize(width, height);
        setBounds(x, y, width, height);
        setFocusPainted(false);
        setFont(Config.ARIAL_BOLD_12);

        setVisible(true);
    }
}
