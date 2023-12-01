package src.components.components.datastructures.list.singlylinkedlist;

import src.Config;
import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.AbstractPanelListNode;
import src.components.components.datastructures.list.AbstractViewListAction;
import src.components.components.datastructures.list.singlylinkedlist.actionanimation.*;
import src.services.ServiceComponent;

import java.awt.*;
import java.util.Iterator;

public class ViewSinglyLinkedListAction extends AbstractViewListAction {
    public static final int INITIAL_X = 100;
    public static final int INITIAL_Y = 50;
    public static final int WIDTH_NODE = (Config.WIDTH - INITIAL_X * 2) / (2 * Config.MAX_NODE_TYPE_2 - 1);
    public static final int HEIGHT_NODE = (WIDTH_NODE - 20) * 2 + 30;
    public static final int GAP_X = WIDTH_NODE;
    public static final int GAP_Y = 150;

    public ViewSinglyLinkedListAction(AbstractListScreen rootScreen) {
        super(rootScreen);
        drawElements();
        addDescriptionPanel();
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

        Iterator<AbstractPanelListNode> iterator = getRootScreen().list.iterator();
        SinglyLinkedListPanelNode prevNode;
        SinglyLinkedListPanelNode node = null;
        while (iterator.hasNext()) {
            prevNode = node;
            node = (SinglyLinkedListPanelNode) iterator.next();
            node.drawOnOtherPanel(g);
            if (prevNode != null && prevNode.nextArrow != null) {
                ServiceComponent.drawPatternArrow1(
                        g,
                        prevNode.nextArrow[0],
                        prevNode.nextArrow[1],
                        prevNode.nextArrow[2],
                        prevNode.nextArrow[3],
                        prevNode.panels[1].getBackgroundColor());
            }
        }
    }

    @Override
    public void actionAdd(int value) {
        new SinglyLinkedListActionAdd1(
                value, getRootScreen(), null
        ).start();
    }

    @Override
    public void actionAdd(int index, int value) {
        new SinglyLinkedListActionAdd2(
                index, value, getRootScreen(), null
        ).start();
    }

    @Override
    public void actionGet(int index) {
        new SinglyLinkedListActionGet(
                index, getRootScreen(), null
        ).start();
    }

    @Override
    public void actionRemove(int index) {
        new SinglyLinkedListActionRemove1(
                index, getRootScreen(), null
        ).start();
    }

    @Override
    public void actionRemove(Integer value) {
        new SinglyLinkedListActionRemove2(
                value, getRootScreen(), null
        ).start();
    }
}
