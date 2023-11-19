package src.components.components.datastructures.priorityqueue.unsortedlinkedpriorityqueue;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;

import java.awt.*;

public class UnsortedLinkedPriorityQueuePanelNode extends AbstractPanelPriorityQueueNode {
    public Panel[] panels;
    public UnsortedLinkedPriorityQueuePanelNode(int index, int key, int value) {
        super(
                index, key, value,
                ViewUnsortedLinkedPriorityQueueAction.WIDTH_NODE,
                ViewUnsortedLinkedPriorityQueueAction.HEIGHT_NODE
        );
        setBorderWidth(4);
        setBorderColor(Config.COLOR_BAR_TEMP_SORTED);
        addPanels();
        repaint();
    }

    public void addPanels() {
        panels = new Panel[3];
        panels[0] = new Panel(
                10, 10, getWidthPanel() - 20, (getWidthPanel() - 20) / 2,
                getBackgroundColor(), null, key + "", 0);
        panels[1] = new Panel(
                10, panels[0].getY() + panels[0].getHeightPanel(),
                getWidthPanel() - 20, (getWidthPanel() - 20) / 2,
                getBackgroundColor(), null, value + "", 0);
        panels[2] = new Panel(
                panels[1].getX(),
                panels[1].getY() + panels[1].getHeightPanel() + 10,
                panels[1].getWidthPanel(), panels[1].getWidthPanel(),
                getBackgroundColor(), null, "Next", 0);

        panels[0].setBorderWidth(2);
        panels[0].setFont(Config.MONOSPACED_BOLD_16);
        panels[1].setBorderWidth(2);
        panels[1].setFont(Config.MONOSPACED_BOLD_16);
        panels[2].setBorderWidth(0);
        panels[2].setFont(Config.MONOSPACED_BOLD_14);
        panels[2].setBackgroundColor(Config.COLOR_BLUE);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        panels[0].drawOnOtherPanel(g);
        panels[1].drawOnOtherPanel(g);
        panels[2].drawOnOtherPanel(g);
    }

    @Override
    public void drawOnOtherPanel(Graphics g) {
        super.drawOnOtherPanel(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(x, y);
        panels[0].drawOnOtherPanel(g2d);
        panels[1].drawOnOtherPanel(g2d);
        panels[2].drawOnOtherPanel(g2d);
    }


    @Override
    public int createX() {
        return ViewUnsortedLinkedPriorityQueueAction.INITIAL_X
                + (ViewUnsortedLinkedPriorityQueueAction.GAP_X + ViewUnsortedLinkedPriorityQueueAction.WIDTH_NODE) * index;
    }

    @Override
    public int createY() {
        return ViewUnsortedLinkedPriorityQueueAction.INITIAL_Y;
    }

    @Override
    public int[] getDefaultPrevArrow() {
        return null;
    }

    @Override
    public int[] getDefaultNextArrow() {
        return new int[] {
                getX() + panels[2].getX() + panels[2].getWidthPanel(),
                getY() + panels[2].getY() + panels[2].getHeightPanel() / 2,
                getX() + getWidthPanel() + ViewUnsortedLinkedPriorityQueueAction.GAP_X,
                getY() + getHeightPanel() / 2
        };
    }
}