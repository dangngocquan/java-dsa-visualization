package src.components.components.algorithms.sort.quicksort;

import src.Config;
import src.components.components.algorithms.sort.AbstractSortAlgorithmAnimation;
import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;
import src.components.components.algorithms.sort.Bar;
import src.components.components.algorithms.sort.ViewSortAlgorithmAction;

import java.util.Stack;

public class QuickSortAnimation extends AbstractSortAlgorithmAnimation {
    private final Stack<int[]> ranges;
    private int l; // left/low index
    private int r; // right/high index
    private int pivotIndex;
    private int i;
    private final Bar[] bars;
    private int subAnimationStep = 0;

    public QuickSortAnimation(AbstractSortAlgorithmScreen rootScreen, int period) {
        super(rootScreen, period);
        bars = rootScreen.getViewAction().bars;
        ranges = new Stack<>();
        l = 0;
        r = bars.length - 1;
        i = l;
        pivotIndex = l;
    }

    @Override
    public void run() {
        if (isRunning()) {
            if (i == l) {
                solveWhenStartARange();
            } else if (i > r) {
                solveWhenFinishARange();
            } else {
                solveWhenInRange();
            }
        }
    }

    public void solveWhenStartARange() {
        getRootScreen().setDescription(
                String.format("Sort subarray [%d, %d]. Pivot value a[%d] = %d.",
                        l, r, l, bars[l].getValue())
        );
        getRootScreen().getViewAction().pickUpBar(
                i,
                ViewSortAlgorithmAction.initialY0
        );
        getRootScreen().getViewAction().checkBar(
                i,
                Config.COLOR_RED
        );
        i++;
    }

    public void solveWhenInRange() {
        if (animationStep == 0) {
            getRootScreen().setDescription(String.format("Checking a[%d] = %d.", i, bars[i].getValue()));
            getRootScreen().getViewAction().checkBar(
                    i,
                    Config.COLOR_YELLOW
            );
            animationStep++;
        } else if (animationStep == 1) {
            if (bars[i].compareTo(bars[l]) < 0) {
                pivotIndex++;
                getRootScreen().setDescription(
                        String.format(
                                "a[%d] = %d is smaller than pivot a[%d] = %d. Swap a[%d] and a[%d].",
                                i, bars[i].getValue(), l, bars[l].getValue(), i, pivotIndex
                        )
                );
                getRootScreen().getViewAction().swapBars(
                        pivotIndex,
                        ViewSortAlgorithmAction.initialY0,
                        i,
                        ViewSortAlgorithmAction.initialY0
                );
                animationStep++;
            } else {
                getRootScreen().setDescription(String.format(
                        "a[%d] = %d is equals or greater than pivot a[%d] = %d.",
                        i, bars[i].getValue(), l, bars[l].getValue()
                ));
                getRootScreen().getViewAction().checkBar(
                        i,
                        Config.COLOR_WHITE
                );
                animationStep = 0;
                i++;
            }
        } else if (animationStep == 2) {
            getRootScreen().getViewAction().checkBar(
                    pivotIndex,
                    Config.COLOR_PINK
            );
            animationStep = 0;
            i++;
        }
    }

    public void solveWhenFinishARange() {
        if (pivotIndex == l) {
            if (subAnimationStep == 0) {
                getRootScreen().setDescription("Pivot value is corrected index.");
                getRootScreen().getViewAction().pickDownBar(
                        l,
                        ViewSortAlgorithmAction.initialY1
                );
                getRootScreen().getViewAction().checkBar(
                        l,
                        Config.COLOR_BLUE
                );
                if (pivotIndex + 1 == r) {
                    getRootScreen().getViewAction().checkBar(
                            r,
                            Config.COLOR_BLUE
                    );
                } else if (pivotIndex + 1 < r) {
                    ranges.add(new int[] {pivotIndex + 1, r});
                }
                subAnimationStep = 1;
            } else if (subAnimationStep == 1) {
                subAnimationStep = 0;
                if (ranges.isEmpty()) {
                    end();
                } else {
                    int[] range = ranges.pop();
                    l = range[0];
                    r = range[1];
                    i = l;
                    pivotIndex = l;
                }
            }
        } else {
            if (animationStep == 0) {
                getRootScreen().setDescription(
                        String.format(
                                "Swap pivot value (%d) and a[%d] = %d.",
                                bars[l].getValue(), pivotIndex, bars[pivotIndex].getValue()
                        )
                );
                getRootScreen().getViewAction().pickUpBar(
                        pivotIndex,
                        ViewSortAlgorithmAction.initialY0
                );
                animationStep++;
            } else if (animationStep == 1) {
                getRootScreen().getViewAction().swapBars(
                        l,
                        ViewSortAlgorithmAction.initialY1,
                        pivotIndex,
                        ViewSortAlgorithmAction.initialY1
                );
                animationStep++;
            } else if (animationStep == 2) {
                getRootScreen().getViewAction().pickDownBar(
                        l,
                        ViewSortAlgorithmAction.initialY1
                );
                getRootScreen().getViewAction().pickDownBar(
                        pivotIndex,
                        ViewSortAlgorithmAction.initialY1
                );
                getRootScreen().getViewAction().checkBar(
                        pivotIndex,
                        Config.COLOR_BLUE
                );

                if (pivotIndex + 1 == r) {
                    getRootScreen().getViewAction().checkBar(
                            r,
                            Config.COLOR_BLUE
                    );
                } else if (pivotIndex + 1 < r) {
                    ranges.add(new int[] {pivotIndex + 1, r});
                }

                if (l == pivotIndex - 1) {
                    getRootScreen().getViewAction().checkBar(
                            l,
                            Config.COLOR_BLUE
                    );
                } else if (l < pivotIndex - 1) {
                    ranges.add(new int[] {l, pivotIndex - 1});
                }

                animationStep++;
            } else if (animationStep == 3) {
                if (subAnimationStep == 0) {
                    if (l < pivotIndex-1) {
                        getRootScreen().getViewAction().setColorAndLocationBars(
                                l, pivotIndex-1,
                                Config.COLOR_WHITE,
                                ViewSortAlgorithmAction.initialY0
                        );
                    }
                } else if (subAnimationStep == 1) {
                    subAnimationStep = 0;
                    if (ranges.isEmpty()) {
                        end();
                    } else {
                        int[] range = ranges.pop();
                        l = range[0];
                        r = range[1];
                        i = l;
                        pivotIndex = l;
                    }
                    animationStep = 0;
                }



            }
        }
    }
}
