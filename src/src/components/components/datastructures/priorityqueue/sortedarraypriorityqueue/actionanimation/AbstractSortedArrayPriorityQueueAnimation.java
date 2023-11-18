package src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.actionanimation;

import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;

public abstract class AbstractSortedArrayPriorityQueueAnimation extends AbstractPriorityQueueAnimation {
    public AbstractSortedArrayPriorityQueueAnimation(
            AbstractPriorityQueueScreen rootScreen,
            int period, AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
