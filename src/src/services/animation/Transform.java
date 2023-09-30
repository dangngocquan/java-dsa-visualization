package src.services.animation;

import src.components.base.Panel;
import java.util.Timer;
import java.util.TimerTask;

public class Transform {
    private Timer timer;
    private Panel panel;
    private int translateX;
    private int translateY;
    private int delay;
    private int duration;
    private int zOrder;
    private Location locationStart;
    private long msStart;
    private double process = 0.0;


    public Transform(Panel panel, Location start, int translateX, int translateY, int delay, int duration, int zOrder) {
        this.panel = panel;
        this.translateX = translateX;
        this.translateY = translateY;
        this.delay = delay;
        this.duration = duration;
        this.zOrder = zOrder;
        this.locationStart = start;
    }

    public void start() {
        timer = new Timer();
        msStart = System.currentTimeMillis() + delay;
        timer.schedule(new TaskAnimation(), delay, 1);
    }

    public void stop() {
        panel.getParent().setComponentZOrder(panel, zOrder);
        panel.setXY(locationStart.getX() + translateX, locationStart.getY() + translateY);
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
            double x = locationStart.getX() + process * translateX;
            double y = locationStart.getY() + process * translateY;
            panel.setXY(
                    (int) Math.round(x),
                    (int) Math.round(y)
            );
        }
    }
}
