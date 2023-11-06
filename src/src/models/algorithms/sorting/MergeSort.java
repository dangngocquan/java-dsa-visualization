package src.models.algorithms.sorting;

import java.util.Arrays;

public class MergeSort {
    public <T extends Comparable<T>> void sort(T[] a) {
        mergeSort(a, 0, a.length-1);
    }

    // Sort a[left, right]
    private static <T extends Comparable<T>> void mergeSort(T[] a, int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            mergeSort(a, left, mid);
            mergeSort(a, mid + 1, right);
            merge(a, left, mid, right);
        }
    }

    // merge two sorted sub-array a[left, mid] and a[mid+1, right]
    private static <T extends Comparable<T>> void merge(T[] a, int left, int mid, int right) {
        int bLength = right - left + 1;
        T[] b = Arrays.copyOf(a, bLength);
        int i = 0;
        int i1 = left;
        int i2 = mid+1;
        while (i1 <= mid && i2 <= right) {
            if (a[i1].compareTo(a[i2]) <= 0) {
                b[i] = a[i1];
                i1++;
            } else {
                b[i] = a[i2];
                i2++;
            }
            i++;
        }
        while (i1 <= mid) b[i++] = a[i1++];
        while (i2 <= right) b[i++] = a[i2++];
        i = 0;
        while (i < bLength) a[i+left] = b[i++];
    }
}
