package src;

import src.component.base.Panel;
import src.service.animation.Animation;
import src.service.animation.Location;
import src.service.animation.PanelTransform;

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
        panel2 = new Panel(500, 300, 200, 100, new Color(166, 243, 169), "Bris");
        panel = new Panel(100, 100, 200, 100, new Color(218, 121, 121), "Helloooo");
        add(panel);
        add(panel2);
        Animation.transform(panel,100, 100, 1000, 600, 2000, 1000);
        setVisible(true);
    }

}
