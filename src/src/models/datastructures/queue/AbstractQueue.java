package src.models.datastructures.queue;

public abstract class AbstractQueue<E> implements QueueInterface<E> {
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (E e : this) {
            sb.append(e).append(", ");
        }
        if (sb.length() > 1) sb.delete(sb.length()-2, sb.length());
        sb.append("]");
        return sb.toString();
    }
}
