package src.models.datastructures.tree;

import java.util.Arrays;

public class ArrayBinaryTree<E> extends AbstractBinaryTree<E, Integer> {
    private E[] array;
    public static final int DEFAULT_CAPACITY = 64;

    public ArrayBinaryTree() {
        array = (E[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayBinaryTree(int capacity) {
        array = (E[]) new Object[capacity];
    }

    @Override
    public Integer root() {
        if (1 >= array.length) enlarge();
        return 1;
    }

    @Override
    public E elementOfNode(Integer p) {
        if (p == null) return null;
        if (isValidIndex(p)) return array[p];
        return null;
    }

    @Override
    public Integer parent(Integer p) {
        if (p == null) return null;
        if (isValidIndex(p / 2)) return p / 2;
        return null;
    }

    @Override
    public Integer left(Integer p) {
        if (p == null) return null;
        if (isValidIndex(p * 2)) return p * 2;
        return null;
    }

    @Override
    public Integer right(Integer p) {
        if (p == null) return null;
        if (isValidIndex(p * 2 + 1)) return p * 2 + 1;
        return null;
    }

    @Override
    public Integer sibling(Integer p) {
        if (p == null) return null;
        int siblingIndex = p % 2 == 0? p + 1 : p - 1;
        if (isValidIndex(siblingIndex)) return siblingIndex;
        return null;
    }

    public void setRoot(E element) {
        if (1 >= array.length) enlarge();
        array[1] = element;
    }

    public void setLeft(Integer p, E element) {
        if (array[p] == null) return;
        if (2 * p >= array.length) enlarge();
        array[2 * p] = element;
    }

    public void setRight(Integer p, E element) {
        if (array[p] == null) return;
        if (2 * p + 1 >= array.length) enlarge();
        array[2 * p + 1] = element;
    }

    private void enlarge() {
        if (array.length * 2 + 1 <= array.length) throw new IllegalArgumentException("Reached max size.");
        array = Arrays.copyOf(array, array.length * 2 + 1);
    }

    private boolean isValidIndex(int index) {
        return index >= 1 && index < array.length;
    }
}
