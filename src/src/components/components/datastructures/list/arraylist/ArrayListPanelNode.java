package src.components.components.datastructures.list.arraylist;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.list.abstractlistscreen.AbstractPanelListNode;

import java.awt.*;

public class ArrayListPanelNode extends AbstractPanelListNode {
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
        panel.setFont(Config.ARIAL_BOLD_18);
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
}
