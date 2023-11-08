package src.components.components.datastructures.stack.linkedstack;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.stack.AbstractPanelStackNode;

import java.awt.*;

public class LinkedStackPanelNode extends AbstractPanelStackNode {
    public Panel[] panels;
    public LinkedStackPanelNode(int index, int value) {
        super(
                index, value,
                ViewLinkedStackAction.WIDTH_NODE,
                ViewLinkedStackAction.HEIGHT_NODE
        );
        setBorderWidth(4);
        setBorderColor(Config.COLOR_BAR_TEMP_SORTED);
        addPanels();
        repaint();
    }

    public void addPanels() {
        panels = new Panel[2];
        panels[0] = new Panel(
                10, 10, getWidthPanel() - 20, getWidthPanel() - 20,
                getBackgroundColor(), null, value + "", 0);
        panels[1] = new Panel(
                panels[0].getX(),
                panels[0].getY() + panels[0].getHeightPanel() + 10,
                panels[0].getWidthPanel(), panels[0].getHeightPanel(),
                getBackgroundColor(), null, "Next", 0);

        panels[0].setBorderWidth(2);
        panels[0].setFont(Config.ARIAL_BOLD_16);
        panels[1].setBorderWidth(0);
        panels[1].setFont(Config.ARIAL_BOLD_14);
        panels[1].setBackgroundColor(Config.COLOR_BAR_TEMP_SORTED);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        panels[0].drawOnOtherPanel(g);
        panels[1].drawOnOtherPanel(g);
    }

    @Override
    public void drawOnOtherPanel(Graphics g) {
        super.drawOnOtherPanel(g);
        Graphics2D g2d = (Graphics2D) g.create();
        g2d.translate(x, y);
        panels[0].drawOnOtherPanel(g2d);
        panels[1].drawOnOtherPanel(g2d);
    }


    @Override
    public int createX() {
        return ViewLinkedStackAction.INITIAL_X
                + (ViewLinkedStackAction.GAP_X + ViewLinkedStackAction.WIDTH_NODE) * index;
    }

    @Override
    public int createY() {
        return ViewLinkedStackAction.INITIAL_Y;
    }

    @Override
    public int[] getDefaultPrevArrow() {
        return null;
    }

    @Override
    public int[] getDefaultNextArrow() {
        return new int[] {
                getX() + panels[1].getX() + panels[1].getWidthPanel(),
                getY() + panels[1].getY() + panels[1].getHeightPanel() / 2,
                getX() + getWidthPanel() + ViewLinkedStackAction.GAP_X,
                getY() + getHeightPanel() / 2
        };
    }
}