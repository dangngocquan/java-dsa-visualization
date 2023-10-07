package src.components.components.algorithms.sort.viewcontroller;

import src.App;
import src.Config;
import src.components.base.Button;
import src.components.base.Panel;
import src.components.components.algorithms.sort.SortAlgorithmScreen;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;

public class ViewController extends Panel {
    public static Button[] buttons;

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
                initialX, tempY, buttonWidth, buttonHeight, "Start sort"
        );

        add(buttons[0]);
        add(buttons[1]);
    }

    public void addActionListenerForButtons() {
        // Back
        buttons[0].addActionListener(e -> {
            SortAlgorithmScreen sortAlgorithmScreen =
                    (SortAlgorithmScreen) getApp().getScreens().get("SortAlgorithmScreen");
            getApp().getScreens().get("MainAlgorithmsScreen").setHidden(false);
            sortAlgorithmScreen.setHidden(true);
        });

        buttons[1].addActionListener(e -> {
            SortAlgorithmScreen sortAlgorithmScreen =
                    (SortAlgorithmScreen) getApp().getScreens().get("SortAlgorithmScreen");
            if (SortAlgorithmScreen.sortAnimation == null) {
                sortAlgorithmScreen.startSort();
            } else {
                if (SortAlgorithmScreen.sortAnimation.isRunning()) {
                    sortAlgorithmScreen.pauseSort();
                } else if (SortAlgorithmScreen.sortAnimation.isPaused()) {
                    sortAlgorithmScreen.continueSort();
                }
            }
        });
    }

    public void startSort() {
        buttons[1].setText("Pause sort");
    }

    public void pauseSort() {
        buttons[1].setText("Continue sort");
    }

    public void continueSort() {
        buttons[1].setText("Pause sort");
    }

    public void endSort() {
        buttons[1].setText("Start sort");
    }

    public App getApp() {
        return (App) (Service.getFrame(this));
    }

    public void setEnabledAllButtons(boolean enable) {
        buttons[0].setEnabledButton(enable);
        buttons[1].setEnabledButton(enable);
    }

}
