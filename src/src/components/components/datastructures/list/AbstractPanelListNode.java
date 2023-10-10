package src.components.components.datastructures.list;

import src.Config;
import src.components.base.Panel;

public abstract class AbstractPanelListNode extends Panel implements Comparable<AbstractPanelListNode> {
    protected int index;
    protected int value;
    protected int widthNode;
    protected int heightNode;

    public AbstractPanelListNode(int index, int value, int width, int height) {
        super(0, 0, width, height, Config.COLOR_BAR_PLAIN, null, "", 0);
        this.index = index;
        this.value = value;
        this.widthNode = width;
        this.heightNode = height;
        setXY(createX(), createY());
    }

    public abstract int createX();
    public abstract int createY();

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    @Override
    public int compareTo(AbstractPanelListNode o) {
        return value - o.getValue();
    }
}
