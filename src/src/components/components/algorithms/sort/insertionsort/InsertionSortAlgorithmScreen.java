package src.components.components.algorithms.sort.insertionsort;

import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;

public class InsertionSortAlgorithmScreen extends AbstractSortAlgorithmScreen {
    @Override
    public void createSortAnimation() {
        sortAnimation = new InsertionSortAnimation(this, getViewAction().getAnimationPeriod());
    }
}
