package src.components.components.algorithms.search.sequential;

import src.Config;
import src.components.components.algorithms.search.AbstractSearchAlgorithmAnimation;
import src.components.components.algorithms.search.AbstractSearchAlgorithmScreen;
import src.components.components.algorithms.search.Bar;

public class SequentialSearchAnimation extends AbstractSearchAlgorithmAnimation {
    private int i;
    private int j;
    private final Bar[] bars;
    private final Bar barSearching;

    public SequentialSearchAnimation(AbstractSearchAlgorithmScreen rootScreen, int period) {
        super(rootScreen, period);
        j = 0;
        bars = rootScreen.getViewAction().bars;
        barSearching = rootScreen.getViewAction().barSearching;
    }

    @Override
    public void run() {
        if (isRunning()) {
            if (j >= bars.length) {
                end(0);
            } else {
                if (animationStep == 0) {
                    getRootScreen().getViewAction().checkBar(j, Config.COLOR_YELLOW);
                    getRootScreen().getViewAction().moveBarSearching(i, j);
                    i = j;
                    animationStep = 1;
                    getRootScreen().descriptionCompare(barSearching.getValue(), i, bars[i].getValue());
                } else if (animationStep == 1) {
                    if (barSearching.getValue() == bars[j].getValue()) {
                        getRootScreen().getViewAction().checkBar(j , Config.COLOR_GREEN);
                        end(1);
                    } else {
                        animationStep = 2;
                    }
                } else if (animationStep == 2) {
                    getRootScreen().getViewAction().checkBar(j, Config.COLOR_BAR_PLAIN);
                    animationStep = 0;
                    j++;
                }
            }
        }
    }
}
