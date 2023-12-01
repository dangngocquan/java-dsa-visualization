package src.components.components.datastructures.priorityqueue.sortedlinkedpriorityqueue;

import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.models.datastructures.priorityqueue.SortedLinkedPriorityQueue;

public class SortedLinkedPriorityQueueScreen extends AbstractPriorityQueueScreen {
    @Override
    public void createViewAction() {
        viewAction = new ViewSortedLinkedPriorityQueueAction(this);
        add(viewAction);
    }

    @Override
    public void createPriorityQueue() {
        queue = new SortedLinkedPriorityQueue<>();
        SortedLinkedPriorityQueuePanelNode node1
                = new SortedLinkedPriorityQueuePanelNode(0, 0, 1);
        SortedLinkedPriorityQueuePanelNode node2
                = new SortedLinkedPriorityQueuePanelNode(1, 1, 0);
        SortedLinkedPriorityQueuePanelNode node3
                = new SortedLinkedPriorityQueuePanelNode(2, 2, 4);
        queue.insert(0, node1);
        queue.insert(1, node2);
        queue.insert(2, node3);
        node1.setNextArrow(node1.getDefaultNextArrow());
        node2.setNextArrow(node2.getDefaultNextArrow());
    }
}
