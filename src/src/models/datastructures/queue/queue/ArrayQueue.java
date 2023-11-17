package src.models.datastructures.queue.queue;

import java.util.Arrays;
import java.util.Iterator;

public class ArrayQueue<E> extends AbstractQueue<E> {
    public static final int DEFAULT_CAPACITY = 4;
    private E[] data;
    private int firstIndex;
    private int size;

    public ArrayQueue() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayQueue(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(E element) {
        if (size == data.length) enlarge();
        data[(firstIndex + size) % data.length] = element;
        size++;
    }

    @Override
    public E dequeue() {
        if (size == 0) return null;
        E element = data[firstIndex];
        firstIndex = (firstIndex + 1) % data.length;
        size--;
        return element;
    }

    @Override
    public E first() {
        if (size == 0) return null;
        return data[firstIndex];
    }

    @Override
    public E last() {
        if (size == 0) return null;
        return data[(firstIndex + size - 1) % data.length];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    private void enlarge() {
        if (data.length * 2 < data.length) throw new IllegalArgumentException();
        E[] newData = (E[]) new Object[data.length * 2];
        int i = 0;
        while (!isEmpty()) newData[i++] = dequeue();
        firstIndex = 0;
        size = i;
        data = newData;
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayQueueIterator();
    }

    private class ArrayQueueIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            E element = data[(currentIndex + firstIndex) % data.length];
            currentIndex++;
            return element;
        }
    }

    public int getSizeData() {
        return data.length;
    }
    public int getFirstIndex() {
        return firstIndex;
    }
}
