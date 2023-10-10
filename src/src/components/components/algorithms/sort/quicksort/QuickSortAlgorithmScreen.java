package src.components.components.algorithms.sort.quicksort;

import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;

public class QuickSortAlgorithmScreen extends AbstractSortAlgorithmScreen {
    @Override
    public void createSortAnimation() {
        sortAnimation = new QuickSortAnimation(this, getViewAction().getAnimationPeriod());
    }
}
