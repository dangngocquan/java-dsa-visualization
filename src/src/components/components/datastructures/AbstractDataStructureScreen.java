package src.components.components.datastructures;

import src.Config;
import src.components.base.Button;
import src.components.components.AbstractScreen;

public abstract class AbstractDataStructureScreen extends AbstractScreen {
    public AbstractViewDataStructureController viewController;
    public AbstractViewDataStructureAction viewAction;
    public int indexActionSelected = -1;

    public AbstractDataStructureScreen() {
        super(0, 0, Config.WIDTH, Config.WIDTH, Config.BACKGROUND_COLOR_APP, null, "");
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

    public abstract void runAction();
    public abstract void endAction();


    // Unused
    @Override
    public void addButtons() {}

    @Override
    public void createDefaultScreens() {}

    @Override
    public void addActionListenerForButtons() {}
}
