package src.components.components.algorithms.sort.selectionsort;

import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;

public class SelectionSortAlgorithmScreen extends AbstractSortAlgorithmScreen {
    @Override
    public void createSortAnimation() {
        sortAnimation = new SelectionSortAnimation(this, getViewAction().getAnimationPeriod());
    }
}
