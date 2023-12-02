package src.components.components.datastructures.priorityqueue.minheappriorityqueue;

import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;

import src.models.datastructures.priorityqueue.EntryInterface;
import src.models.datastructures.priorityqueue.MinHeapPriorityQueue;

import java.util.Iterator;

public class MinHeapPriorityQueueScreen extends AbstractPriorityQueueScreen {
    public static int[] INDEX_COLUMNS = new int[] {
            7,
            3, 11,
            1, 5, 9, 13,
            0, 2, 4, 6, 8, 10, 12, 14
    };
    public static int[] INDEX_ROWS = new int[] {
            0,
            1, 1,
            2, 2, 2, 2,
            3, 3, 3, 3, 3, 3, 3, 3
    };
    @Override
    public void createPriorityQueue() {
        queue = new MinHeapPriorityQueue<>();
        queue.insert(0, new MinHeapPriorityQueuePanelNode(INDEX_ROWS[0], INDEX_COLUMNS[0], 0, 1));
        queue.insert(1, new MinHeapPriorityQueuePanelNode(INDEX_ROWS[1], INDEX_COLUMNS[1], 1, 0));
        queue.insert(2, new MinHeapPriorityQueuePanelNode(INDEX_ROWS[2], INDEX_COLUMNS[2], 2, 4));
        queue.insert(3, new MinHeapPriorityQueuePanelNode(INDEX_ROWS[3], INDEX_COLUMNS[3], 3, 1));
        queue.insert(4, new MinHeapPriorityQueuePanelNode(INDEX_ROWS[4], INDEX_COLUMNS[4], 4, 0));
        queue.insert(5, new MinHeapPriorityQueuePanelNode(INDEX_ROWS[5], INDEX_COLUMNS[5], 5, 4));
        queue.insert(6, new MinHeapPriorityQueuePanelNode(INDEX_ROWS[6], INDEX_COLUMNS[6], 6, 1));
        queue.insert(7, new MinHeapPriorityQueuePanelNode(INDEX_ROWS[7], INDEX_COLUMNS[7], 7, 0));
    }

    @Override
    public void createViewAction() {
        viewAction = new ViewMinHeapPriorityQueueAction(this);
        add(viewAction);
    }

    public MinHeapPriorityQueuePanelNode[] getCloneData() {
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator
                = queue.iterator();
        MinHeapPriorityQueuePanelNode[] nodes = new MinHeapPriorityQueuePanelNode[15];
        int i = 0;
        while (iterator.hasNext()) {
            nodes[i++] = (MinHeapPriorityQueuePanelNode) iterator.next().getValue();
        }
        return nodes;
    }
}