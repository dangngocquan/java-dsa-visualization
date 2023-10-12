package src.services.serviceanimations;

import src.components.base.Panel;
import src.components.components.datastructures.AbstractPanelDataStructureNode;

import java.util.Timer;
import java.util.TimerTask;

public class Transform {
    private Timer timer;
    private final Panel panel;
    private final int translateX;
    private final int translateY;
    private final int delay;
    private final int duration;
    private final Location locationStart;
    private long msStart;


    public Transform(Panel panel, Location start, int translateX, int translateY, int delay, int duration) {
        this.panel = panel;
        this.translateX = translateX;
        this.translateY = translateY;
        this.delay = delay;
        this.duration = duration;
        this.locationStart = start;
    }

    public void start() {
        timer = new Timer();
        msStart = System.currentTimeMillis() + delay;
        timer.schedule(new TaskAnimation(), delay, 1);
    }

    public void stop() {
        panel.setXY(locationStart.getX() + translateX, locationStart.getY() + translateY);
        if (panel instanceof AbstractPanelDataStructureNode) {
            panel.getParent().repaint();
        }
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
                return;
            }
            double x = locationStart.getX() + process * translateX;
            double y = locationStart.getY() + process * translateY;
            panel.setXY(
                    (int) Math.round(x),
                    (int) Math.round(y)
            );
            if (panel instanceof AbstractPanelDataStructureNode) {
                panel.getParent().repaint();
            }
        }
    }
}
