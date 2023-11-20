package src.components.components.datastructures.tree;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.AbstractPanelDataStructureNode;

import java.awt.*;

public class TreePanelNode
        extends AbstractPanelDataStructureNode
        implements Comparable<TreePanelNode> {
    public int indexRow;
    public int indexColumn;
    private Panel panel;
    public TreePanelNode(int indexRow, int indexColumn, int value) {
        super(
                0, value,
                AbstractViewTreeAction.SIZE_PER_NODE,
                AbstractViewTreeAction.SIZE_PER_NODE
        );
        this.indexRow = indexRow;
        this.indexColumn = indexColumn;
        addPanel();
        setXY(createX(), createY());
        repaint();
    }

    @Override
    public void setBackgroundColor(Color backgroundColor) {
        panel.setBackgroundColor(backgroundColor);
    }

    public void addPanel() {
        removeAll();
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
        return AbstractViewTreeAction.INITIAL_X
                + AbstractViewTreeAction.GAP_X * (indexColumn + 1)
                + AbstractViewTreeAction.SIZE_PER_NODE * indexColumn;
    }

    @Override
    public int createY() {
        return AbstractViewTreeAction.INITIAL_Y
                + AbstractViewTreeAction.GAP_Y * (indexRow + 1)
                + AbstractViewTreeAction.SIZE_PER_NODE * indexRow;
    }

    @Override
    public int[] getDefaultPrevArrow() {
        return new int[0];
    }

    @Override
    public int[] getDefaultNextArrow() {
        return new int[0];
    }

    @Override
    public int compareTo(TreePanelNode panel) {
        return this.value - panel.value;
    }
}
