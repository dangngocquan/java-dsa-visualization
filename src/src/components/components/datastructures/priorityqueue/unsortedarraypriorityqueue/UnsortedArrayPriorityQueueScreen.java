package src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue;

import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.models.datastructures.priorityqueue.UnsortedArrayPriorityQueue;

public class UnsortedArrayPriorityQueueScreen extends AbstractPriorityQueueScreen {
    @Override
    public void createQueue() {
        queue = new UnsortedArrayPriorityQueue<>();
        queue.insert(0, new UnsortedArrayPriorityQueuePanelNode(queue.size(), 0, 1));
        queue.insert(1, new UnsortedArrayPriorityQueuePanelNode(queue.size(), 1, 0));
        queue.insert(2, new UnsortedArrayPriorityQueuePanelNode(queue.size(), 2, 4));
    }

    @Override
    public void createViewAction() {
        viewAction = new ViewUnsortedArrayPriorityQueueAction(this);
        add(viewAction);
    }
}