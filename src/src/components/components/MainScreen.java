package src.components.components;

import src.App;
import src.Config;
import src.components.base.Button;
import src.components.base.Panel;
import src.components.components.screenalgorithms.MainAlgorithmsScreen;
import src.components.components.screendatastructures.MainDataStructuresScreen;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends Panel {
    private Button[] buttons;
    private Panel[] screens;
    public MainScreen(
            int x, int y, int width, int height,
            Color backgroundColor,
            ImageIcon backgroundImage,
            String text) {
        super(x, y, width, height, backgroundColor, backgroundImage, text);
        addButtons();
        createDefaultScreens();
        addActionListenerForButtons();
    }

    public void addButtons() {
        buttons = new Button[2];
        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapHeight = 40;
        buttons[0] = new Button(
                (Config.WIDTH - buttonWidth) / 2,
                (Config.HEIGHT - buttonHeight) / 2 - (gapHeight + buttonHeight),
                buttonWidth, buttonHeight,
                "Data Structure"
        );
        buttons[1] = new Button(
                buttons[0].getX(),
                buttons[0].getY() + buttons[0].getHeight() + gapHeight,
                buttonWidth, buttonHeight,
                "Algorithms"
        );

        add(buttons[0]);
        add(buttons[1]);
        repaint();
    }

    public void createDefaultScreens() {
        screens = new Panel[2];
    }

    public void setHidden(boolean isHidden) {
        setVisible(!isHidden);
    }

    public App getApp() {
        return (App) (Service.getFrame(this));
    }

    public void addActionListenerForButtons() {
        buttons[0].addActionListener(e -> {
            if (screens[0] == null) {
                screens[0] = new MainDataStructuresScreen(
                        0, 0, Config.WIDTH, Config.HEIGHT,
                        Config.BACKGROUND_COLOR_APP, null, ""
                );
                getApp().add(screens[0]);
            }
            setHidden(true);
            screens[0].setVisible(true);
        });

        buttons[1].addActionListener(e -> {
            if (screens[1] == null) {
                screens[1] = new MainAlgorithmsScreen(
                        0, 0, Config.WIDTH, Config.HEIGHT,
                        Config.BACKGROUND_COLOR_APP, null, ""
                );
                getApp().add(screens[1]);
            }
            setHidden(true);
            screens[1].setVisible(true);
        });
    }
}
