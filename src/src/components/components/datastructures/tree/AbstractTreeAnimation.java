package src.components.components.datastructures.tree;

import src.components.components.datastructures.AbstractDataStructureAnimation;
import src.components.components.datastructures.AbstractDataStructureScreen;

public abstract class AbstractTreeAnimation extends AbstractDataStructureAnimation {
    public AbstractTreeAnimation(
            AbstractDataStructureScreen rootScreen,
            int period,
            AbstractTreeAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
    }
}
