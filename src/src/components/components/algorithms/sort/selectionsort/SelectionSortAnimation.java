package src.components.components.algorithms.sort.selectionsort;

import src.Config;
import src.components.components.algorithms.sort.AbstractSortAlgorithmAnimation;
import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;
import src.components.components.algorithms.sort.Bar;

public class SelectionSortAnimation extends AbstractSortAlgorithmAnimation {
    private int i;
    private int j;
    private int indexMinBar;
    private Bar[] bars;

    public SelectionSortAnimation(AbstractSortAlgorithmScreen rootScreen, int period) {
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
                    indexMinBar = i;
                    getRootScreen().getViewAction().checkBar(i, Config.COLOR_BAR_FLAG);
                    j++;
                } else if (j >= bars.length) {
                    if (i == indexMinBar) {
                        getRootScreen().getViewAction().checkBar(i, Config.COLOR_BAR_TEMP_SORTED);
                        i++;
                        j = i;
                    } else {
                        if (animationStep == 0) {
                            getRootScreen().getViewAction().swapBars(
                                    i,
                                    getRootScreen().getViewAction().initialY0,
                                    indexMinBar,
                                    getRootScreen().getViewAction().initialY0
                            );
                            animationStep++;
                        } else if (animationStep == 1) {
                            getRootScreen().getViewAction().checkBar(i, Config.COLOR_BAR_TEMP_SORTED);
                            i++;
                            j = i;
                            indexMinBar = i;
                            animationStep = 0;
                        }
                    }

                } else {
                    if (animationStep == 0) {
                        getRootScreen().getViewAction().checkBar(j, Config.COLOR_BAR_CHECKING);
                        animationStep++;
                    } else if (animationStep == 1) {
                        if (bars[j].compareTo(bars[indexMinBar]) < 0) {
                            getRootScreen().getViewAction().checkBar(indexMinBar, Config.COLOR_BAR_PLAIN);
                            animationStep++;
                        } else {
                            getRootScreen().getViewAction().checkBar(j, Config.COLOR_BAR_PLAIN);
                            animationStep = 0;
                            j++;
                        }
                    } else if (animationStep == 2) {
                        getRootScreen().getViewAction().checkBar(j, Config.COLOR_BAR_FLAG);
                        indexMinBar = j;
                        animationStep = 0;
                        j++;
                    }

                }
            }
        }
    }
}
