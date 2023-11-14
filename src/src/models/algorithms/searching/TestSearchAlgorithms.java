package src.models.algorithms.searching;

public class TestSearchAlgorithms {
    public void run() {
        Integer[] a = new Integer[] {1, 7, 2, 3, 8, 5, 10, 6, 9, 4};
        SequentialSearch<Integer> sequentialSearch = new SequentialSearch<>();
        System.out.println(sequentialSearch.search(a, 6));
        Integer[] a2 = new Integer[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        BinarySearch<Integer> binarySearch = new BinarySearch<>();
        System.out.println(binarySearch.search(a2, 6));
    }
}
