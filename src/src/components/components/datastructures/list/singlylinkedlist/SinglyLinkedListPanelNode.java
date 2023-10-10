package src.components.components.datastructures.list.singlylinkedlist;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.list.AbstractPanelListNode;

public class SinglyLinkedListPanelNode extends AbstractPanelListNode {
    private Panel[] panels;
    public SinglyLinkedListPanelNode(int index, int value) {
        super(
                index, value,
                ViewSinglyLinkedListAction.WIDTH_NODE,
                ViewSinglyLinkedListAction.HEIGHT_NODE
        );
        setBorderWidth(2);
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
        panels[1].setBorderWidth(2);
        panels[1].setFont(Config.ARIAL_BOLD_14);
        panels[1].setBorderColor(Config.COLOR_BAR_TEMP_SORTED);

        add(panels[0]);
        add(panels[1]);
    }


    @Override
    public int createX() {
        return ViewSinglyLinkedListAction.INITIAL_X
                + (ViewSinglyLinkedListAction.GAP_X + ViewSinglyLinkedListAction.WIDTH_NODE) * index;
    }

    @Override
    public int createY() {
        return ViewSinglyLinkedListAction.INITIAL_Y;
    }
}
