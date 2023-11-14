package src.models.algorithms.searching;

public class BinarySearch<E extends Comparable<E>> implements SearchAlgorithm<E, Integer> {

    @Override
    public Integer search(E[] data, E value) {
        if (value == null) return -1;
        int l = 0;
        int r = data.length - 1;
        while (l <= r) {
            int m = (l + r) / 2;
            int compareResult = value.compareTo(data[m]);
            if (compareResult == 0) return m;
            if (compareResult < 0) {
                r = m - 1;
            } else {
                l = m + 1;
            }
        }
        return -1;
    }
}
