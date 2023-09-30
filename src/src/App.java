package src;

import src.components.base.Panel;
import src.services.animation.Animation;

import javax.swing.*;
import java.awt.*;

public class App extends JFrame {
    public JPanel panel;
    public JPanel panel2;
    public App() {
        super("Java Algorithm Visualizer");
        setSize(Config.DEVICE_WIDTH, Config.DEVICE_HEIGHT);
        setResizable(false);
        setAlwaysOnTop(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        getContentPane().setBackground(Config.BACKGROUND_COLOR_APP);
        setFont(Config.ARIAL_BOLD_12);

        setVisible(true);
    }

}
