package src.components.components.datastructures.list.arraylist.actionanimation;

import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;

public abstract class AbstractArrayListAnimation extends AbstractListAnimation {
    public AbstractArrayListAnimation(
            AbstractListScreen rootScreen,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
