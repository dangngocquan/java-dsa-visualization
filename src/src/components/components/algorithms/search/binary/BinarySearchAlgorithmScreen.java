package src.components.components.algorithms.search.binary;

import src.components.components.algorithms.search.AbstractSearchAlgorithmScreen;

public class BinarySearchAlgorithmScreen extends AbstractSearchAlgorithmScreen {
    @Override
    public void createSearchAnimation() {
        searchAnimation = new BinarySearchAnimation(this, getViewAction().getAnimationPeriod());
    }
}
