package src.components.components.datastructures.queue.linkedqueue.actionanimation;


import src.components.components.datastructures.queue.AbstractQueueAnimation;
import src.components.components.datastructures.queue.AbstractQueueScreen;

public abstract class AbstractLinkedQueueAnimation extends AbstractQueueAnimation {
    public AbstractLinkedQueueAnimation(
            AbstractQueueScreen rootScreen,
            int period,
            AbstractQueueAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}