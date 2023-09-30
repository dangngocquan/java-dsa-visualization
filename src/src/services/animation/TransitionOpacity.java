package src.services.animation;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class TransitionOpacity {
    private Timer timer;
    private Component component;
    private int initialOpacity;
    private int targetOpacity;
    private int absOpacity;
    private int r, g, b;
    private int delay;
    private int duration;
    private long msStart;
    private double process = 0.0;


    public TransitionOpacity(Component component, int initialOpacity, int targetOpacity, int delay, int duration) {
        this.component = component;
        this.initialOpacity = initialOpacity;
        this.targetOpacity = targetOpacity;
        this.absOpacity = targetOpacity - initialOpacity;
        this.r = component.getBackground().getRed();
        this.g = component.getBackground().getGreen();
        this.b = component.getBackground().getBlue();
        this.delay = delay;
        this.duration = duration;
        ((JPanel) component).setOpaque(false);
    }

    public void start() {
        timer = new Timer();
        msStart = System.currentTimeMillis() + delay;
        timer.schedule(new TaskAnimation(), delay, 1);
    }

    public void stop() {
        component.setBackground(new Color(r, g, b, targetOpacity));
        timer.cancel();
        timer.purge();
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
            component.setBackground(new Color(r, g, b, initialOpacity + (int) (absOpacity * process)));
        }
    }
}
