package src.models.datastructures.list;

public interface MyList<E> extends Iterable<E> {
    int size();
    boolean add(E value);
    void add(int index, E value);
    E get(int index);
    E set(int index, E value);
    E remove(int index);
    boolean remove(E value);
    boolean isEmpty();
}
