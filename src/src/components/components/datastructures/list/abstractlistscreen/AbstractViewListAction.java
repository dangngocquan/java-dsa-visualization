package src.components.components.datastructures.list.abstractlistscreen;

import src.components.components.datastructures.AbstractViewDataStructureAction;

public abstract class AbstractViewListAction extends AbstractViewDataStructureAction {
    public AbstractViewListAction(AbstractListScreen rootScreen) {
        super(rootScreen);
    }

    public AbstractListScreen getRootScreen() {
        return (AbstractListScreen) rootScreen;
    }

    public abstract void actionAdd(int value);
    public abstract void actionAdd(int index, int value);
    public abstract void actionGet(int index);
    public abstract void actionRemove(int index);
    public abstract void actionRemove(Integer value);
}
