package src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.queue.arrayqueue.ViewArrayQueueAction;

import java.awt.*;

public class SortedArrayPriorityQueuePanelNode extends AbstractPanelPriorityQueueNode {
    private Panel panelKey;
    private Panel panelValue;
    public SortedArrayPriorityQueuePanelNode(int index, int key, int value) {
        super(
                index, key, value,
                ViewArrayQueueAction.SIZE_PER_NODE,
                ViewArrayQueueAction.SIZE_PER_NODE
        );
        addPanel();
        repaint();
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        panelKey.setBackgroundColor(backgroundColor);
        panelValue.setBackgroundColor(backgroundColor);
    }

    public void addPanel() {
        panelKey = new Panel(
                0, 0, getWidthPanel(), getHeightPanel()/3,
                getBackgroundColor(), null,
                key + "", 0
        );
        panelKey.setBorderWidth(1);
        panelKey.setFont(Config.MONOSPACED_BOLD_18);

        panelValue = new Panel(
                0, getHeightPanel()/3,
                getWidthPanel(), getHeightPanel() - getHeightPanel()/3,
                getBackgroundColor(), null,
                value + "", 0
        );
        panelValue.setBorderWidth(1);
        panelValue.setFont(Config.MONOSPACED_BOLD_18);

        add(panelKey);
        add(panelValue);
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