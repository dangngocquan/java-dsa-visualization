package src.components.components.datastructures.priorityqueue.minheappriorityqueue.actionanimation;

import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.minheappriorityqueue.MinHeapPriorityQueueScreen;

public abstract class AbstractMinHeapPriorityQueueAnimation extends AbstractPriorityQueueAnimation {
    public AbstractMinHeapPriorityQueueAnimation(
            AbstractPriorityQueueScreen rootScreen,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    public MinHeapPriorityQueueScreen getRootScreen() {
        return (MinHeapPriorityQueueScreen) rootScreen;
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}