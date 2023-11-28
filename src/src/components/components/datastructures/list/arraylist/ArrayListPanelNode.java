package src.components.components.datastructures.list.arraylist;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.list.AbstractPanelListNode;

import java.awt.*;

public class ArrayListPanelNode extends AbstractPanelListNode implements Comparable<ArrayListPanelNode> {
    private Panel panel;
    public ArrayListPanelNode(int index, int value) {
        super(index, value, ViewArrayListAction.SIZE_PER_NODE, ViewArrayListAction.SIZE_PER_NODE);
        addPanel();
        repaint();
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        panel.setBackgroundColor(backgroundColor);
    }

    public void addPanel() {
        panel = new Panel(
                0, 0, getWidthPanel(), getHeightPanel(),
                getBackgroundColor(), null,
                value + "", 0
        );
        panel.setBorderWidth(1);
        panel.setFont(Config.MONOSPACED_BOLD_18);
        add(panel);
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

    @Override
    public int[] getDefaultPrevArrow() {
        return null;
    }

    @Override
    public int[] getDefaultNextArrow() {
        return null;
    }

    @Override
    public int compareTo(ArrayListPanelNode o) {
        return value - o.value;
    }
}
