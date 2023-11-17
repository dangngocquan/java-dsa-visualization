package src.components.components.datastructures.queue;

import src.components.components.datastructures.AbstractPanelDataStructureNode;

public abstract class AbstractPanelQueueNode extends AbstractPanelDataStructureNode {
    public AbstractPanelQueueNode(int index, int value, int width, int height) {
        super(index, value, width, height);
    }

    @Override
    public int createX() {
        return 0;
    }

    @Override
    public int createY() {
        return 0;
    }

    @Override
    public int[] getDefaultPrevArrow() {
        return new int[0];
    }

    @Override
    public int[] getDefaultNextArrow() {
        return new int[0];
    }
}