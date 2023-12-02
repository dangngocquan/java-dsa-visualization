package src.components.components.datastructures.priorityqueue.sortedlinkedpriorityqueue;

import src.Config;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.AbstractViewPriorityQueueAction;
import src.components.components.datastructures.priorityqueue.sortedlinkedpriorityqueue.actionanimation.SortedLinkedPriorityQueueActionInsert;
import src.components.components.datastructures.priorityqueue.sortedlinkedpriorityqueue.actionanimation.SortedLinkedPriorityQueueActionRemoveMin;
import src.models.datastructures.priorityqueue.EntryInterface;
import src.services.ServiceComponent;

import java.awt.*;
import java.util.Iterator;

public class ViewSortedLinkedPriorityQueueAction extends AbstractViewPriorityQueueAction {
    public static final int INITIAL_X = 100;
    public static final int INITIAL_Y = 50;
    public static final int WIDTH_NODE = (Config.WIDTH - INITIAL_X * 2) / (Config.MAX_NODE_TYPE_2 * 2 - 1);
    public static final int HEIGHT_NODE = (WIDTH_NODE - 20) * 2 + 30;
    public static final int GAP_X = WIDTH_NODE;
    public static final int GAP_Y = 200;

    public ViewSortedLinkedPriorityQueueAction(AbstractPriorityQueueScreen rootScreen) {
        super(rootScreen);
        drawElements();
        addDescriptionPanel();
        repaint();
    }

    public SortedLinkedPriorityQueueScreen getRootScreen() {
        return (SortedLinkedPriorityQueueScreen) rootScreen;
    }

    public void drawElements() {
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator
                = getRootScreen().queue.iterator();
        while (iterator.hasNext()) {
            AbstractPanelPriorityQueueNode panelNode = iterator.next().getValue();
            panelNode.setVisible(false);
            add(panelNode);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator
                = getRootScreen().queue.iterator();
        SortedLinkedPriorityQueuePanelNode prevNode;
        SortedLinkedPriorityQueuePanelNode node = null;
        while (iterator.hasNext()) {
            prevNode = node;
            node = (SortedLinkedPriorityQueuePanelNode) iterator.next().getValue();
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
        }
    }

    @Override
    public void actionInsert(Integer key, Integer value) {
        new SortedLinkedPriorityQueueActionInsert(
                key, value, getRootScreen(), null
        ).start();
    }

    @Override
    public void actionRemoveMin() {
        new SortedLinkedPriorityQueueActionRemoveMin(
                getRootScreen(), null
        ).start();
    }
}