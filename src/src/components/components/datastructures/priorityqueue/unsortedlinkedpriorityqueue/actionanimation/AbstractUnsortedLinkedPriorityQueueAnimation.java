package src.components.components.datastructures.priorityqueue.unsortedlinkedpriorityqueue.actionanimation;

import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;

public abstract class AbstractUnsortedLinkedPriorityQueueAnimation extends AbstractPriorityQueueAnimation {
    public AbstractUnsortedLinkedPriorityQueueAnimation(
            AbstractPriorityQueueScreen rootScreen,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}