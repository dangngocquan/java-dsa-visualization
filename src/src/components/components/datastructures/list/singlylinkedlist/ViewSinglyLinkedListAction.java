package src.components.components.datastructures.list.singlylinkedlist;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.list.abstractlistscreen.AbstractListScreen;
import src.components.components.datastructures.list.abstractlistscreen.AbstractPanelListNode;
import src.components.components.datastructures.list.abstractlistscreen.AbstractViewListAction;
import src.components.components.datastructures.list.arraylist.ArrayListScreen;

import java.awt.*;

public class ViewSinglyLinkedListAction extends AbstractViewListAction {
    public static final int INITIAL_X = 100;
    public static final int INITIAL_Y = 150;
    public static final int WIDTH_NODE = (Config.WIDTH - INITIAL_X * 2) / 15;
    public static final int HEIGHT_NODE = (WIDTH_NODE - 20) * 2 + 30;
    public static final int GAP_X = WIDTH_NODE;
    public static final int GAP_Y = 200;
    public Panel mainPanel;

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
            AbstractPanelListNode panelNode = getRootScreen().list.get(i);
            add(panelNode);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(2));
        g2d.drawLine(0, 0, 400, 400);
        System.out.println("Paint");
    }

    @Override
    public void actionAdd(int value) {

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
