package src.models.datastructures.queue.priorityqueue;

import java.util.Iterator;

public class UnsortedArrayPriorityQueue<K extends Comparable<K>, E> extends AbstractArrayPriorityQueue<K, E> {
    @Override
    public void insert(EntryInterface<K, E> entry) {
        if (entry == null) return;
        if (size == data.length) enlarge();
        data[size++] = entry;
    }

    @Override
    public EntryInterface<K, E> removeMin() {
        if (isEmpty()) return null;
        EntryInterface<K, E> entryMin = min();
        int indexEntryMin = size;
        for (int i = 0; i < size; i++) {
            if (data[i] == entryMin) {
                indexEntryMin = i;
                break;
            }
        }
        for (int i = indexEntryMin; i < size - 1; i++) data[i] = data[i+1];
        size--;
        return entryMin;
    }

    @Override
    public EntryInterface<K, E> min() {
        if (isEmpty()) return null;
        Iterator<EntryInterface<K, E>> iterator = iterator();
        EntryInterface<K, E> entryMin = iterator.next();
        while (iterator.hasNext()) {
            EntryInterface<K, E> currentEntry = iterator.next();
            if (currentEntry.getKey().compareTo(entryMin.getKey()) < 0) {
                entryMin = currentEntry;
            }
        }
        return entryMin;
    }
}
