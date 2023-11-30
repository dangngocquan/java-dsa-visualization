package src.components.components.algorithms.search.binary;

import src.Config;
import src.components.components.algorithms.search.AbstractSearchAlgorithmAnimation;
import src.components.components.algorithms.search.AbstractSearchAlgorithmScreen;
import src.components.components.algorithms.search.Bar;

public class BinarySearchAnimation extends AbstractSearchAlgorithmAnimation {
    private int i;
    private int mid;
    private int left;
    private int right;
    private final Bar[] bars;
    private final Bar barSearching;

    public BinarySearchAnimation(AbstractSearchAlgorithmScreen rootScreen, int period) {
        super(rootScreen, period);
        bars = rootScreen.getViewAction().bars;
        left = 0;
        right = bars.length - 1;
        mid = (left + right) / 2;
        barSearching = rootScreen.getViewAction().barSearching;
    }

    @Override
    public void run() {
        if (isRunning()) {
            if (left > right) {
                end(0);
            } else {
                if (animationStep == 0) {
                    mid = (left + right) / 2;
                    getRootScreen().getViewAction().checkBar(mid, Config.COLOR_YELLOW);
                    getRootScreen().getViewAction().moveBarSearching(i, mid);
                    i = mid;
                    animationStep = 1;
                    getRootScreen().descriptionCompare(barSearching.getValue(), i, bars[i].getValue());
                } else if (animationStep == 1) {
                    if (barSearching.getValue() == bars[mid].getValue()) {
                        getRootScreen().getViewAction().checkBar(mid, Config.COLOR_GREEN);
                        end(1);
                    } else {
                        animationStep = 2;
                    }
                } else if (animationStep == 2) {
                    getRootScreen().getViewAction().checkBar(mid, Config.COLOR_BAR_PLAIN);
                    animationStep = 0;
                    if (barSearching.getValue() < bars[mid].getValue()) {
                        right = mid - 1;
                    } else {
                        left = mid + 1;
                    }
                }
            }
        }
    }
}
