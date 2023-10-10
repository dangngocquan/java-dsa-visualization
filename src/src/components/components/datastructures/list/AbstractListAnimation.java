package src.components.components.datastructures.list;

import src.components.components.datastructures.AbstractDataStructureAnimation;

public abstract class AbstractListAnimation extends AbstractDataStructureAnimation {
    public AbstractListAnimation(
            AbstractListScreen rootScreen, int period,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
    }

}
