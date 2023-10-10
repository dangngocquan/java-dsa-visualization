package src.components.components.algorithms.sort.quicksort;

import src.Config;
import src.components.components.algorithms.sort.AbstractSortAlgorithmAnimation;
import src.components.components.algorithms.sort.AbstractSortAlgorithmScreen;
import src.components.components.algorithms.sort.Bar;

import java.util.Stack;

public class QuickSortAnimation extends AbstractSortAlgorithmAnimation {
    private Stack<int[]> ranges;
    private int l; // left/low index
    private int r; // right/high index
    private int pivotIndex;
    private int i;
    private Bar[] bars;

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
        getRootScreen().getViewAction().pickUpBar(
                i,
                getRootScreen().getViewAction().initialY0
        );
        getRootScreen().getViewAction().checkBar(
                i,
                Config.COLOR_BAR_FLAG
        );
        i++;
    }

    public void solveWhenInRange() {
        if (animationStep == 0) {
            getRootScreen().getViewAction().checkBar(
                    i,
                    Config.COLOR_BAR_CHECKING
            );
            animationStep++;
        } else if (animationStep == 1) {
            if (bars[i].compareTo(bars[l]) < 0) {
                pivotIndex++;
                getRootScreen().getViewAction().swapBars(
                        pivotIndex,
                        getRootScreen().getViewAction().initialY0,
                        i,
                        getRootScreen().getViewAction().initialY0
                );
                animationStep++;
            } else {
                getRootScreen().getViewAction().checkBar(
                        i,
                        Config.COLOR_BAR_PLAIN
                );
                animationStep = 0;
                i++;
            }
        } else if (animationStep == 2) {
            getRootScreen().getViewAction().checkBar(
                    pivotIndex,
                    Config.COLOR_BAR_SMALLER_PIVOT
            );
            animationStep = 0;
            i++;
        }
    }

    public void solveWhenFinishARange() {
        if (pivotIndex == l) {
            getRootScreen().getViewAction().pickDownBar(
                    l,
                    getRootScreen().getViewAction().initialY1
            );
            getRootScreen().getViewAction().checkBar(
                    l,
                    Config.COLOR_BAR_TEMP_SORTED
            );
            if (pivotIndex + 1 == r) {
                getRootScreen().getViewAction().checkBar(
                        r,
                        Config.COLOR_BAR_TEMP_SORTED
                );
            } else if (pivotIndex + 1 < r) {
                ranges.add(new int[] {pivotIndex + 1, r});
            }
            if (ranges.isEmpty()) {
                end();
            } else {
                int[] range = ranges.pop();
                l = range[0];
                r = range[1];
                i = l;
                pivotIndex = l;
            }
        } else {
            if (animationStep == 0) {
                getRootScreen().getViewAction().pickUpBar(
                        pivotIndex,
                        getRootScreen().getViewAction().initialY0
                );
                animationStep++;
            } else if (animationStep == 1) {
                getRootScreen().getViewAction().swapBars(
                        l,
                        getRootScreen().getViewAction().initialY1,
                        pivotIndex,
                        getRootScreen().getViewAction().initialY1
                );
                animationStep++;
            } else if (animationStep == 2) {
                getRootScreen().getViewAction().pickDownBar(
                        l,
                        getRootScreen().getViewAction().initialY1
                );
                getRootScreen().getViewAction().pickDownBar(
                        pivotIndex,
                        getRootScreen().getViewAction().initialY1
                );
                getRootScreen().getViewAction().checkBar(
                        pivotIndex,
                        Config.COLOR_BAR_TEMP_SORTED
                );

                if (pivotIndex + 1 == r) {
                    getRootScreen().getViewAction().checkBar(
                            r,
                            Config.COLOR_BAR_TEMP_SORTED
                    );
                } else if (pivotIndex + 1 < r) {
                    ranges.add(new int[] {pivotIndex + 1, r});
                }

                if (l == pivotIndex - 1) {
                    getRootScreen().getViewAction().checkBar(
                            l,
                            Config.COLOR_BAR_TEMP_SORTED
                    );
                } else if (l < pivotIndex - 1) {
                    ranges.add(new int[] {l, pivotIndex - 1});
                }

                animationStep++;
            } else if (animationStep == 3) {
                if (l < pivotIndex-1) {
                    getRootScreen().getViewAction().setColorAndLocationBars(
                            l, pivotIndex-1,
                            Config.COLOR_BAR_PLAIN,
                            getRootScreen().getViewAction().initialY0
                    );
                }

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
