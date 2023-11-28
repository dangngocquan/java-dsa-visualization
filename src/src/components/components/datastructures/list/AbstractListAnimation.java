package src.components.components.datastructures.list;

import src.components.components.datastructures.AbstractDataStructureAnimation;

public abstract class AbstractListAnimation extends AbstractDataStructureAnimation {
    public AbstractListAnimation(
            AbstractListScreen rootScreen,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, rootScreen.getPeriod(), nextAnimation);
    }

}
