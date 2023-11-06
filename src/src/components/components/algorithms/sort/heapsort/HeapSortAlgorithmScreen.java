package src.components.components.algorithms.sort.heapsort;

import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;
import src.components.components.algorithms.sort.bubblesort.BubbleSortAnimation;

public class HeapSortAlgorithmScreen extends AbstractSortAlgorithmScreen {
    @Override
    public void createSortAnimation() {
        sortAnimation = new HeapSortAnimation(this, getViewAction().getAnimationPeriod());
    }
}
