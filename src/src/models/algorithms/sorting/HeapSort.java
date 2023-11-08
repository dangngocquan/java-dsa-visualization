package src.models.algorithms.sorting;


public class HeapSort implements SortAlgorithm {
    @Override
    public <T extends Comparable<T>> void sort(T[] array) {
        int i = 0;
        while (i < array.length) {
            upHeap(array, i);
            i++;
        }
        i = array.length-1;
        while (i > -1) {
            T temp = array[i];
            array[i] = array[0];
            array[0] = temp;
            downHeap(array, i);
            i--;
        }
    }

    private <T extends Comparable<T>> void upHeap(T[] array, int i) {
        while (i > 0 && array[i].compareTo(array[(i-1)/2]) > 0) {
            T temp = array[i];
            array[i] = array[(i-1)/2];
            array[(i-1)/2] = temp;
            i = (i-1)/2;
        }
    }

    private <T extends Comparable<T>> void downHeap(T[] array, int last) {
        int j = 0;
        while (2*j+1 < last) { // if node j has any child node
            if (2*j+2 < last) { // 2 children
                if (array[j].compareTo(array[2*j+1]) > 0 && array[j].compareTo(array[2*j+2]) > 0) {
                    break;
                }
                int index = array[2*j+1].compareTo(array[2*j+2]) > 0? 2*j+1 : 2*j+2;
                T temp = array[j];
                array[j] = array[index];
                array[index] = temp;
                j = index;
            } else { // 1 child
                if (array[j].compareTo(array[2*j+1]) > 0) break;
                T temp = array[j];
                array[j] = array[2*j+1];
                array[2*j+1] = temp;
                j = 2*j+1;
            }
        }
    }
}
