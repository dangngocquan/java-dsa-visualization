package src.components.components.datastructures.list.arraylist.actionanimation;

import src.components.components.datastructures.list.abstractlistscreen.AbstractListScreen;

import java.util.Timer;
import java.util.TimerTask;

public abstract class ArrayListAnimation extends TimerTask {
    protected Timer timer;
    protected AbstractListScreen rootScreen;
    protected int period;
    protected int animationStep;

    public ArrayListAnimation(AbstractListScreen rootScreen, int period) {
        this.rootScreen = rootScreen;
        this.period = period;
        timer = new Timer();
    }

    public void start() {
        timer.schedule(this, 0, period);
    }

    public void end() {
        timer.cancel();
        timer.purge();
        rootScreen.endAction();
    }
}
