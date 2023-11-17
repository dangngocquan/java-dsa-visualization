package src.components.components.datastructures.queue.arrayqueue;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.queue.AbstractPanelQueueNode;

import java.awt.*;

public class ArrayQueuePanelNode extends AbstractPanelQueueNode {
    private Panel panel;
    public ArrayQueuePanelNode(int index, int value) {
        super(index, value, ViewArrayQueueAction.SIZE_PER_NODE, ViewArrayQueueAction.SIZE_PER_NODE);
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
        return ViewArrayQueueAction.INITIAL_X
                + ViewArrayQueueAction.GAP_X * (index + 1)
                + ViewArrayQueueAction.SIZE_PER_NODE * index;
    }

    @Override
    public int createY() {
        return ViewArrayQueueAction.INITIAL_Y + 10;
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