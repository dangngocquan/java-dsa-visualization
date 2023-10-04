package src.components.components.algorithms.sort.sortanimations;

import src.Config;
import src.components.components.algorithms.sort.SortAlgorithmScreen;
import src.components.components.algorithms.sort.viewsort.Bar;

import java.util.Timer;

public class SelectionSort extends SortAnimation {
    private int i;
    private int j;
    private int indexMinBar;

    public SelectionSort(
            SortAlgorithmScreen sortAlgorithmScreen,
            Bar[] bars, Timer timer, int period) {
        super(sortAlgorithmScreen, bars, timer, period);
        i = 0;
        j = 0;
    }

    @Override
    public void run() {
        if (isRunning()) {
            if (i >= bars.length) {
                end();
            } else {
                if (j == i) {
                    indexMinBar = i;
                    sortAlgorithmScreen.getViewSort().checkBar(i, Config.COLOR_BAR_FLAG);
                    j++;
                } else if (j >= bars.length) {
                    if (i == indexMinBar) {
                        sortAlgorithmScreen.getViewSort().checkBar(i, Config.COLOR_BAR_TEMP_SORTED);
                        i++;
                        j = i;
                    } else {
                        if (animationStep == 0) {
                            sortAlgorithmScreen.getViewSort().swapBars(
                                    i,
                                    sortAlgorithmScreen.getViewSort().initialY0,
                                    indexMinBar,
                                    sortAlgorithmScreen.getViewSort().initialY0
                            );
                            animationStep++;
                        } else if (animationStep == 1) {
                            sortAlgorithmScreen.getViewSort().checkBar(i, Config.COLOR_BAR_TEMP_SORTED);
                            i++;
                            j = i;
                            indexMinBar = i;
                            animationStep = 0;
                        }
                    }

                } else {
                    if (animationStep == 0) {
                        sortAlgorithmScreen.getViewSort().checkBar(j, Config.COLOR_BAR_CHECKING);
                        animationStep++;
                    } else if (animationStep == 1) {
                        if (bars[j].compareTo(bars[indexMinBar]) < 0) {
                            sortAlgorithmScreen.getViewSort().checkBar(indexMinBar, Config.COLOR_BAR_PLAIN);
                            animationStep++;
                        } else {
                            sortAlgorithmScreen.getViewSort().checkBar(j, Config.COLOR_BAR_PLAIN);
                            animationStep = 0;
                            j++;
                        }
                    } else if (animationStep == 2) {
                        sortAlgorithmScreen.getViewSort().checkBar(j, Config.COLOR_BAR_FLAG);
                        indexMinBar = j;
                        animationStep = 0;
                        j++;
                    }

                }
            }
        } else if (isEnded()) {
            sortAlgorithmScreen.getViewSort().runEndAnimation();
            sortAlgorithmScreen.endSort();
        }
    }
}
