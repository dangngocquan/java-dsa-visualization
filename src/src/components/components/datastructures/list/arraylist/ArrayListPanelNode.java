package src.components.components.datastructures.list.arraylist;

import src.components.components.datastructures.list.abstractlistscreen.AbstractPanelListNode;

public class ArrayListPanelNode extends AbstractPanelListNode {
    public ArrayListPanelNode(int index, int value) {
        super(index, value, ViewArrayListAction.SIZE_PER_NODE, ViewArrayListAction.SIZE_PER_NODE);
    }

    @Override
    public int createX() {
        return ViewArrayListAction.INITIAL_X
                + ViewArrayListAction.GAP_X * (index + 1)
                + ViewArrayListAction.SIZE_PER_NODE * index;
    }

    @Override
    public int createY() {
        return ViewArrayListAction.INITIAL_Y + 10;
    }
}
