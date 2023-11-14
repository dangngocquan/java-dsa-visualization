package src.models.algorithms.searching;

public class SequentialSearch<E extends Comparable<E>>
        implements SearchAlgorithm<E, Integer> {
    @Override
    public Integer search(E[] data, E value) {
        if (value == null) return -1;
        for (int i = 0; i < data.length; i++)
            if (value.compareTo(data[i]) == 0) return i;
        return -1;
    }
}
