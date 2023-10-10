package src.components.components.algorithms.sort.bubblesort;

import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;

public class BubbleSortAlgorithmScreen extends AbstractSortAlgorithmScreen {

    @Override
    public void createSortAnimation() {
        sortAnimation = new BubbleSortAnimation(this, getViewAction().getAnimationPeriod());
    }
}
