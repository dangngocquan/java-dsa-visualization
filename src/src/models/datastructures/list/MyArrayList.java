package src.models.datastructures.list;

import java.util.Iterator;

public class MyArrayList<E extends Comparable<E>> extends MyAbstractList<E> {
    public static final int DEFAULT_CAPACITY = 4;
    private E[] data;
    private int size;

    @SuppressWarnings("unchecked")
    public MyArrayList() {
        data = (E[]) new Comparable[DEFAULT_CAPACITY];
    }

    @SuppressWarnings("unchecked")
    public MyArrayList(int capacity) {
        data = (E[]) new Comparable[capacity];
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E value) {
        if (size == data.length) enlarge();
        data[size++] = value;
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        if (size == data.length) enlarge();
        for (int i = size-1; i >= index; i--) {
            data[i+1] = data[i];
        }
        data[index] = value;
        size++;
    }

    @Override
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return data[index];
    }

    @Override
    public E set(int index, E value) {
        E prev = get(index);
        data[index] = value;
        return prev;
    }

    @Override
    public E remove(int index) {
        E value = get(index);
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i+1];
        }
        size--;
        return value;
    }

    @Override
    public boolean remove(E value) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (data[i].compareTo(value) == 0) {
                index = i;
                break;
            }
        }
        if (index == -1) return false;
        remove(index);
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new MyArrayListIterator();
    }

    private class MyArrayListIterator implements Iterator<E> {
        private int currentIndex = 0;

        @Override
        public boolean hasNext() {
            return currentIndex < size;
        }

        @Override
        public E next() {
            return data[currentIndex++];
        }
    }

    @SuppressWarnings("unchecked")
    private void enlarge() {
        E[] newData = (E[]) new Comparable[data.length * 2];
        System.arraycopy(data, 0, newData, 0, data.length);
        data = newData;
    }

    public int getSizeData() {
        return data.length;
    }
}
