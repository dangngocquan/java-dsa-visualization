package src.components.components.algorithms.search.sequential;

import src.components.components.algorithms.search.AbstractSearchAlgorithmScreen;

public class SequentialSearchAlgorithmScreen extends AbstractSearchAlgorithmScreen {
    @Override
    public void createSearchAnimation() {
        searchAnimation = new SequentialSearchAnimation(this, getViewAction().getAnimationPeriod());
    }
}
