package src.components.components.algorithms.search;

import src.Config;
import src.components.components.algorithms.AbstractAlgorithmScreen;
import src.components.components.algorithms.search.binary.BinarySearchAlgorithmScreen;
import src.services.ServiceGenerateRandom;

public abstract class AbstractSearchAlgorithmScreen extends AbstractAlgorithmScreen {
    public int[] array;
    public int valueSearching;
    public AbstractSearchAlgorithmAnimation searchAnimation;

    @Override
    public void createData() {
        if (this instanceof BinarySearchAlgorithmScreen) {
            array = ServiceGenerateRandom.createRandomSortedArray(20, 1, 100);
        } else {
            array = ServiceGenerateRandom.createRandomArray(20, 1, 100);
        }
        valueSearching = 10;
    }

    @Override
    public void createViewController() {
        viewController = new ViewSearchAlgorithmController(this);
        add(viewController);
    }

    @Override
    public void createViewAction() {
        viewAction = new ViewSearchAlgorithmAction(this, array);
        add(viewAction);
    }

    public ViewSearchAlgorithmAction getViewAction() {
        return (ViewSearchAlgorithmAction) viewAction;
    }

    public ViewSearchAlgorithmController getViewController() {
        return (ViewSearchAlgorithmController) viewController;
    }

    public void setArray(int[] array) {
        boolean isChanged = this.array.length != array.length;
        if (!isChanged) {
            for (int i = 0; i < array.length; i++) {
                if (this.array[i] != array[i]) {
                    isChanged = true;
                    break;
                }
            }
        }
        if (isChanged) {
            endSearch(0);
            this.array = array;
            getViewAction().setElements(array);
            repaint();
        }
    }

    public void setValueSearching(int value) {
        if (value != valueSearching) {
            valueSearching = value;
            endSearch(0);
        }
    }

    public abstract void createSearchAnimation();

    public void startSearch() {
        getViewController().startSearch();
        getViewAction().setColorAndLocationBars(
                0, getViewAction().bars.length-1,
                Config.COLOR_BAR_PLAIN,
                ViewSearchAlgorithmAction.initialY0
        );
        createSearchAnimation();
        searchAnimation.start();
    }

    public void pauseSearch() {
        if (searchAnimation != null && searchAnimation.isRunning()) {
            getViewController().pauseSearch();
            searchAnimation.pause();
        }
    }

    public void continueSearch() {
        if (searchAnimation != null && searchAnimation.isPaused()) {
            getViewController().continueSearch();
            searchAnimation.continueRun();
        }
    }

    public void endSearch(int type) {
        searchAnimation = null;
        getViewController().endSearch();
        getViewAction().runEndAnimation(type);
    }
}
