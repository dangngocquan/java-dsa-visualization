package src.components.components.datastructures.list.abstractlistscreen;

import src.components.components.datastructures.AbstractDataStructureAnimation;

import java.util.Timer;

public abstract class AbstractListAnimation extends AbstractDataStructureAnimation {
    public AbstractListAnimation(
            AbstractListScreen rootScreen, int period,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
    }

}
