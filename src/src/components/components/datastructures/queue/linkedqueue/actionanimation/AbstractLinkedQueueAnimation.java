package src.components.components.datastructures.queue.linkedqueue.actionanimation;


import src.components.components.datastructures.queue.AbstractQueueAnimation;
import src.components.components.datastructures.queue.AbstractQueueScreen;

public abstract class AbstractLinkedQueueAnimation extends AbstractQueueAnimation {
    public AbstractLinkedQueueAnimation(
            AbstractQueueScreen rootScreen,
            AbstractQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    @Override
    public String toString() {
        return this.getClass().getSimpleName();
    }
}