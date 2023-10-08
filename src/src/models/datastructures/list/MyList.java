package src.models.datastructures.list;

public interface MyList<E> extends Iterable<E> {
    public int size();
    public boolean add(E value);
    public void add(int index, E value);
    public E get(int index);
    public E set(int index, E value);
    public E remove(int index);
    public boolean remove(E value);
    public boolean isEmpty();
}
