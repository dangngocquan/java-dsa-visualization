package src.services.serviceanimations;

import src.components.base.Panel;

import java.util.Timer;
import java.util.TimerTask;

public class TransformArrowOfPanelNode {
    private Timer timer;
    private final Panel rootScreen;
    private int[] refLocation;
    private final int[] start;
    private final int[] end;
    private final int translateX1;
    private final int translateY1;
    private final int translateX2;
    private final int translateY2;


    private final int delay;
    private final int duration;
    private long msStart;


    public TransformArrowOfPanelNode(
            Panel rootScreen,
            int[] start, int[] end, int delay, int duration) {
        this.rootScreen = rootScreen;
        this.refLocation = start;
        this.start = start.clone();
        this.end = end;
        this.translateX1 = end[0] - start[0];
        this.translateY1 = end[1] - start[1];
        this.translateX2 = end[2] - start[2];
        this.translateY2 = end[3] - start[3];
        this.delay = delay;
        this.duration = duration;
    }

    public void start() {
        timer = new Timer();
        msStart = System.currentTimeMillis() + delay;
        timer.schedule(new TaskAnimation(), delay, 1);
    }

    public void stop() {
        refLocation[0] = end[0];
        refLocation[1] = end[1];
        refLocation[2] = end[2];
        refLocation[3] = end[3];
        rootScreen.repaint();
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
            refLocation[0] = (int) (start[0] + process * translateX1);
            refLocation[1] = (int) (start[1] + process * translateY1);
            refLocation[2] = (int) (start[2] + process * translateX2);
            refLocation[3] = (int) (start[3] + process * translateY2);
            rootScreen.repaint();
        }
    }
}
