package src.components.components.algorithms.sort;

import src.App;
import src.Config;
import src.components.base.Button;
import src.components.base.Panel;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;

public class ViewController extends Panel {
    private Button[] buttons;
    public ViewController(int x, int y, int width, int height, Color backgroundColor, ImageIcon backgroundImage, String text, int shadowSize) {
        super(x, y, width, height, backgroundColor, backgroundImage, text, shadowSize);
        addButtons();
        addActionListenerForButtons();
        repaint();
    }

    public void addButtons() {
        buttons = new Button[2];

        int initialX = 25;
        int initialY = 50;
        int tempY = initialY;
        int gapY = 40;
        int buttonWidth = 250;
        int buttonHeight = 50;

        buttons[0] = new Button(
                initialX, tempY, buttonWidth, buttonHeight, "Back"
        );
        tempY += buttonHeight + gapY;

        buttons[1] = new Button(
                initialX, tempY, buttonWidth, buttonHeight, "Run sort"
        );

        add(buttons[0]);
        add(buttons[1]);
    }

    public void addActionListenerForButtons() {
        // Back
        buttons[0].addActionListener(e -> {
            getApp().getScreens().get("MainAlgorithmsScreen").setHidden(false);
            getApp().getScreens().get("SortAlgorithmScreen").setHidden(true);
        });
    }

    public App getApp() {
        return (App) (Service.getFrame(this));
    }

}
