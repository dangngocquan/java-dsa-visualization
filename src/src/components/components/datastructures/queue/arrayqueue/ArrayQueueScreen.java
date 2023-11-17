package src.components.components.datastructures.queue.arrayqueue;

import src.components.components.datastructures.queue.AbstractQueueScreen;
import src.models.datastructures.queue.queue.ArrayQueue;

public class ArrayQueueScreen extends AbstractQueueScreen {
    @Override
    public void createQueue() {
        queue = new ArrayQueue<>();
        queue.enqueue(new ArrayQueuePanelNode(queue.size(), 1));
        queue.enqueue(new ArrayQueuePanelNode(queue.size(), 0));
        queue.enqueue(new ArrayQueuePanelNode(queue.size(), 4));
    }

    @Override
    public void createViewAction() {
        viewAction = new ViewArrayQueueAction(this);
        add(viewAction);
    }
}