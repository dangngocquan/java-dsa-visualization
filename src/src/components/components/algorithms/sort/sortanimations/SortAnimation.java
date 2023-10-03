package src.components.components.algorithms.sort.sortanimations;

import src.components.components.algorithms.sort.SortAlgorithmScreen;
import src.components.components.algorithms.sort.viewcontroller.ViewController;
import src.components.components.algorithms.sort.viewsort.Bar;
import src.components.components.algorithms.sort.viewsort.ViewSort;

import java.util.Timer;
import java.util.TimerTask;

public abstract class SortAnimation extends TimerTask {
    public static final int IS_NOT_START = 0;
    public static final int IS_PAUSED = 1;
    public static final int IS_RUNNING = 2;
    public static final int IS_ENDED = 3;

    protected int status;
    protected Timer timer;
    protected int period;
    protected int animationStep = 0;
    protected SortAlgorithmScreen sortAlgorithmScreen;
    protected Bar[] bars;

    public SortAnimation(SortAlgorithmScreen sortAlgorithmScreen, Bar[] bars, Timer timer, int period) {
        this.timer = timer;
        this.period = period;
        this.sortAlgorithmScreen = sortAlgorithmScreen;
        this.bars = bars;
        this.animationStep = 0;
        this.status = IS_NOT_START;
    }

    public void start() {
        status = IS_RUNNING;
        timer.schedule(this, 0, period);
    }

    @Override
    public abstract void run();

    public void pause() {
        status = IS_PAUSED;
    };

    public void continueRun() {
        status = IS_RUNNING;
    }

    public void end() {
        status = IS_ENDED;
        sortAlgorithmScreen.endSort();
    }

    public boolean isNotStart() {
        return status == 0;
    }

    public boolean isRunning() {
        return status == 2;
    }

    public boolean isPaused() {
        return status == 1;
    }

    public boolean isEnded() {
        return status == 3;
    }
}
