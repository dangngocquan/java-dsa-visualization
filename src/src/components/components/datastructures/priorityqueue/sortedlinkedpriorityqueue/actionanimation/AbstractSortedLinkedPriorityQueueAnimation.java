package src.components.components.datastructures.priorityqueue.sortedlinkedpriorityqueue.actionanimation;

import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;

public abstract class AbstractSortedLinkedPriorityQueueAnimation extends AbstractPriorityQueueAnimation {
    public AbstractSortedLinkedPriorityQueueAnimation(
            AbstractPriorityQueueScreen rootScreen,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}