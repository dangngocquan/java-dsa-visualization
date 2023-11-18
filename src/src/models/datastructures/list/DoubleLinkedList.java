package src.models.datastructures.list;

import java.util.Iterator;

public class DoubleLinkedList<E extends Comparable<E>> extends MyAbstractList<E> {
    public class Node {
        public E data;
        public Node prev;
        public Node next;

        public Node(E data) {
            this.data = data;
            this.prev = null;
            this.next = null;
        }
    }

    private Node first;
    private Node last;
    private int size;

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean add(E value) {
        Node newNode = new Node(value);
        if (size == 0) {
            first = newNode;
            last = newNode;
            size++;
            return true;
        }
        last.next = newNode;
        newNode.prev = last;
        last = last.next;
        size++;
        return true;
    }

    @Override
    public void add(int index, E value) {
        if (index < 0 || index > size) throw new ArrayIndexOutOfBoundsException();
        Node newNode = new Node(value);
        if (index == size) {
            add(value);
            return;
        }
        if (index == 0) {
            newNode.next = first;
            first.prev = newNode;
            first = newNode;
            size++;
            return;
        }
        Node prevNode = getNode(index - 1);
        Node nextNode = prevNode.next;
        newNode.next = nextNode;
        prevNode.next = newNode;
        newNode.prev = prevNode;
        nextNode.prev = newNode;
        size++;
    }

    @Override
    public E get(int index) {
        Node node = getNode(index);
        return node.data;
    }

    @Override
    public E set(int index, E value) {
        Node node = getNode(index);
        E prevData = node.data;
        node.data = value;
        return prevData;
    }

    @Override
    public E remove(int index) {
        if (index < 0 || index >= size) throw new ArrayIndexOutOfBoundsException();
        Node node = getNode(index);
        E data = node.data;
        if (index == 0 && index == size - 1) {
            first = null;
            last = null;
            size--;
            return data;
        }
        if (index == 0) {
            first = first.next;
            first.prev = null;
            size--;
            return data;
        }
        if (index == size - 1) {
            last = last.prev;
            last.next = null;
            size--;
            return data;
        }
        Node prevNode = node.prev;
        Node nextNode = node.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        size--;
        return data;
    }

    @Override
    public boolean remove(E value) {
        int index = -1;
        int i = 0;
        Node currentNode = first;
        while (i < size && currentNode != null) {
            if (currentNode.data.compareTo(value) == 0) {
                index = i;
                break;
            }
            currentNode = currentNode.next;
            i++;
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
        return new MySinglyLinkedListIterator();
    }

    private class MySinglyLinkedListIterator implements Iterator<E> {
        private Node currentNode = first;

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public E next() {
            E data = currentNode.data;
            currentNode = currentNode.next;
            return data;
        }
    }

    public Node getNode(int index) {
        if (index < 0 || index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        Node currentNode = first;
        while (index-- > 0) currentNode = currentNode.next;
        return currentNode;
    }
}
