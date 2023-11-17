package src.components.components.datastructures.queue.arrayqueue.actionanimation;

import src.components.components.datastructures.queue.AbstractQueueAnimation;
import src.components.components.datastructures.queue.AbstractQueueScreen;

public abstract class AbstractArrayQueueAnimation extends AbstractQueueAnimation {
    public AbstractArrayQueueAnimation(
            AbstractQueueScreen rootScreen,
            int period, AbstractQueueAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}