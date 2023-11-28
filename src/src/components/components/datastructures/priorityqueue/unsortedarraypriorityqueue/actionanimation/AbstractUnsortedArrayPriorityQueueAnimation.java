package src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue.actionanimation;

import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;

public abstract class AbstractUnsortedArrayPriorityQueueAnimation extends AbstractPriorityQueueAnimation {
    public AbstractUnsortedArrayPriorityQueueAnimation(
            AbstractPriorityQueueScreen rootScreen,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
