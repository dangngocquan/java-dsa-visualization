package src.components.components.algorithms.sort;

import src.Config;
import src.components.components.algorithms.AbstractAlgorithmScreen;
import src.services.ServiceGenerateRandom;

public abstract class AbstractSortAlgorithmScreen extends AbstractAlgorithmScreen {
    public int[] array;
    public AbstractSortAlgorithmAnimation sortAnimation;

    @Override
    public void createData() {
        array = ServiceGenerateRandom.createRandomArray(20, 1, 100);
    }

    @Override
    public void createViewController() {
        viewController = new ViewSortAlgorithmController(this);
        add(viewController);
    }

    @Override
    public void createViewAction() {
        viewAction = new ViewSortAlgorithmAction(this, array);
        add(viewAction);
    }

    public ViewSortAlgorithmAction getViewAction() {
        return (ViewSortAlgorithmAction) viewAction;
    }

    public ViewSortAlgorithmController getViewController() {
        return (ViewSortAlgorithmController) viewController;
    }

    public void setArray(int[] array) {
        boolean isChanged = false;
        if (this.array.length != array.length) isChanged = true;
        if (!isChanged) {
            for (int i = 0; i < array.length; i++) {
                if (this.array[i] != array[i]) {
                    isChanged = true;
                    break;
                }
            }
        }
        if (isChanged) {
            endSort();
            this.array = array;
            getViewAction().setElements(array);
            repaint();
        }
    }

    public abstract void createSortAnimation();

    public void startSort() {
        getViewController().startSort();
        getViewAction().setColorAndLocationBars(
                0, getViewAction().bars.length-1,
                Config.COLOR_BAR_PLAIN,
                getViewAction().initialY0
        );
        createSortAnimation();
        sortAnimation.start();
    }

    public void pauseSort() {
        if (sortAnimation != null && sortAnimation.isRunning()) {
            getViewController().pauseSort();
            sortAnimation.pause();
        }
    }

    public void continueSort() {
        if (sortAnimation != null && sortAnimation.isPaused()) {
            getViewController().continueSort();
            sortAnimation.continueRun();
        }
    }

    public void endSort() {
        sortAnimation = null;
        getViewController().endSort();
        getViewAction().runEndAnimation();
    }
}
