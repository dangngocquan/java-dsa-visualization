package src.components.components.datastructures.queue.arrayqueue.actionanimation;

import src.components.components.datastructures.queue.AbstractQueueAnimation;
import src.components.components.datastructures.queue.AbstractQueueScreen;

public abstract class AbstractArrayQueueAnimation extends AbstractQueueAnimation {
    public AbstractArrayQueueAnimation(
            AbstractQueueScreen rootScreen,
            AbstractQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}