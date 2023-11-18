package src.components.components.datastructures.priorityqueue;

import src.components.components.datastructures.AbstractViewDataStructureAction;

public abstract class AbstractViewPriorityQueueAction extends AbstractViewDataStructureAction {
    public AbstractViewPriorityQueueAction(AbstractPriorityQueueScreen rootScreen) {
        super(rootScreen);
    }

    public AbstractPriorityQueueScreen getRootScreen() {
        return (AbstractPriorityQueueScreen) rootScreen;
    }

    public abstract void actionInsert(Integer key, Integer value);
    public abstract void actionRemoveMin();
}