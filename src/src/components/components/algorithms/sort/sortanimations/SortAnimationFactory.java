package src.components.components.algorithms.sort.sortanimations;

import src.components.components.algorithms.sort.SortAlgorithmScreen;
import src.components.components.algorithms.sort.viewcontroller.ViewController;
import src.components.components.algorithms.sort.viewsort.Bar;
import src.components.components.algorithms.sort.viewsort.ViewSort;

import java.util.Timer;

public class SortAnimationFactory {
    public static SortAnimation createSortAnimation(
            String name, SortAlgorithmScreen sortAlgorithmScreen, Bar[] bars, Timer timer, int period) {
        switch (name) {
            case "Bubble Sort" :
                return new BubbleSort(
                        sortAlgorithmScreen, bars, timer, period
                );
            case "Selection Sort" :
                return new SelectionSort(
                        sortAlgorithmScreen, bars, timer, period
                );
            case "Insertion Sort" :
                return new InsertionSort(
                        sortAlgorithmScreen, bars, timer, period
                );
            case "Quick Sort" :
                return new QuickSort(
                        sortAlgorithmScreen, bars, timer, period
                );
            case "Merge Sort" :
                return new MergeSort(
                        sortAlgorithmScreen, bars, timer, period
                );
            default:
                return null;
        }
    }
}
