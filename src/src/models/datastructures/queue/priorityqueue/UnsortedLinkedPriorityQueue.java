package src.models.datastructures.queue.priorityqueue;

public class UnsortedLinkedPriorityQueue<K extends Comparable<K>, E>
        extends AbstractLinkedPriorityQueue<K, E> {
    @Override
    public void insert(EntryInterface<K, E> entry) {
        if (entry == null) return;
        if (size == 0) {
            first = new Node<>(entry);
            last = first;
            size++;
            return;
        }
        last.next = new Node<>(entry);
        last = last.next;
        size++;
    }

    @Override
    public EntryInterface<K, E> removeMin() {
        if (isEmpty()) return null;
        int index = 0;
        EntryInterface<K, E> entryMin = min();
        Node<K, E> currentNode = first;
        while (currentNode.entry != entryMin) {
            currentNode = currentNode.next;
            index++;
        }
        if (index == 0 && index == size - 1) {
            first = null;
            last = null;
            size--;
            return entryMin;
        }
        if (index == 0) {
            first = first.next;
            size--;
            return entryMin;
        }
        Node<K, E> prevNode = getNode(index-1);
        prevNode.next = prevNode.next.next;
        size--;
        return entryMin;
    }

    @Override
    public EntryInterface<K, E> min() {
        if (isEmpty()) return null;
        EntryInterface<K, E> entryMin = first.entry;
        for (EntryInterface<K, E> entry : this) {
            if (entry.getKey().compareTo(entryMin.getKey()) < 0) {
                entryMin = entry;
            }
        }
        return entryMin;
    }
}
