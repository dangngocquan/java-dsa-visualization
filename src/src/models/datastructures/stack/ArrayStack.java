package src.models.datastructures.stack;

import java.util.Iterator;

public class ArrayStack<E> extends AbstractStack<E> {
    public static final int DEFAULT_CAPACITY = 4;
    private E[] data;
    private int size;

    public ArrayStack() {
        data = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayStack(int capacity) {
        data = (E[]) new Object[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(E element) {
        if (size == data.length) enlarge();
        data[size++] = element;
    }

    @Override
    public E pop() {
        if (size == 0) return null;
        return data[--size];
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public E top() {
        if (size == 0) return null;
        return data[size-1];
    }

    @Override
    public Iterator<E> iterator() {
        return new ArrayStackIterator();
    }

    class ArrayStackIterator implements Iterator<E> {
        private int currentIndex = size-1;

        @Override
        public boolean hasNext() {
            return currentIndex > -1;
        }

        @Override
        public E next() {
            return data[currentIndex--];
        }
    }

    private void enlarge() {
        if (data.length * 2 + 1 < data.length) throw new IllegalArgumentException();
        E[] newData = (E[]) new Object[data.length * 2 + 1];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public int getSizeData() {
        return data.length;
    }
}
