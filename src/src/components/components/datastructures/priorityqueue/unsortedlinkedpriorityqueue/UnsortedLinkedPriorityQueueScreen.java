package src.components.components.datastructures.priorityqueue.unsortedlinkedpriorityqueue;

import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.models.datastructures.priorityqueue.UnsortedLinkedPriorityQueue;

public class UnsortedLinkedPriorityQueueScreen extends AbstractPriorityQueueScreen {
    @Override
    public void createViewAction() {
        viewAction = new ViewUnsortedLinkedPriorityQueueAction(this);
        add(viewAction);
    }

    @Override
    public void createQueue() {
        queue = new UnsortedLinkedPriorityQueue<>();
        UnsortedLinkedPriorityQueuePanelNode node1
                = new UnsortedLinkedPriorityQueuePanelNode(0, 0, 1);
        UnsortedLinkedPriorityQueuePanelNode node2
                = new UnsortedLinkedPriorityQueuePanelNode(1, 1, 0);
        UnsortedLinkedPriorityQueuePanelNode node3
                = new UnsortedLinkedPriorityQueuePanelNode(2, 2, 4);
        queue.insert(0, node1);
        queue.insert(1, node2);
        queue.insert(2, node3);
        node1.setNextArrow(node1.getDefaultNextArrow());
        node2.setNextArrow(node2.getDefaultNextArrow());
    }
}