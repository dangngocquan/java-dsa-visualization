package src.models.datastructures.queue.priorityqueue;

import java.util.Iterator;

public abstract class AbstractLinkedPriorityQueue<K extends Comparable<K>, E>
        extends AbstractPriorityQueue<K, E> {
    public static class Node<K, E> {
        public EntryInterface<K, E> entry;
        public Node<K, E> next;

        public Node(K key, E value) {
            this.entry = new Entry<>(key, value);
            next = null;
        }

        public Node(EntryInterface<K, E> entry) {
            this.entry = entry;
            next = null;
        }
    }

    protected Node<K, E> first;
    protected Node<K, E> last;
    protected int size;

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

    @Override
    public Iterator<EntryInterface<K, E>> iterator() {
        return new LinkedQueueIterator();
    }

    protected Node<K, E> getNode(int index) {
        if (index < 0 || index >= size) throw new ArrayIndexOutOfBoundsException();
        Node<K, E> currentNode = first;
        while (index-- > 0) currentNode = currentNode.next;
        return currentNode;
    }

    protected class LinkedQueueIterator implements Iterator<EntryInterface<K, E>> {
        private Node<K, E> currentNode;

        public LinkedQueueIterator() {
            currentNode = first;
        }

        @Override
        public boolean hasNext() {
            return currentNode != null;
        }

        @Override
        public EntryInterface<K, E> next() {
            EntryInterface<K, E> entry = currentNode.entry;
            currentNode = currentNode.next;
            return entry;
        }
    }
}
