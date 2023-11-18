package src.components.components.datastructures.priorityqueue;

import src.components.components.datastructures.AbstractDataStructureAnimation;
import src.components.components.datastructures.AbstractDataStructureScreen;

public abstract class AbstractPriorityQueueAnimation extends AbstractDataStructureAnimation {
    public AbstractPriorityQueueAnimation(
            AbstractDataStructureScreen rootScreen,
            int period,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
    }
}