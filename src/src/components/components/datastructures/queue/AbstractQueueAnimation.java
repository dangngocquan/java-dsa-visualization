package src.components.components.datastructures.queue;

import src.components.components.datastructures.AbstractDataStructureAnimation;
import src.components.components.datastructures.AbstractDataStructureScreen;

public abstract class AbstractQueueAnimation extends AbstractDataStructureAnimation {
    public AbstractQueueAnimation(
            AbstractDataStructureScreen rootScreen,
            int period,
            AbstractQueueAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
    }
}