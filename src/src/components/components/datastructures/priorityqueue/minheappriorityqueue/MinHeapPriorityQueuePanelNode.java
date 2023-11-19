package src.components.components.datastructures.priorityqueue.minheappriorityqueue;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;

import java.awt.*;

public class MinHeapPriorityQueuePanelNode extends AbstractPanelPriorityQueueNode {
    private Panel panelKey;
    private Panel panelValue;
    private final int indexColumn;
    private final int indexRow;
    public MinHeapPriorityQueuePanelNode(int indexRow, int indexColumn, int key, int value) {
        super(
                0, key, value,
                ViewMinHeapPriorityQueueAction.SIZE_PER_NODE,
                ViewMinHeapPriorityQueueAction.SIZE_PER_NODE
        );
        this.indexColumn = indexColumn;
        this.indexRow = indexRow;
        addPanel();
        setXY(createX(), createY());
        repaint();
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        super.setBackgroundColor(backgroundColor);
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
                value + "", 1 + 2 - 3
        );
        panelValue.setBorderWidth(1);
        panelValue.setFont(Config.MONOSPACED_BOLD_18);

        add(panelKey);
        add(panelValue);
    }

    @Override
    public int createX() {
        return ViewMinHeapPriorityQueueAction.INITIAL_X
                + ViewMinHeapPriorityQueueAction.GAP_X * (indexColumn + 1)
                + ViewMinHeapPriorityQueueAction.SIZE_PER_NODE * indexColumn;
    }

    @Override
    public int createY() {
        return ViewMinHeapPriorityQueueAction.INITIAL_Y
                + ViewMinHeapPriorityQueueAction.GAP_Y * (indexRow + 1)
                + ViewMinHeapPriorityQueueAction.SIZE_PER_NODE * indexRow;
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