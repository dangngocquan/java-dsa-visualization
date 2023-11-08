package src.components.components.algorithms.sort.heapsort;

import src.Config;
import src.components.components.algorithms.sort.AbstractSortAlgorithmAnimation;
import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;
import src.components.components.algorithms.sort.Bar;
import src.components.components.algorithms.sort.ViewSortAlgorithmAction;

public class HeapSortAnimation extends AbstractSortAlgorithmAnimation {
    private int i;
    private int j;
    private int turn;
    private final Bar[] bars;
    private int subAnimationStep;
    private int tempIndex;

    public HeapSortAnimation(AbstractSortAlgorithmScreen rootScreen, int period) {
        super(rootScreen, period);
        bars = rootScreen.getViewAction().bars;
        i = 0;
        j = i;
        turn = 0;
        subAnimationStep = 0;
    }

    @Override
    public void run() {
        if (isRunning()) {
            if (i >= bars.length) {
                turn = 1;
                i = bars.length-1;
                j = 0;
            } else if (i < 0) {
                end();
            } else {
                if (turn == 0) {
                    upHeap();
                } else {
                    if (animationStep == 0) {
                        getRootScreen().getViewAction().pickUpBar(0, ViewSortAlgorithmAction.initialY0);
                        getRootScreen().getViewAction().pickUpBar(i, ViewSortAlgorithmAction.initialY0);
                        animationStep = 1;
                    } else if (animationStep == 1) {
                        getRootScreen().getViewAction().swapBars(
                                0,
                                ViewSortAlgorithmAction.initialY1,
                                i,
                                ViewSortAlgorithmAction.initialY1
                        );
                        animationStep= 2;
                    } else if (animationStep == 2) {
                        getRootScreen().getViewAction().pickDownBar(0, ViewSortAlgorithmAction.initialY1);
                        getRootScreen().getViewAction().pickDownBar(i, ViewSortAlgorithmAction.initialY1);
                        getRootScreen().getViewAction().checkBar(i, Config.COLOR_BLUE);
                        animationStep = 3;
                    } else if (animationStep == 3) {
                        downHeap();
                    }
                }
            }
        }
    }

    private void upHeap() {
        if (subAnimationStep == 0) {
            getRootScreen().getViewAction().checkBar(j, Config.COLOR_RED);
            getRootScreen().getViewAction().pickUpBar(j, ViewSortAlgorithmAction.initialY0);
            subAnimationStep = 1;
        } else if (subAnimationStep == 1) {
            if (j > 0) {
                getRootScreen().getViewAction().checkBar((j-1)/2, Config.COLOR_YELLOW);
                subAnimationStep = 2;
            } else {
                getRootScreen().getViewAction().pickDownBar(j, ViewSortAlgorithmAction.initialY1);
                getRootScreen().getViewAction().checkBar(j, Config.COLOR_WHITE);
                i++;
                j = i;
                subAnimationStep = 0;
            }
        } else if (subAnimationStep == 2) {
            if (bars[j].compareTo(bars[(j-1)/2]) > 0) {
                getRootScreen().getViewAction().swapBars(
                        (j-1)/2,
                        ViewSortAlgorithmAction.initialY0,
                        j,
                        ViewSortAlgorithmAction.initialY1
                );
                subAnimationStep = 3;
            } else {
                getRootScreen().getViewAction().pickDownBar(j, ViewSortAlgorithmAction.initialY1);
                getRootScreen().getViewAction().checkBar((j-1)/2, Config.COLOR_WHITE);
                getRootScreen().getViewAction().checkBar(j, Config.COLOR_WHITE);
                i++;
                j = i;
                subAnimationStep = 0;
            }
        } else if (subAnimationStep == 3) {
            getRootScreen().getViewAction().checkBar(j, Config.COLOR_WHITE);
            j = (j-1)/2;
            subAnimationStep = 1;
        }
    }

    private void downHeap() {
        if (subAnimationStep == 0) {
            getRootScreen().getViewAction().checkBar(j, Config.COLOR_RED);
            getRootScreen().getViewAction().pickUpBar(j, ViewSortAlgorithmAction.initialY0);
            subAnimationStep = 1;
        } else if (subAnimationStep == 1) {
            if (2*j+1 < i) { // if node j has any child node
                getRootScreen().getViewAction().checkBar(2*j+1, Config.COLOR_YELLOW);
                subAnimationStep = 2;
                if (2*j+2 < i) { // 2 children
                    getRootScreen().getViewAction().checkBar(2*j+2, Config.COLOR_YELLOW);
                    subAnimationStep = 3;
                }
            } else {
                getRootScreen().getViewAction().pickDownBar(j, ViewSortAlgorithmAction.initialY1);
                getRootScreen().getViewAction().checkBar(j, Config.COLOR_WHITE);
                i--;
                j = 0;
                animationStep = 0;
                subAnimationStep = 0;
            }
        } else if (subAnimationStep == 2) { // 1 child
            if (bars[j].compareTo(bars[2*j+1]) > 0) {
                getRootScreen().getViewAction().pickDownBar(j, ViewSortAlgorithmAction.initialY1);
                getRootScreen().getViewAction().checkBar(j, Config.COLOR_WHITE);
                getRootScreen().getViewAction().checkBar(2*j+1, Config.COLOR_WHITE);
                i--;
                j = 0;
                animationStep = 0;
                subAnimationStep = 0;
                return;
            }
            getRootScreen().getViewAction().swapBars(
                    j,
                    ViewSortAlgorithmAction.initialY1,
                    2*j+1,
                    ViewSortAlgorithmAction.initialY0

            );
            subAnimationStep = 4;
        } else if (subAnimationStep == 3) { // 2 children
            if (bars[j].compareTo(bars[2*j+1]) > 0 && bars[j].compareTo(bars[2*j+2]) > 0) {
                getRootScreen().getViewAction().pickDownBar(j, ViewSortAlgorithmAction.initialY1);
                getRootScreen().getViewAction().checkBar(j, Config.COLOR_WHITE);
                getRootScreen().getViewAction().checkBar(2*j+1, Config.COLOR_WHITE);
                getRootScreen().getViewAction().checkBar(2*j+2, Config.COLOR_WHITE);
                i--;
                j = 0;
                animationStep = 0;
                subAnimationStep = 0;
                return;
            }
            int indexSmaller = bars[2*j+1].compareTo(bars[2*j+2]) > 0? 2*j+2 : 2*j+1;
            getRootScreen().getViewAction().checkBar(indexSmaller, Config.COLOR_WHITE);
            subAnimationStep = 5;
        } else if (subAnimationStep == 4) { // 1 child continue
            getRootScreen().getViewAction().checkBar(j, Config.COLOR_WHITE);
            subAnimationStep = 1;
            j = 2*j+1;
        } else if (subAnimationStep == 5) { // 2 children continue
            tempIndex = bars[2*j+1].compareTo(bars[2*j+2]) > 0? 2*j+1 : 2*j+2;
            getRootScreen().getViewAction().swapBars(
                    j,
                    ViewSortAlgorithmAction.initialY1,
                    tempIndex,
                    ViewSortAlgorithmAction.initialY0
            );
            subAnimationStep = 6;
        } else if (subAnimationStep == 6) { // 2 children continue
            getRootScreen().getViewAction().checkBar(j, Config.COLOR_WHITE);
            j = tempIndex;
            subAnimationStep = 1;
        }
    }
}
