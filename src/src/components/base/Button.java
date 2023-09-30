package src.components.base;

import javax.swing.*;
import javax.swing.plaf.basic.BasicButtonUI;
import java.awt.*;

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
//        setBorderPainted(false);
        setFont(new Font("Arial", Font.BOLD, 18));
        setUI(new BasicButtonUI());
        setVisible(true);
    }
}
