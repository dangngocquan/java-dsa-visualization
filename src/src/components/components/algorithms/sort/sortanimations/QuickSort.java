package src.components.components.algorithms.sort.sortanimations;

import src.Config;
import src.components.components.algorithms.sort.SortAlgorithmScreen;
import src.components.components.algorithms.sort.viewsort.Bar;

import java.util.Stack;
import java.util.Timer;

public class QuickSort extends SortAnimation {
    private Stack<int[]> ranges;
    private int l; // left/low index
    private int r; // right/high index
    private int pivotIndex;
    private int i;

    public QuickSort(SortAlgorithmScreen sortAlgorithmScreen, Bar[] bars, Timer timer, int period) {
        super(sortAlgorithmScreen, bars, timer, period);
        ranges = new Stack<>();
        l = 0;
        r = bars.length - 1;
        i = l;
        pivotIndex = l;
    }

    @Override
    public void run() {
        if (isRunning()) {
            if (i == l) {
                solveWhenStartARange();
            } else if (i > r) {
                solveWhenFinishARange();
            } else {
                solveWhenInRange();
            }
        } else if (isEnded()) {
            sortAlgorithmScreen.getViewSort().runEndAnimation();
            sortAlgorithmScreen.endSort();
        }
    }

    public void solveWhenStartARange() {
        sortAlgorithmScreen.getViewSort().pickUpBar(
                i,
                sortAlgorithmScreen.getViewSort().initialY0
        );
        sortAlgorithmScreen.getViewSort().checkBar(
                i,
                Config.COLOR_BAR_FLAG
        );
        i++;
    }

    public void solveWhenInRange() {
        if (animationStep == 0) {
            sortAlgorithmScreen.getViewSort().checkBar(
                    i,
                    Config.COLOR_BAR_CHECKING
            );
            animationStep++;
        } else if (animationStep == 1) {
            if (bars[i].compareTo(bars[l]) < 0) {
                pivotIndex++;
                sortAlgorithmScreen.getViewSort().swapBars(
                        pivotIndex,
                        sortAlgorithmScreen.getViewSort().initialY0,
                        i,
                        sortAlgorithmScreen.getViewSort().initialY0
                );
                animationStep++;
            } else {
                sortAlgorithmScreen.getViewSort().checkBar(
                        i,
                        Config.COLOR_BAR_PLAIN
                );
                animationStep = 0;
                i++;
            }
        } else if (animationStep == 2) {
            sortAlgorithmScreen.getViewSort().checkBar(
                    pivotIndex,
                    Config.COLOR_BAR_SMALLER_PIVOT
            );
            animationStep = 0;
            i++;
        }
    }

    public void solveWhenFinishARange() {
        if (pivotIndex == l) {
            sortAlgorithmScreen.getViewSort().pickDownBar(
                    l,
                    sortAlgorithmScreen.getViewSort().initialY1
            );
            sortAlgorithmScreen.getViewSort().checkBar(
                    l,
                    Config.COLOR_BAR_TEMP_SORTED
            );
            if (pivotIndex + 1 == r) {
                sortAlgorithmScreen.getViewSort().checkBar(
                        r,
                        Config.COLOR_BAR_TEMP_SORTED
                );
            } else if (pivotIndex + 1 < r) {
                ranges.add(new int[] {pivotIndex + 1, r});
            }
            if (ranges.isEmpty()) {
                end();
            } else {
                int[] range = ranges.pop();
                l = range[0];
                r = range[1];
                i = l;
                pivotIndex = l;
            }
        } else {
            if (animationStep == 0) {
                sortAlgorithmScreen.getViewSort().pickUpBar(
                        pivotIndex,
                        sortAlgorithmScreen.getViewSort().initialY0
                );
                animationStep++;
            } else if (animationStep == 1) {
                sortAlgorithmScreen.getViewSort().swapBars(
                        l,
                        sortAlgorithmScreen.getViewSort().initialY1,
                        pivotIndex,
                        sortAlgorithmScreen.getViewSort().initialY1
                );
                animationStep++;
            } else if (animationStep == 2) {
                sortAlgorithmScreen.getViewSort().pickDownBar(
                        l,
                        sortAlgorithmScreen.getViewSort().initialY1
                );
                sortAlgorithmScreen.getViewSort().pickDownBar(
                        pivotIndex,
                        sortAlgorithmScreen.getViewSort().initialY1
                );
                sortAlgorithmScreen.getViewSort().checkBar(
                        pivotIndex,
                        Config.COLOR_BAR_TEMP_SORTED
                );

                if (pivotIndex + 1 == r) {
                    sortAlgorithmScreen.getViewSort().checkBar(
                            r,
                            Config.COLOR_BAR_TEMP_SORTED
                    );
                } else if (pivotIndex + 1 < r) {
                    ranges.add(new int[] {pivotIndex + 1, r});
                }

                if (l == pivotIndex - 1) {
                    sortAlgorithmScreen.getViewSort().checkBar(
                            l,
                            Config.COLOR_BAR_TEMP_SORTED
                    );
                } else if (l < pivotIndex - 1) {
                    ranges.add(new int[] {l, pivotIndex - 1});
                }

                animationStep++;
            } else if (animationStep == 3) {
                if (l < pivotIndex-1) {
                    sortAlgorithmScreen.getViewSort().setColorAndLocationBars(
                            l, pivotIndex-1,
                            Config.COLOR_BAR_PLAIN,
                            sortAlgorithmScreen.getViewSort().initialY0
                    );
                }

                if (ranges.isEmpty()) {
                    end();
                } else {
                    int[] range = ranges.pop();
                    l = range[0];
                    r = range[1];
                    i = l;
                    pivotIndex = l;
                }
                animationStep = 0;
            }
        }
    }
}
