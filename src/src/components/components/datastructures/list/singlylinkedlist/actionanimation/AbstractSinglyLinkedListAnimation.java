package src.components.components.datastructures.list.singlylinkedlist.actionanimation;

import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;

public abstract class AbstractSinglyLinkedListAnimation extends AbstractListAnimation {
    public AbstractSinglyLinkedListAnimation(
            AbstractListScreen rootScreen,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
