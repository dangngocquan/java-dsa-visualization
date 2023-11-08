package src.components.components.datastructures.stack;

import src.components.components.datastructures.AbstractPanelDataStructureNode;

public abstract class AbstractPanelStackNode extends AbstractPanelDataStructureNode {
    public AbstractPanelStackNode(int index, int value, int width, int height) {
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
