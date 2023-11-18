package src.models.datastructures.queue;

public interface QueueInterface<E> extends Iterable<E> {
    int size();

    void enqueue(E element);

    E dequeue();

    E first();

    E last();

    boolean isEmpty();
}
