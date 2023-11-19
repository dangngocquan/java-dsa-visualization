package src.models.datastructures.stack;

import java.util.Iterator;

public class LinkedStack<E> extends AbstractStack<E> {
    class Node {
        E element;
        Node next;
    }

    private Node stack = null;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public void push(E element) {
        Node node = new Node();
        node.element = element;
        node.next = stack;
        stack = node;
        size++;
    }

    @Override
    public E pop() {
        E element = null;
        if (stack != null) {
            element = stack.element;
            stack = stack.next;
            size--;
        }
        return element;
    }

    @Override
    public boolean isEmpty() {
        return stack == null;
    }

    @Override
    public E top() {
        if (stack == null) return null;
        return stack.element;
    }

    @Override
    public Iterator<E> iterator() {
        return new LinkedStackIterator();
    }

    class LinkedStackIterator implements Iterator<E> {
        private Node currentNode = stack;

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
