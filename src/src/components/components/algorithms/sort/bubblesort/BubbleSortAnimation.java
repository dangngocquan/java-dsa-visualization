package src.components.components.algorithms.sort.bubblesort;

import src.Config;
import src.components.components.algorithms.sort.AbstractSortAlgorithmAnimation;
import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;
import src.components.components.algorithms.sort.Bar;
import src.components.components.algorithms.sort.ViewSortAlgorithmAction;

public class BubbleSortAnimation extends AbstractSortAlgorithmAnimation {
    private int turn;
    private int j;
    private boolean isSorted;
    private final Bar[] bars;

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
                    newTurnOrEnd();
                } else {
                    compareAdjacencyElements();
                }
            }
        }
    }

    public void newTurnOrEnd() {
        if (isSorted) {
            end();
        } else {
            getRootScreen().setDescription(
                    String.format(
                            "a[%d] = %d is correct index now.",
                            j-1, bars[j-1].getValue()
                    )
            );
            getRootScreen().getViewAction().tempSortedBar(j-1);
            turn++;
            j = 1;
            isSorted = true;
        }
    }

    public void compareAdjacencyElements() {
        if (animationStep == 0) {
            getRootScreen().setDescription(
                    String.format(
                            "Comparing a[%d] = %d to a[%d] = %d.",
                            j-1, bars[j-1].getValue(), j, bars[j].getValue()
                    )
            );
            getRootScreen().getViewAction().checkBar(j-1, Config.COLOR_YELLOW);
            getRootScreen().getViewAction().checkBar(j, Config.COLOR_YELLOW);
            animationStep++;
        } else if (animationStep == 1) {
            Bar v1 = bars[j-1];
            Bar v2 = bars[j];
            if (v1.compareTo(v2) > 0) {
                getRootScreen().setDescription(
                        String.format(
                                "a[%d] = %d is greater than a[%d] = %d. Swap them.",
                                j-1, bars[j-1].getValue(), j, bars[j].getValue()
                        )
                );
                getRootScreen().getViewAction().swapBars(
                        j-1,
                        ViewSortAlgorithmAction.initialY0,
                        j,
                        ViewSortAlgorithmAction.initialY0
                );
                isSorted = false;
                animationStep++;
            } else {
                getRootScreen().setDescription(
                        String.format(
                                "a[%d] = %d is equals or smaller than a[%d] = %d. No action.",
                                j-1, bars[j-1].getValue(), j, bars[j].getValue()
                        )
                );
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
