package src.models.algorithms.sorting;

import java.util.Arrays;

public class TestSortAlgorithms {
    public void run() {
        Integer[] a = new Integer[] {1, 2, 2, 3, 1, 5, 3, 6, 9, 4};
        new HeapSort().sort(a);
        System.out.println(Arrays.toString(a));
    }
}
