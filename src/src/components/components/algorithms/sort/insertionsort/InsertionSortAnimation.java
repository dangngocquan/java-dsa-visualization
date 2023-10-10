package src.components.components.algorithms.sort.insertionsort;

import src.Config;
import src.components.components.algorithms.sort.AbstractSortAlgorithmAnimation;
import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;
import src.components.components.algorithms.sort.Bar;

public class InsertionSortAnimation extends AbstractSortAlgorithmAnimation {
    private int i;
    private int j;
    private Bar[] bars;

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
                    getRootScreen().getViewAction().pickUpBar(
                            j, getRootScreen().getViewAction().initialY0
                    );
                    getRootScreen().getViewAction().checkBar(j, Config.COLOR_BAR_FLAG);
                    j--;
                } else if (j < 0) {
                    if (animationStep == 0) {
                        getRootScreen().getViewAction().pickDownBar(
                                0, getRootScreen().getViewAction().initialY1
                        );
                        animationStep++;
                    } else if (animationStep == 1) {
                        getRootScreen().getViewAction().checkBar(0, Config.COLOR_BAR_TEMP_SORTED);
                        i++;
                        j = i;
                        animationStep = 0;
                    }
                } else  {
                    if (animationStep == 0) {
                        getRootScreen().getViewAction().checkBar(j, Config.COLOR_BAR_CHECKING);
                        animationStep++;
                    } else if (animationStep == 1) {
                        if (bars[j].compareTo(bars[j+1]) > 0) {
                            getRootScreen().getViewAction().swapBars(
                                    j,
                                    getRootScreen().getViewAction().initialY0,
                                    j+1,
                                    getRootScreen().getViewAction().initialY1

                            );
                            getRootScreen().getViewAction().checkBar(j+1, Config.COLOR_BAR_TEMP_SORTED);
                            animationStep = 0;
                            j--;
                        } else {
                            getRootScreen().getViewAction().checkBar(j, Config.COLOR_BAR_TEMP_SORTED);
                            animationStep++;
                        }
                    } else if (animationStep == 2) {
                        getRootScreen().getViewAction().pickDownBar(
                                j+1, getRootScreen().getViewAction().initialY1
                        );
                        animationStep++;
                    } else if (animationStep == 3) {
                        getRootScreen().getViewAction().checkBar(j+1, Config.COLOR_BAR_TEMP_SORTED);
                        animationStep = 0;
                        i++;
                        j = i;
                    }
                }
            }
        }
    }
}
