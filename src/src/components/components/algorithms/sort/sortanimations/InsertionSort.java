package src.components.components.algorithms.sort.sortanimations;

import src.Config;
import src.components.components.algorithms.sort.SortAlgorithmScreen;
import src.components.components.algorithms.sort.viewsort.Bar;

import java.util.Timer;

public class InsertionSort extends SortAnimation {
    private int i;
    private int j;

    public InsertionSort(
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
                    sortAlgorithmScreen.getViewSort().pickUpBar(
                            j, sortAlgorithmScreen.getViewSort().initialY0
                    );
                    sortAlgorithmScreen.getViewSort().checkBar(j, Config.COLOR_BAR_FLAG);
                    j--;
                } else if (j < 0) {
                    if (animationStep == 0) {
                        sortAlgorithmScreen.getViewSort().pickDownBar(
                                0, sortAlgorithmScreen.getViewSort().initialY1
                        );
                        animationStep++;
                    } else if (animationStep == 1) {
                        sortAlgorithmScreen.getViewSort().checkBar(0, Config.COLOR_BAR_TEMP_SORTED);
                        i++;
                        j = i;
                        animationStep = 0;
                    }
                } else  {
                    if (animationStep == 0) {
                        sortAlgorithmScreen.getViewSort().checkBar(j, Config.COLOR_BAR_CHECKING);
                        animationStep++;
                    } else if (animationStep == 1) {
                        if (bars[j].compareTo(bars[j+1]) > 0) {
                            sortAlgorithmScreen.getViewSort().swapBars(
                                    j,
                                    sortAlgorithmScreen.getViewSort().initialY0,
                                    j+1,
                                    sortAlgorithmScreen.getViewSort().initialY1

                            );
                            sortAlgorithmScreen.getViewSort().checkBar(j+1, Config.COLOR_BAR_TEMP_SORTED);
                            animationStep = 0;
                            j--;
                        } else {
                            sortAlgorithmScreen.getViewSort().checkBar(j, Config.COLOR_BAR_TEMP_SORTED);
                            animationStep++;
                        }
                    } else if (animationStep == 2) {
                        sortAlgorithmScreen.getViewSort().pickDownBar(
                                j+1, sortAlgorithmScreen.getViewSort().initialY1
                        );
                        animationStep++;
                    } else if (animationStep == 3) {
                        sortAlgorithmScreen.getViewSort().checkBar(j+1, Config.COLOR_BAR_TEMP_SORTED);
                        animationStep = 0;
                        i++;
                        j = i;
                    }
                }
            }
        } else if (isEnded()) {
            sortAlgorithmScreen.getViewSort().runEndAnimation();
            sortAlgorithmScreen.endSort();
        }
    }
}
