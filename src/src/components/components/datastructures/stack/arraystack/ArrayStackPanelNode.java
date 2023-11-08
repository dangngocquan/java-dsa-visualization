package src.components.components.datastructures.stack.arraystack;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.stack.AbstractPanelStackNode;

import java.awt.*;

public class ArrayStackPanelNode extends AbstractPanelStackNode {
    private Panel panel;
    public ArrayStackPanelNode(int index, int value) {
        super(index, value, ViewArrayStackAction.SIZE_PER_NODE, ViewArrayStackAction.SIZE_PER_NODE);
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
        return ViewArrayStackAction.INITIAL_X
                + ViewArrayStackAction.GAP_X * (index + 1)
                + ViewArrayStackAction.SIZE_PER_NODE * index;
    }

    @Override
    public int createY() {
        return ViewArrayStackAction.INITIAL_Y + 10;
    }

    @Override
    public int[] getDefaultPrevArrow() {
        return null;
    }

    @Override
    public int[] getDefaultNextArrow() {
        return null;
    }
}
