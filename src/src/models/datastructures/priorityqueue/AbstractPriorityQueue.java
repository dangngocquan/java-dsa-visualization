package src.models.datastructures.priorityqueue;

public abstract class AbstractPriorityQueue<K, E>
        implements PriorityQueueInterface<K, E>, Iterable<EntryInterface<K, E>> {
    public static class Entry<K, E> implements EntryInterface<K, E> {
        protected K key;
        protected E value;

        public Entry(K key, E value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return this.key;
        }

        @Override
        public E getValue() {
            return this.value;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("PriorityQueue[");
        for (EntryInterface<K, E> entry : this) sb.append(entry).append(", ");
        if (this.size() > 0) sb.delete(sb.length() - 2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
