package src.models.datastructures.priorityqueue;

import java.util.Iterator;

public interface PriorityQueueInterface<K, E> {
    int size();
    boolean isEmpty();
    void insert(EntryInterface<K, E> entry);
    void insert(K k, E e);
    EntryInterface<K, E> removeMin();
    EntryInterface<K, E> min();
    Iterator<EntryInterface<K, E>> iterator();
}
