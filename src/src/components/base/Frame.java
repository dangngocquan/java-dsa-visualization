package src.components.base;

import src.Config;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
    public Frame(String title, int x, int y, int width, int height, Color backgroundColor, Font font) {
        super(title);
        setSize(width, height);
        setResizable(false);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(backgroundColor);
        setFont(font);
        setLocation(x, y);
        setVisible(true);
    }
}
