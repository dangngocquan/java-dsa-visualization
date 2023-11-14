package src.models.algorithms.searching;

public interface SearchAlgorithm<E extends Comparable<E>, T> {
    T search(E[] data, E value);
}
