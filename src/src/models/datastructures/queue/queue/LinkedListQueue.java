package src.models.datastructures.queue.queue;

import java.util.Iterator;

public class LinkedListQueue<E> extends AbstractQueue<E> {
    class Node {
        E element;
        Node next;
    }

    private Node first;
    private Node last;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void enqueue(E element) {
        Node node = new Node();
        node.element = element;
        if (size == 0) {
            first = node;
            last = node;
            size++;
            return;
        }
        last.next = node;
        last = node;
        size++;
    }

    @Override
    public E dequeue() {
        if (size == 0) return null;
        E element = first.element;
        if (size == 1) {
            first = null;
            last = null;
            size--;
            return element;
        }
        first = first.next;
        size--;
        return element;
    }

    @Override
    public E first() {
        if (first == null) return null;
        return first.element;
    }

    @Override
    public E last() {
        if (last == null) return null;
        return last.element;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedListQueueIterator();
    }

    private class LinkedListQueueIterator implements Iterator<E> {
        private Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            E element = currentNode.element;
            currentNode = currentNode.next;
            return element;
        }
    }
}
