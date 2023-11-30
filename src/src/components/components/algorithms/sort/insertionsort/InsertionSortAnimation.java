package src.components.components.algorithms.sort.insertionsort;

import src.Config;
import src.components.components.algorithms.sort.AbstractSortAlgorithmAnimation;
import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;
import src.components.components.algorithms.sort.Bar;
import src.components.components.algorithms.sort.ViewSortAlgorithmAction;

public class InsertionSortAnimation extends AbstractSortAlgorithmAnimation {
    private int i;
    private int j;
    private final Bar[] bars;

    public InsertionSortAnimation(AbstractSortAlgorithmScreen rootScreen, int period) {
        super(rootScreen, period);
        bars = rootScreen.getViewAction().bars;
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
                    pickUpElementInserting();
                } else if (j < 0) {
                    pickDownElementInserting();
                } else  {
                    insertElementToArray();
                }
            }
        }
    }

    public void pickUpElementInserting() {
        getRootScreen().getViewAction().pickUpBar(
                j, ViewSortAlgorithmAction.initialY0
        );
        getRootScreen().getViewAction().checkBar(j, Config.COLOR_RED);
        getRootScreen().setDescription(
                String.format(
                    "Inserting a[%d] = %d to sorted subarray.",
                    j, bars[j].getValue()
                )
        );
        j--;
    }

    public void pickDownElementInserting() {
        if (animationStep == 0) {
            getRootScreen().setDescription(
                    "Inserted value to sorted subarray."
            );
            getRootScreen().getViewAction().pickDownBar(
                    0, ViewSortAlgorithmAction.initialY1
            );
            animationStep++;
        } else if (animationStep == 1) {
            getRootScreen().getViewAction().checkBar(0, Config.COLOR_BLUE);
            i++;
            j = i;
            animationStep = 0;
        }
    }


    public void insertElementToArray() {
        if (animationStep == 0) {
            getRootScreen().getViewAction().checkBar(j, Config.COLOR_YELLOW);
            getRootScreen().setDescription(
                    String.format(
                            "Comparing value inserting (%d) to a[%d] = %d",
                            bars[j+1].getValue(), j, bars[j].getValue()
                    )
            );
            animationStep++;
        } else if (animationStep == 1) {
            if (bars[j].compareTo(bars[j+1]) > 0) {
                getRootScreen().setDescription(
                        String.format(
                                "Value inserting (%d) is smaller than a[%d] = %d. Swap them.",
                                bars[j+1].getValue(), j, bars[j].getValue()
                        )
                );
                getRootScreen().getViewAction().swapBars(
                        j,
                        ViewSortAlgorithmAction.initialY0,
                        j+1,
                        ViewSortAlgorithmAction.initialY1

                );
                getRootScreen().getViewAction().checkBar(j+1, Config.COLOR_BLUE);
                animationStep = 0;
                j--;
            } else {
                getRootScreen().setDescription(
                        String.format(
                                "Value inserting (%d) is equals or greater than a[%d] = %d. No action.",
                                bars[j+1].getValue(), j, bars[j].getValue()
                        )
                );
                getRootScreen().getViewAction().checkBar(j, Config.COLOR_BLUE);
                animationStep++;
            }
        } else if (animationStep == 2) {
            getRootScreen().getViewAction().pickDownBar(
                    j+1, ViewSortAlgorithmAction.initialY1
            );
            animationStep++;
        } else if (animationStep == 3) {
            getRootScreen().setDescription(
                    "Inserted value to sorted subarray."
            );
            getRootScreen().getViewAction().checkBar(j+1, Config.COLOR_BLUE);
            animationStep = 0;
            i++;
            j = i;
        }
    }
}
