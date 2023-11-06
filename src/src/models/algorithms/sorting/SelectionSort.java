package src.models.algorithms.sorting;

public class SelectionSort {
    public <T extends Comparable<T>> void sort(T[] a) {
        for (int i = 0; i < a.length-1; i++) {
            int indexOfMinValue = i;
            for (int j = i+1; j < a.length; j++) {
                if (a[j].compareTo(a[indexOfMinValue]) < 0) {
                    indexOfMinValue = j;
                }
            }
            if (indexOfMinValue != i) {
                T temp = a[indexOfMinValue];
                a[indexOfMinValue] = a[i];
                a[i] = temp;
            }
        }
    }
}
