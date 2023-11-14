package src.components.components.algorithms.search;

import src.components.components.algorithms.AbstractAlgorithmAnimation;

public abstract class AbstractSearchAlgorithmAnimation extends AbstractAlgorithmAnimation {
    public AbstractSearchAlgorithmAnimation(
            AbstractSearchAlgorithmScreen rootScreen, int period) {
        super(rootScreen, period, null);
    }

    public AbstractSearchAlgorithmScreen getRootScreen() {
        return (AbstractSearchAlgorithmScreen) rootScreen;
    }

    @Override
    public void end() {

    }

    public void end(int type) {
        timer.cancel();
        timer.purge();
        getRootScreen().endSearch(type);
    }
}
