package src.service.animation;

import javax.swing.*;
import java.util.*;
import java.util.Timer;

public class PanelTransform {
    private Timer timer;
    private JPanel panel;
    private Location start;
    private Location end;
    private int delay;
    private int duration;
    private double x;
    private double y;
    private double dX;
    private double dY;


    public PanelTransform(JPanel panel, Location start, Location end, int delay, int duration) {
        this.panel = panel;
        this.start = start;
        this.end = end;
        this.delay = delay;
        this.duration = duration;
        this.dX = (end.getX() - start.getX()) * 1.0 / duration;
        this.dY = (end.getY() - start.getY()) * 1.0/ duration;
        panel.getParent().setComponentZOrder(panel, panel.getParent().getComponentCount()-1);

    }

    public void start() {
        timer = new Timer();
        x = start.getX();
        y = start.getY();
        timer.schedule(new TaskAnimation(), delay, 1);
    }

    public void stop() {
        timer.cancel();
        timer.purge();
    }

    private class TaskAnimation extends TimerTask {

        @Override
        public void run() {
            x += dX;
            y += dY;
            if (x > end.getX()) x = end.getX();
            if (y > end.getY()) y = end.getY();
            if (Double.compare(x, end.getX()) == 0 && Double.compare(y, end.getY()) == 0) {
                stop();
                return;
            }
            panel.setLocation(
                    (int) Math.round(x),
                    (int) Math.round(y));
        }
    }
}
