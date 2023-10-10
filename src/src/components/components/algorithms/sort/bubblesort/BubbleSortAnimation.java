package src.components.components.algorithms.sort.bubblesort;

import src.Config;
import src.components.components.algorithms.sort.AbstractSortAlgorithmAnimation;
import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;
import src.components.components.algorithms.sort.Bar;

public class BubbleSortAnimation extends AbstractSortAlgorithmAnimation {
    private int turn;
    private int j;
    private boolean isSorted;
    private Bar[] bars;

    public BubbleSortAnimation(AbstractSortAlgorithmScreen rootScreen, int period) {
        super(rootScreen, period);
        turn = 0;
        j = 1;
        isSorted = true;
        bars = rootScreen.getViewAction().bars;
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
                        getRootScreen().getViewAction().tempSortedBar(j-1);
                        turn++;
                        j = 1;
                        isSorted = true;
                    }
                } else {
                    if (animationStep == 0) {
                        getRootScreen().getViewAction().checkBar(j-1, Config.COLOR_BAR_CHECKING);
                        getRootScreen().getViewAction().checkBar(j, Config.COLOR_BAR_CHECKING);
                        animationStep++;
                    } else if (animationStep == 1) {
                        Bar v1 = bars[j-1];
                        Bar v2 = bars[j];
                        if (v1.compareTo(v2) > 0) {
                            getRootScreen().getViewAction().swapBars(
                                    j-1,
                                    getRootScreen().getViewAction().initialY0,
                                    j,
                                    getRootScreen().getViewAction().initialY0
                            );
                            isSorted = false;
                            animationStep++;
                        } else {
                            getRootScreen().getViewAction().checkBar(j-1, Config.COLOR_BAR_PLAIN);
                            getRootScreen().getViewAction().checkBar(j, Config.COLOR_BAR_PLAIN);
                            animationStep = 0;
                            j++;
                        }
                    } else if (animationStep == 2) {
                        getRootScreen().getViewAction().checkBar(j-1, Config.COLOR_BAR_PLAIN);
                        getRootScreen().getViewAction().checkBar(j, Config.COLOR_BAR_PLAIN);
                        animationStep = 0;
                        j++;
                    }
                }
            }
        }
    }
}
