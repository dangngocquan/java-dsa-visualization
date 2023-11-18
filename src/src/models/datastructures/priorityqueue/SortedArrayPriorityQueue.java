package src.models.datastructures.priorityqueue;

public class SortedArrayPriorityQueue<K extends Comparable<K>, E> extends AbstractArrayPriorityQueue<K, E> {
    @Override
    public void insert(EntryInterface<K, E> entry) {
        if (entry == null) return;
        if (data.length == size) enlarge();
        int i = size;
        while (i > 0 && entry.getKey().compareTo(data[i-1].getKey()) < 0) data[i--] = data[i];
        data[i] = entry;
        size++;
    }

    @Override
    public EntryInterface<K, E> removeMin() {
        if (isEmpty()) return null;
        EntryInterface<K, E> entryMin = data[0];
        for (int i = 0; i < size - 1; i++) data[i] = data[i+1];
        size--;
        return entryMin;
    }

    @Override
    public EntryInterface<K, E> min() {
        if (isEmpty()) return null;
        return data[0];
    }
}
