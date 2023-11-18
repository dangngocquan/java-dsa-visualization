package src.components.components.datastructures.priorityqueue.minheappriorityqueue.actionanimation;

import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.actionanimation.AbstractSortedArrayPriorityQueueAnimation;

public class MinHeapPriorityQueueActionRemoveMin extends AbstractSortedArrayPriorityQueueAnimation {
    public MinHeapPriorityQueueActionRemoveMin(
            AbstractPriorityQueueScreen rootScreen,
            int period,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
    }

    @Override
    public void run() {

    }
}
