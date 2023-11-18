package src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue;

import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.models.datastructures.priorityqueue.SortedArrayPriorityQueue;

public class SortedArrayPriorityQueueScreen extends AbstractPriorityQueueScreen {
    @Override
    public void createQueue() {
        queue = new SortedArrayPriorityQueue<>();
        queue.insert(0, new SortedArrayPriorityQueuePanelNode(queue.size(), 0, 1));
        queue.insert(1, new SortedArrayPriorityQueuePanelNode(queue.size(), 1, 0));
        queue.insert(2, new SortedArrayPriorityQueuePanelNode(queue.size(), 2, 4));
    }

    @Override
    public void createViewAction() {
        viewAction = new ViewSortedArrayPriorityQueueAction(this);
        add(viewAction);
    }
}