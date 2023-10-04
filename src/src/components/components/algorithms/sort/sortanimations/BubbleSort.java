package src.components.components.algorithms.sort.sortanimations;

import src.Config;
import src.components.components.algorithms.sort.SortAlgorithmScreen;
import src.components.components.algorithms.sort.viewsort.Bar;

import java.util.Timer;

public class BubbleSort extends SortAnimation {
    private int turn;
    private int j;
    private boolean isSorted;

    public BubbleSort(SortAlgorithmScreen sortAlgorithmScreen, Bar[] bars, Timer timer, int period) {
        super(sortAlgorithmScreen, bars, timer, period);
        turn = 0;
        j = 1;
        isSorted = true;
    }

    @Override
    public void run() {
        if (isRunning()) {
            if (turn >= bars.length - 1) {
                end();
            } else {
                if (j >= bars.length - turn) {
                    if (isSorted) {
                        end();
                    } else {
                        sortAlgorithmScreen.getViewSort().tempSortedBar(j-1);
                        turn++;
                        j = 1;
                        isSorted = true;
                    }
                } else {
                    if (animationStep == 0) {
                        sortAlgorithmScreen.getViewSort().checkBar(j-1, Config.COLOR_BAR_CHECKING);
                        sortAlgorithmScreen.getViewSort().checkBar(j, Config.COLOR_BAR_CHECKING);
                        animationStep++;
                    } else if (animationStep == 1) {
                        Bar v1 = bars[j-1];
                        Bar v2 = bars[j];
                        if (v1.compareTo(v2) > 0) {
                            sortAlgorithmScreen.getViewSort().swapBars(
                                    j-1,
                                    sortAlgorithmScreen.getViewSort().initialY0,
                                    j,
                                    sortAlgorithmScreen.getViewSort().initialY0
                            );
                            isSorted = false;
                            animationStep++;
                        } else {
                            sortAlgorithmScreen.getViewSort().checkBar(j-1, Config.COLOR_BAR_PLAIN);
                            sortAlgorithmScreen.getViewSort().checkBar(j, Config.COLOR_BAR_PLAIN);
                            animationStep = 0;
                            j++;
                        }
                    } else if (animationStep == 2) {
                        sortAlgorithmScreen.getViewSort().checkBar(j-1, Config.COLOR_BAR_PLAIN);
                        sortAlgorithmScreen.getViewSort().checkBar(j, Config.COLOR_BAR_PLAIN);
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
