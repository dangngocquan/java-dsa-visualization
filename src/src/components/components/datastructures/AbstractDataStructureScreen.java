package src.components.components.datastructures;

import src.Config;
import src.components.base.Button;
import src.components.components.AbstractScreen;

public abstract class AbstractDataStructureScreen extends AbstractScreen {
    public AbstractViewDataStructureController viewController;
    public AbstractViewDataStructureAction viewAction;
    public int indexActionSelected = -1;

    public AbstractDataStructureScreen() {
        super();
        createViewController();
        createViewAction();
        repaint();
    }

    public abstract void createViewController();

    public abstract void createViewAction();

    public abstract void setIndexActionSelected(int indexActionSelected);

    public void setEnabledAllButtons(boolean isEnabled) {
        for (Button button : viewController.buttons) button.setEnabledButton(isEnabled);
    }

    public int getPeriod() {
        return viewAction == null? Config.DEFAULT_ANIMATION_SPEED : viewAction.getPeriod();
    }

    public void setPeriod(int period) {
        viewAction.setPeriod(period);
    }

    public abstract void runAction();

    public void endAction() {
        viewController.buttons[0].setEnabledButton(true);
        viewController.buttons[1].setEnabledButton(true);
        viewController.buttons[3].setEnabledButton(true);
        setIndexActionSelected(-1);
    }


    // Unused
    @Override
    public void addButtons() {}

    @Override
    public void createDefaultScreens() {}

    @Override
    public void addActionListenerForButtons() {}
}
