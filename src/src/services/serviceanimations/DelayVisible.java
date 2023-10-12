package src.services.serviceanimations;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

public class DelayVisible {
    private Timer timer;
    private final Component component;
    private final boolean visible;
    private final int delay;
    private final int duration;
    private long msStart;


    public DelayVisible(Component component, boolean visible, int delay, int duration) {
        this.component = component;
        this.visible = visible;
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
        component.setVisible(visible);
        timer.cancel();
        timer.purge();
    }

    private class TaskAnimation extends TimerTask {
        @Override
        public void run() {
            long temp = System.currentTimeMillis() - msStart;
            double process = temp * 1.0 / duration;
            if (process >= 1f) {
                stop();
            }
        }
    }
}
