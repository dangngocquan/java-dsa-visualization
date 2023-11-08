package src.components.components.datastructures.stack.arraystack.actionanimation;

import src.components.components.datastructures.stack.AbstractStackAnimation;
import src.components.components.datastructures.stack.AbstractStackScreen;

public abstract class AbstractArrayStackAnimation extends AbstractStackAnimation {
    public AbstractArrayStackAnimation(
            AbstractStackScreen rootScreen,
            int period, AbstractStackAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}