package src.models.datastructures.queue.priorityqueue;

public class SortedLinkedPriorityQueue<K extends Comparable<K>, E>
        extends AbstractLinkedPriorityQueue<K, E> {
    @Override
    public void insert(EntryInterface<K, E> entry) {
        if (entry == null) return;
        int index = 0;
        for (EntryInterface<K, E> e : this) {
            if (e.getKey().compareTo(entry.getKey()) > 0) break;
            index++;
        }
        Node<K, E> node = new Node<>(entry);
        if (index == 0 && index == size) {
            first = node;
            last = node;
            size++;
            return;
        }
        if (index == 0) {
            node.next = first;
            first = node;
            size++;
            return;
        }
        if (index == size) {
            last.next = node;
            last = last.next;
            size++;
            return;
        }
        Node<K, E> prevNode = getNode(index-1);
        node.next = prevNode.next;
        prevNode.next = node;
        size++;
    }

    @Override
    public EntryInterface<K, E> removeMin() {
        if (isEmpty()) return null;
        EntryInterface<K, E> entryMin = min();
        if (size == 1) {
           first = null;
           last = null;
           size--;
           return entryMin;
        }
        first = first.next;
        size--;
        return entryMin;
    }

    @Override
    public EntryInterface<K, E> min() {
        if (isEmpty()) return null;
        return first.entry;
    }
}
