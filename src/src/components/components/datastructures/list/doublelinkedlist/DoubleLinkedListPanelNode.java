package src.components.components.datastructures.list.doublelinkedlist;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.list.AbstractPanelListNode;

import java.awt.*;

public class DoubleLinkedListPanelNode extends AbstractPanelListNode {
    public Panel[] panels;
    public DoubleLinkedListPanelNode(int index, int value) {
        super(
                index, value,
                ViewDoubleLinkedListAction.WIDTH_NODE,
                ViewDoubleLinkedListAction.HEIGHT_NODE
        );
        setBorderWidth(4);
        setBorderColor(Config.COLOR_BAR_TEMP_SORTED);
        addPanels();
        repaint();
    }

    public void addPanels() {
        panels = new Panel[3];
        panels[0] = new Panel(
                10,
                10,
                getWidthPanel()-20,
                (getHeightPanel()-40) / 3,
                getBackgroundColor(), null, "Prev", 0);
        panels[1] = new Panel(
                panels[0].getX(),
                panels[0].getY() + panels[0].getHeightPanel() + 10,
                panels[0].getWidthPanel(),
                panels[0].getHeightPanel(),
                getBackgroundColor(), null, value + "", 0);
        panels[2] = new Panel(
                panels[1].getX(),
                panels[1].getY() + panels[1].getHeightPanel() + 10,
                panels[1].getWidthPanel(),
                panels[1].getHeightPanel(),
                getBackgroundColor(), null, "Next", 0);

        panels[0].setBorderWidth(0);
        panels[0].setFont(Config.ARIAL_BOLD_14);
        panels[0].setBackgroundColor(Config.COLOR_BLUE);
        panels[1].setBorderWidth(2);
        panels[1].setFont(Config.ARIAL_BOLD_16);
        panels[2].setBorderWidth(0);
        panels[2].setFont(Config.ARIAL_BOLD_14);
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
        return ViewDoubleLinkedListAction.INITIAL_X
                + (ViewDoubleLinkedListAction.GAP_X + ViewDoubleLinkedListAction.WIDTH_NODE) * index;
    }

    @Override
    public int createY() {
        return ViewDoubleLinkedListAction.INITIAL_Y;
    }

    @Override
    public int[] getDefaultPrevArrow() {
        return new int[] {
                getX() + panels[0].getX(),
                getY() + panels[0].getY() + panels[0].getHeightPanel() / 2,
                getX() - ViewDoubleLinkedListAction.GAP_X,
                getY() + getHeightPanel() / 2
        };
    }

    @Override
    public int[] getDefaultNextArrow() {
        return new int[] {
            getX() + panels[2].getX() + panels[2].getWidthPanel(),
            getY() + panels[2].getY() + panels[2].getHeightPanel() / 2,
            getX() + getWidthPanel() + ViewDoubleLinkedListAction.GAP_X,
            getY() + getHeightPanel() / 2
        };
    }
}
