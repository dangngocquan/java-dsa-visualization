package src.components.components.algorithms.sort.mergesort;

import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;

public class MergeSortAlgorithmScreen extends AbstractSortAlgorithmScreen {
    @Override
    public void createSortAnimation() {
        sortAnimation = new MergeSortAnimation(this, getViewAction().getAnimationPeriod());
    }
}
