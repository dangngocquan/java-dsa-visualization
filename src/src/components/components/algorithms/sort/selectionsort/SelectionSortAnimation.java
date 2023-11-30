package src.components.components.algorithms.sort.selectionsort;

import src.Config;
import src.components.components.algorithms.sort.AbstractSortAlgorithmAnimation;
import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;
import src.components.components.algorithms.sort.Bar;
import src.components.components.algorithms.sort.ViewSortAlgorithmAction;

public class SelectionSortAnimation extends AbstractSortAlgorithmAnimation {
    private int i;
    private int j;
    private int indexMinBar;
    private final Bar[] bars;

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
                    initialMinValue();
                } else if (j >= bars.length) {
                    translateMinValueToCorrectIndex();
                } else {
                    findMinValue();
                }
            }
        }
    }

    public void initialMinValue() {
        indexMinBar = i;
        getRootScreen().setDescription(
                String.format("minElement := a[%d] = %d.", indexMinBar, bars[indexMinBar].getValue()));
        getRootScreen().getViewAction().checkBar(i, Config.COLOR_BAR_FLAG);
        j++;
    }

    public void translateMinValueToCorrectIndex() {
        if (i == indexMinBar) {
            getRootScreen().setDescription(
                    String.format("Correct index: a[%d] = %d.", i, bars[indexMinBar].getValue())
            );
            getRootScreen().getViewAction().checkBar(i, Config.COLOR_BLUE);
            i++;
            j = i;
        } else {
            if (animationStep == 0) {
                getRootScreen().setDescription(
                        String.format("Swap a[%d] = %d and a[%d] = %d.",
                                i, bars[i].getValue(),
                                indexMinBar, bars[indexMinBar].getValue())
                );
                getRootScreen().getViewAction().swapBars(
                        i,
                        ViewSortAlgorithmAction.initialY0,
                        indexMinBar,
                        ViewSortAlgorithmAction.initialY0
                );
                animationStep++;
            } else if (animationStep == 1) {
                getRootScreen().setDescription(
                        String.format("Correct index: a[%d] = %d.", i, bars[i].getValue())
                );
                getRootScreen().getViewAction().checkBar(i, Config.COLOR_BLUE);
                i++;
                j = i;
                indexMinBar = i;
                animationStep = 0;
            }
        }
    }

    public void findMinValue() {
        if (animationStep == 0) {
            getRootScreen().setDescription(
                    String.format("Checking a[%d] = %d.", j, bars[j].getValue())
            );
            getRootScreen().getViewAction().checkBar(j, Config.COLOR_YELLOW);
            animationStep++;
        } else if (animationStep == 1) {
            if (bars[j].compareTo(bars[indexMinBar]) < 0) {
                getRootScreen().setDescription(
                        String.format(
                                "a[%d] = %d is smaller than current min element a[%d] = %d.",
                                j, bars[j].getValue(), indexMinBar, bars[indexMinBar].getValue()
                        )
                );
                getRootScreen().getViewAction().checkBar(indexMinBar, Config.COLOR_WHITE);
                animationStep++;
            } else {
                getRootScreen().setDescription(
                        String.format(
                                "a[%d] = %d is equals or greater than current min element a[%d] = %d.",
                                j, bars[j].getValue(), indexMinBar, bars[indexMinBar].getValue()
                        )
                );
                getRootScreen().getViewAction().checkBar(j, Config.COLOR_WHITE);
                animationStep = 0;
                j++;
            }
        } else if (animationStep == 2) {
            getRootScreen().setDescription(
                    String.format(
                            "Updating current min element: minElement := a[%d] = %d.",
                            j, bars[j].getValue()
                    )
            );
            getRootScreen().getViewAction().checkBar(j, Config.COLOR_RED);
            indexMinBar = j;
            animationStep = 0;
            j++;
        }
    }
}
