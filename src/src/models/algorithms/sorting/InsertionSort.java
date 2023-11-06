package src.models.algorithms.sorting;

public class InsertionSort {
    public <T extends Comparable<T>> void sort(T[] a) {
        for (int i = 1; i < a.length; i++) {
            T valueInsert = a[i];
            int j = i-1;
            while (j >= 0) {
                if (a[j].compareTo(valueInsert) > 0) {
                    a[j+1] = a[j];
                    j--;
                } else {
                    break;
                }
            }
            a[j+1] = valueInsert;
        }
    }
}
