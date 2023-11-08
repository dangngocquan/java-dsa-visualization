package src.components.components.datastructures.stack;

import src.components.components.datastructures.AbstractDataStructureAnimation;
import src.components.components.datastructures.AbstractDataStructureScreen;

public abstract class AbstractStackAnimation extends AbstractDataStructureAnimation {
    public AbstractStackAnimation(
            AbstractDataStructureScreen rootScreen,
            int period,
            AbstractStackAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
    }
}
