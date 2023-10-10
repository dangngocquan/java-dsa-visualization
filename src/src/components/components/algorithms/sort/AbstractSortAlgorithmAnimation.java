package src.components.components.algorithms.sort;

import src.components.components.algorithms.AbstractAlgorithmAnimation;

public abstract class AbstractSortAlgorithmAnimation extends AbstractAlgorithmAnimation {
    public AbstractSortAlgorithmAnimation(
            AbstractSortAlgorithmScreen rootScreen, int period) {
        super(rootScreen, period, null);
    }

    public AbstractSortAlgorithmScreen getRootScreen() {
        return (AbstractSortAlgorithmScreen) rootScreen;
    }

    @Override
    public void end() {
        timer.cancel();
        timer.purge();
        getRootScreen().endSort();
    }
}
