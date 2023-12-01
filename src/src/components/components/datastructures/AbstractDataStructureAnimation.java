package src.components.components.datastructures;

import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstractDataStructureAnimation extends TimerTask {
    protected Timer timer;
    protected AbstractDataStructureScreen rootScreen;
    protected int period;
    protected int animationStep;
    protected AbstractDataStructureAnimation nextAnimation;

    public AbstractDataStructureAnimation(
            AbstractDataStructureScreen rootScreen, int period,
            AbstractDataStructureAnimation nextAnimation) {
        this.rootScreen = rootScreen;
        this.period = period;
        this.nextAnimation = nextAnimation;
        timer = new Timer();
    }

    public void start() {
        rootScreen.setDescription("");
        timer.schedule(this, 0, period);
    }

    public void end() {
        timer.cancel();
        timer.purge();
        if (nextAnimation == null) {
            rootScreen.endAction();
        } else {
            nextAnimation.start();
        }
    }
}
