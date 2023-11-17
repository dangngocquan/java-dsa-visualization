package src.components.components.datastructures.queue;

import src.components.components.datastructures.AbstractViewDataStructureAction;

public abstract class AbstractViewQueueAction extends AbstractViewDataStructureAction {
    public AbstractViewQueueAction(AbstractQueueScreen rootScreen) {
        super(rootScreen);
    }

    public AbstractQueueScreen getRootScreen() {
        return (AbstractQueueScreen) rootScreen;
    }

    public abstract void actionEnqueue(int value);
    public abstract void actionDequeue();
}