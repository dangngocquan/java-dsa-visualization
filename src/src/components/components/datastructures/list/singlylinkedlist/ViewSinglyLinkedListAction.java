package src.components.components.datastructures.list.singlylinkedlist;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.AbstractPanelListNode;
import src.components.components.datastructures.list.AbstractViewListAction;
import src.components.components.datastructures.list.singlylinkedlist.actionanimation.SinglyLinkedListActionAdd1;
import src.models.datastructures.list.MySinglyLinkedList;
import src.services.ServiceAnimation;
import src.services.ServiceComponent;

import javax.swing.plaf.basic.BasicGraphicsUtils;
import java.awt.*;
import java.util.Iterator;

public class ViewSinglyLinkedListAction extends AbstractViewListAction {
    public static final int INITIAL_X = 100;
    public static final int INITIAL_Y = 50;
    public static final int WIDTH_NODE = (Config.WIDTH - INITIAL_X * 2) / 15;
    public static final int HEIGHT_NODE = (WIDTH_NODE - 20) * 2 + 30;
    public static final int GAP_X = WIDTH_NODE;
    public static final int GAP_Y = 200;

    public ViewSinglyLinkedListAction(AbstractListScreen rootScreen) {
        super(rootScreen);
        drawElements();
        repaint();
    }

    public SinglyLinkedListScreen getRootScreen() {
        return (SinglyLinkedListScreen) rootScreen;
    }

    public void drawElements() {
        for (int i = 0; i < getRootScreen().list.size(); i++) {
            AbstractPanelDataStructureNode panelNode = getRootScreen().list.get(i);
            panelNode.setVisible(false);
            add(panelNode);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        Iterator<AbstractPanelDataStructureNode> iterator = getRootScreen().list.iterator();
        SinglyLinkedListPanelNode prevNode = null;
        SinglyLinkedListPanelNode node = null;
        while (iterator.hasNext()) {
            prevNode = node;
            node = (SinglyLinkedListPanelNode) iterator.next();
            node.drawOnOtherPanel(g);
            if (prevNode != null && node != null) {
                int x1 = prevNode.getX() + prevNode.panels[1].getX() + prevNode.panels[1].getWidthPanel();
                int y1 = prevNode.getY() + prevNode.panels[1].getY() + prevNode.panels[1].getHeightPanel() / 2;
                int x2 = node.getX();
                int y2 = node.getY() + node.getHeightPanel() / 2;
                ServiceComponent.drawPatternArrow1(g, x1, y1, x2, y2, prevNode.panels[1].getBackgroundColor());
            }
        }
    }

    @Override
    public void actionAdd(int value) {
        new SinglyLinkedListActionAdd1(
                value, getRootScreen(), 2000, null
        ).start();
    }

    @Override
    public void actionAdd(int index, int value) {

    }

    @Override
    public void actionGet(int index) {

    }

    @Override
    public void actionRemove(int index) {

    }

    @Override
    public void actionRemove(Integer value) {

    }
}
