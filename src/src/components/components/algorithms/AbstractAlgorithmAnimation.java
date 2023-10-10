package src.components.components.algorithms;

import src.components.components.datastructures.AbstractDataStructureAnimation;

import java.util.Timer;
import java.util.TimerTask;

public abstract class AbstractAlgorithmAnimation extends TimerTask {
    protected Timer timer;
    protected AbstractAlgorithmScreen rootScreen;
    protected int period;
    protected int animationStep;
    protected AbstractDataStructureAnimation nextAnimation;
    protected int status = -1;

    public AbstractAlgorithmAnimation(
            AbstractAlgorithmScreen rootScreen, int period,
            AbstractDataStructureAnimation nextAnimation) {
        this.rootScreen = rootScreen;
        this.period = period;
        this.nextAnimation = nextAnimation;
        timer = new Timer();
    }

    public boolean isRunning() {
        return status == 1;
    }

    public boolean isPaused() {
        return status == 0;
    }

    public boolean isEnded() {
        return status == 2;
    }

    public void start() {
        timer.schedule(this, 0, period);
        status = 1;
    }

    public void pause() {
        status = 0;
    }

    public void continueRun() {
        status = 1;
    }

    public abstract void end();
}
