package src.components.components.datastructures.stack;

import src.components.components.datastructures.AbstractViewDataStructureAction;

public abstract class AbstractViewStackAction extends AbstractViewDataStructureAction {
    public AbstractViewStackAction(AbstractStackScreen rootScreen) {
        super(rootScreen);
    }

    public AbstractStackScreen getRootScreen() {
        return (AbstractStackScreen) rootScreen;
    }

    public abstract void actionPush(int value);
    public abstract void actionPop();
}
