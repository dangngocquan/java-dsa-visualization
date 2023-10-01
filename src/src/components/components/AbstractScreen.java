package src.components.components;

import src.App;
import src.Config;
import src.components.base.Button;
import src.components.base.Panel;
import src.services.animation.Animation;
import src.services.animation.Location;
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
        super(x, y, width, height, backgroundColor, backgroundImage, text, 0);
        addButtons();
        createDefaultScreens();
        addActionListenerForButtons();
        setBackground(Config.BACKGROUND_COLOR_APP);
        repaint();
    }

    public void setHidden(boolean isHidden) {
        int delay = 0;
        int duration = 200;
        if (isHidden) {
            int duration0 = duration;
            Animation.translate(
                    this,
                    new Location(0, 0),
                    0, Config.HEIGHT,
                    delay, duration, 0
            );
            delay += duration0 + 10;
            Animation.delayVisible(this, false, delay, 10);
        } else {
            delay += duration + 10;
            Animation.delayVisible(this, true, delay, 10);
            delay += 10;
            Animation.translate(
                    this,
                    new Location(0, Config.HEIGHT),
                    0, -Config.HEIGHT,
                    delay, duration, 0
            );

        }
    }

    public App getApp() {
        return (App) (Service.getFrame(this));
    }

    public abstract void addButtons();

    public abstract void createDefaultScreens();

    public abstract void addActionListenerForButtons();
}
