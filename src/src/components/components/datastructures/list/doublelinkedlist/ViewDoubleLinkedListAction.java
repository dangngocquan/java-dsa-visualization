package src.components.components.datastructures.list.doublelinkedlist;

import src.Config;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.AbstractPanelListNode;
import src.components.components.datastructures.list.AbstractViewListAction;
import src.components.components.datastructures.list.doublelinkedlist.actionanimation.*;
import src.services.ServiceComponent;

import java.awt.*;
import java.util.Iterator;

public class ViewDoubleLinkedListAction extends AbstractViewListAction {
    public static final int INITIAL_X = 100;
    public static final int INITIAL_Y = 50;
    public static final int WIDTH_NODE = (Config.WIDTH - INITIAL_X * 2) / (2 * Config.MAX_NODE_TYPE_2 - 1);
    public static final int HEIGHT_NODE = (WIDTH_NODE - 20) * 3 + 40;
    public static final int GAP_X = WIDTH_NODE;
    public static final int GAP_Y = 60;

    public ViewDoubleLinkedListAction(AbstractListScreen rootScreen) {
        super(rootScreen);
        drawElements();
        addDescriptionPanel();
        repaint();
    }

    public DoubleLinkedListScreen getRootScreen() {
        return (DoubleLinkedListScreen) rootScreen;
    }

    public void drawElements() {
        for (int i = 0; i < getRootScreen().list.size(); i++) {
            AbstractPanelListNode panelNode = getRootScreen().list.get(i);
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
        DoubleLinkedListPanelNode prevNode;
        DoubleLinkedListPanelNode node = null;
        while (iterator.hasNext()) {
            prevNode = node;
            node = (DoubleLinkedListPanelNode) iterator.next();
            node.drawOnOtherPanel(g);
            if (prevNode != null && prevNode.nextArrow != null) {
                ServiceComponent.drawPatternArrow1(
                        g,
                        prevNode.nextArrow[0],
                        prevNode.nextArrow[1],
                        prevNode.nextArrow[2],
                        prevNode.nextArrow[3],
                        prevNode.panels[2].getBackgroundColor());
            }
            if (node.prevArrow != null) {
                ServiceComponent.drawPatternArrow1(
                        g,
                        node.prevArrow[0],
                        node.prevArrow[1],
                        node.prevArrow[2],
                        node.prevArrow[3],
                        node.panels[0].getBackgroundColor());
            }
        }
    }

    @Override
    public void actionAdd(int value) {
        new DoubleLinkedListActionAdd1(
                value, getRootScreen(), null
        ).start();
    }

    @Override
    public void actionAdd(int index, int value) {
        new DoubleLinkedListActionAdd2(
                index, value, getRootScreen(), null
        ).start();
    }

    @Override
    public void actionGet(int index) {
        new DoubleLinkedListActionGet(
                index, getRootScreen(), null
        ).start();
    }

    @Override
    public void actionRemove(int index) {
        new DoubleLinkedListActionRemove1(
                index, getRootScreen(), null
        ).start();
    }

    @Override
    public void actionRemove(Integer value) {
        new DoubleLinkedListActionRemove2(
                value, getRootScreen(), null
        ).start();
    }
}
