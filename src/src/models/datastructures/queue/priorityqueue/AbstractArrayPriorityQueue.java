package src.models.datastructures.queue.priorityqueue;

import java.util.Arrays;
import java.util.Iterator;

public abstract class AbstractArrayPriorityQueue<K extends Comparable<K>, E>
        extends AbstractPriorityQueue<K, E> {
    public static final int DEFAULT_CAPACITY = 64;
    protected EntryInterface<K, E>[] data;
    protected int size;

    public AbstractArrayPriorityQueue() {
        this.data = new EntryInterface[DEFAULT_CAPACITY];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void insert(K k, E e) {
        insert(new Entry<>(k, e));
    }

    protected void enlarge() {
        if (data.length * 2 <= data.length) throw new IllegalArgumentException();
        data = Arrays.copyOf(data, data.length * 2);
    }

    @Override
    public Iterator<EntryInterface<K, E>> iterator() {
        return new ArrayQueueIterator();
    }

    protected class ArrayQueueIterator implements Iterator<EntryInterface<K, E>> {
        private int count = 0;
        private int currentPosition= 0;

        @Override
        public boolean hasNext() {
            return count < size;
        }

        @Override
        public EntryInterface<K, E> next() {
            while (count < size && data[currentPosition] == null) currentPosition++;
            count++;
            return data[currentPosition++];
        }
    }
}
