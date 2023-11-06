package src.models.datastructures.queue.priorityqueue;

public class MinHeapPriorityQueue<K extends Comparable<K>, E>
        extends AbstractArrayPriorityQueue<K, E> {
    // Start with index = 1
    @Override
    public void insert(EntryInterface<K, E> entry) {
        if (entry == null) return;
        if (data.length - 1 == size) enlarge();
        data[++size] = entry;
        upHeap();
    }

    @Override
    public EntryInterface<K, E> removeMin() {
        if (isEmpty()) return null;
        EntryInterface<K, E> entryMin = data[1];
        data[1] = data[size];
        data[size] = entryMin;
        size--;
        downHeap();
        return entryMin;
    }

    @Override
    public EntryInterface<K, E> min() {
        if (isEmpty()) return null;
        return data[1];
    }

    private void upHeap() {
        if (size == 0) return;
        int i = size;
        while (i > 1 && data[i].getKey().compareTo(data[i/2].getKey()) < 0) {
            EntryInterface<K, E> temp = data[i];
            data[i] = data[i/2];
            data[i/2] = temp;
            i /= 2;
        }
    }

    private void downHeap() {
        if (size == 0) return;
        int i = 1;
        while (i < size) {
            if (2*i  + 1 <= size) { // has left child and right child
                K keyParent = data[i].getKey();
                K keyChildLeft = data[2*i].getKey();
                K keyChildRight = data[2*i+1].getKey();
                if (keyParent.compareTo(keyChildLeft) < 0 && keyParent.compareTo(keyChildRight) < 0) break;
                EntryInterface<K, E> temp = data[i];
                int j = keyChildLeft.compareTo(keyChildRight) <= 0? 2*i : 2*i+1;
                data[i] = data[j];
                data[j] = temp;
                i = j;
            } else if (2*i <= size) { // only has left child
                if (data[i].getKey().compareTo(data[2*i].getKey()) < 0) break;
                EntryInterface<K, E> temp = data[i];
                data[i] = data[2*i];
                data[2*i] = temp;
                i = 2*i;
            } else break; // no child
        }
    }
}
