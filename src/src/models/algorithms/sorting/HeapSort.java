package src.models.algorithms.sorting;

import src.models.datastructures.queue.priorityqueue.MinHeapPriorityQueue;
import src.models.datastructures.queue.priorityqueue.PriorityQueueInterface;

public class HeapSort {
    public <T extends Comparable<T>> void sort(T[] array) {
        PriorityQueueInterface<T, T> heapQueue = new MinHeapPriorityQueue<>();
        for (T element : array) heapQueue.insert(element, element);
        int i = 0;
        while (!heapQueue.isEmpty()) array[i++] = heapQueue.removeMin().getKey();
    }
}
