package src.components.components.datastructures.stack.linkedstack.actionanimation;

import src.components.components.datastructures.stack.AbstractStackAnimation;
import src.components.components.datastructures.stack.AbstractStackScreen;

public abstract class AbstractLinkedStackAnimation extends AbstractStackAnimation {
    public AbstractLinkedStackAnimation(
            AbstractStackScreen rootScreen,
            AbstractStackAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
