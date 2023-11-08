package src.components.components.datastructures.stack.linkedstack.actionanimation;

import src.components.components.datastructures.stack.AbstractStackAnimation;
import src.components.components.datastructures.stack.AbstractStackScreen;

public abstract class AbstractLinkedStackAnimation extends AbstractStackAnimation {
    public AbstractLinkedStackAnimation(
            AbstractStackScreen rootScreen,
            int period,
            AbstractStackAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
