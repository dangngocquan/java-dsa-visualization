package src.components.components;

import src.App;
import src.components.base.Button;
import src.components.base.Panel;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractScreen extends Panel {
    protected Button[] buttons;
    protected AbstractScreen[] screens;

    public AbstractScreen(
            int x, int y, int width, int height,
            Color backgroundColor,
            ImageIcon backgroundImage,
            String text) {
        super(x, y, width, height, backgroundColor, backgroundImage, text);
        addButtons();
        createDefaultScreens();
        addActionListenerForButtons();
        repaint();
    }

    public void setHidden(boolean isHidden) {
        setVisible(!isHidden);
    }

    public App getApp() {
        return (App) (Service.getFrame(this));
    }

    public abstract void addButtons();

    public abstract void createDefaultScreens();

    public abstract void addActionListenerForButtons();
}
