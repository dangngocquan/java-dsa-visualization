package src.components.components;

import src.App;
import src.Config;
import src.components.base.Button;
import src.components.base.Panel;
import src.services.animation.Animation;
import src.services.animation.Location;
import src.services.services.Service;


public abstract class AbstractScreen extends Panel {
    protected Button[] buttons;
    protected AbstractScreen[] screens;

    public AbstractScreen() {
        super(0, 0, Config.WIDTH, Config.HEIGHT, Config.BACKGROUND_COLOR_APP, null, "", 0);
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
                    delay, duration
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
                    delay, duration
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
