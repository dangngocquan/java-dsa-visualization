package src.services.animation;


import src.components.base.Panel;

import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TransitionColor {
    private Timer timer;
    private Component component;
    private Color initialColor;
    private Color targetColor;
    private int absR, absG, absB;
    private int delay;
    private int duration;
    private long msStart;
    private double process = 0.0;


    public TransitionColor(Component component, Color initialColor, Color targetColor, int delay, int duration) {
        this.component = component;
        this.initialColor = initialColor;
        this.targetColor = targetColor;
        this.absR = targetColor.getRed() - initialColor.getRed();
        this.absG = targetColor.getGreen() - initialColor.getGreen();
        this.absB = targetColor.getBlue() - initialColor.getBlue();
        this.delay = delay;
        this.duration = duration;
    }

    public void start() {
        timer = new Timer();
        msStart = System.currentTimeMillis() + delay;
        timer.schedule(new TaskAnimation(), delay, 1);
    }

    public void stop() {
        timer.cancel();
        timer.purge();
        if (component instanceof Panel) {
            ((Panel) component).setBackgroundColor(targetColor);
        } else {
            component.setBackground(targetColor);
        }
    }

    private class TaskAnimation extends TimerTask {
        @Override
        public void run() {
            long temp = System.currentTimeMillis() - msStart;
            process = temp * 1.0 / duration;
            if (process >= 1f) {
                stop();
                return;
            }
            if (component instanceof Panel) {
                ((Panel) component).setBackgroundColor(
                        new Color(
                                initialColor.getRed() + (int) (absR * process),
                                initialColor.getGreen() + (int) (absG * process),
                                initialColor.getBlue() + (int) (absB * process)
                        )
                );
            } else {
                component.setBackground(
                        new Color(
                                initialColor.getRed() + (int) (absR * process),
                                initialColor.getGreen() + (int) (absG * process),
                                initialColor.getBlue() + (int) (absB * process)
                        )
                );
            }
        }
    }
}
