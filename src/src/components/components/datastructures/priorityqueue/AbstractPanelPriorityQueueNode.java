package src.components.components.datastructures.priorityqueue;

import src.components.components.datastructures.AbstractPanelDataStructureNode;

public abstract class AbstractPanelPriorityQueueNode extends AbstractPanelDataStructureNode {
    public int key;
    public AbstractPanelPriorityQueueNode(int index, int key, int value, int width, int height) {
        super(index, value, width, height);
        this.key = key;
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
