package src.components.components.algorithms;

import src.components.components.AbstractScreen;

public abstract class AbstractAlgorithmScreen extends AbstractScreen {
    public AbstractViewAlgorithmController viewController;
    public AbstractViewAlgorithmAction viewAction;

    public AbstractAlgorithmScreen() {
        super();
        createViewController();
        createData();
        createViewAction();
        repaint();
    }

    public abstract void createViewController();
    public abstract void createData();

    public abstract void createViewAction();

    // Unused
    @Override
    public void addButtons() {}

    @Override
    public void createDefaultScreens() {}

    @Override
    public void addActionListenerForButtons() {}

    public void setDescription(String s) {
        viewAction.setDescription(s);
    }

    public String getDescription() {
        return viewAction.description.getText();
    }
}
