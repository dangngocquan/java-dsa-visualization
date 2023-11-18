package src.components.components.datastructures.queue.linkedqueue;

import src.components.components.datastructures.queue.AbstractQueueScreen;
import src.models.datastructures.queue.LinkedListQueue;

public class LinkedQueueScreen extends AbstractQueueScreen {
    @Override
    public void createViewAction() {
        viewAction = new ViewLinkedQueueAction(this);
        add(viewAction);
    }

    @Override
    public void createQueue() {
        queue = new LinkedListQueue<>();
        LinkedQueuePanelNode node1 = new LinkedQueuePanelNode(0, 1);
        LinkedQueuePanelNode node2 = new LinkedQueuePanelNode(1, 0);
        LinkedQueuePanelNode node3 = new LinkedQueuePanelNode(2, 4);
        queue.enqueue(node1);
        queue.enqueue(node2);
        queue.enqueue(node3);
        node1.setNextArrow(node1.getDefaultNextArrow());
        node2.setNextArrow(node2.getDefaultNextArrow());
    }
}