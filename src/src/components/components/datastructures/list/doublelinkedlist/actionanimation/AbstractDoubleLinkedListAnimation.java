package src.components.components.datastructures.list.doublelinkedlist.actionanimation;

import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;

public abstract class AbstractDoubleLinkedListAnimation extends AbstractListAnimation {
    public AbstractDoubleLinkedListAnimation(
            AbstractListScreen rootScreen,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
