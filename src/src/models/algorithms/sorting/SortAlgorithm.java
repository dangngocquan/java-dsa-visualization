package src.models.algorithms.sorting;

public interface SortAlgorithm {
    <T extends Comparable<T>> void sort(T[] a);
}
