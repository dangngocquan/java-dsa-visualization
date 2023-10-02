package src.components.components.algorithms.sort.sortanimations;

import src.components.components.algorithms.sort.viewsort.Bar;
import src.components.components.algorithms.sort.viewsort.ViewSort;

import java.util.Timer;

public class BubbleSort extends SortAnimation {
    private int i;
    private int j;
    private boolean isSorted;

    public BubbleSort(ViewSort viewSort, Bar[] bars, Timer timer, int period) {
        super(viewSort, bars, timer, period);
        i = 0;
        j = 1;
        isSorted = true;
    }

    @Override
    public void run() {
        if (isRunning()) {
            if (i >= bars.length - 1) {
                end();
            } else {
                if (j >= bars.length - i) {
                    i++;
                    j = 1;
                    if (isSorted) {
                        end();
                    }
                    isSorted = true;
                } else {
                    if (animationStep == 0) {
                        viewSort.checkBar(j-1);
                        viewSort.checkBar(j);
                        animationStep++;
                    } else if (animationStep == 1) {
                        Bar v1 = bars[j-1];
                        Bar v2 = bars[j];
                        if (v1.compareTo(v2) > 0) {
                            viewSort.swapBars(j-1, j);
                            isSorted = false;
                            animationStep++;
                        } else {
                            viewSort.uncheckBar(j-1);
                            viewSort.uncheckBar(j);
                            animationStep = 0;
                            j++;
                        }
                    } else if (animationStep == 2) {
                        viewSort.uncheckBar(j-1);
                        viewSort.uncheckBar(j);
                        animationStep = 0;
                        j++;
                    }
                }
            }
        }
    }
}
