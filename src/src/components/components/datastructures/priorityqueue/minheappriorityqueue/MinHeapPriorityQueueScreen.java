package src.components.components.datastructures.priorityqueue.minheappriorityqueue;

import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;

import src.models.datastructures.priorityqueue.EntryInterface;
import src.models.datastructures.priorityqueue.MinHeapPriorityQueue;

import java.util.Iterator;

public class MinHeapPriorityQueueScreen extends AbstractPriorityQueueScreen {
    public static int[] indexColumns = new int[] {
            7, 3, 11, 1, 5, 9, 13, 0, 2, 4, 6, 8, 10, 12, 14
    };
    public static int[] indexRows = new int[] {
            0, 1, 1, 2, 2, 2, 2, 3, 3, 3, 3, 3, 3, 3, 3
    };
    @Override
    public void createQueue() {
        queue = new MinHeapPriorityQueue<>();
        queue.insert(0, new MinHeapPriorityQueuePanelNode(indexRows[0], indexColumns[0], 0, 1));
        queue.insert(1, new MinHeapPriorityQueuePanelNode(indexRows[1], indexColumns[1], 1, 0));
        queue.insert(2, new MinHeapPriorityQueuePanelNode(indexRows[2], indexColumns[2], 2, 4));
        queue.insert(3, new MinHeapPriorityQueuePanelNode(indexRows[3], indexColumns[3], 3, 1));
        queue.insert(4, new MinHeapPriorityQueuePanelNode(indexRows[4], indexColumns[4], 4, 0));
//        queue.insert(5, new MinHeapPriorityQueuePanelNode(indexRows[5], indexColumns[5], 5, 4));
//        queue.insert(6, new MinHeapPriorityQueuePanelNode(indexRows[6], indexColumns[6], 6, 1));
//        queue.insert(7, new MinHeapPriorityQueuePanelNode(indexRows[7], indexColumns[7], 7, 0));
//        queue.insert(8, new MinHeapPriorityQueuePanelNode(indexRows[8], indexColumns[8], 8, 4));
//        queue.insert(9, new MinHeapPriorityQueuePanelNode(indexRows[9], indexColumns[9], 9, 1));
//        queue.insert(10, new MinHeapPriorityQueuePanelNode(indexRows[10], indexColumns[10], 10, 0));
//        queue.insert(11, new MinHeapPriorityQueuePanelNode(indexRows[11], indexColumns[11], 11, 4));
//        queue.insert(12, new MinHeapPriorityQueuePanelNode(indexRows[12], indexColumns[12], 12, 1));
//        queue.insert(13, new MinHeapPriorityQueuePanelNode(indexRows[13], indexColumns[13], 13, 0));
//        queue.insert(14, new MinHeapPriorityQueuePanelNode(indexRows[14], indexColumns[14], 14, 4));
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