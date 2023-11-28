package src.components.components.datastructures.priorityqueue.unsortedlinkedpriorityqueue;

import src.Config;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.AbstractViewPriorityQueueAction;
import src.components.components.datastructures.priorityqueue.unsortedlinkedpriorityqueue.actionanimation.UnsortedLinkedPriorityQueueActionInsert;
import src.components.components.datastructures.priorityqueue.unsortedlinkedpriorityqueue.actionanimation.UnsortedLinkedPriorityQueueActionRemoveMin;
import src.models.datastructures.priorityqueue.EntryInterface;
import src.services.ServiceComponent;

import java.awt.*;
import java.util.Iterator;

public class ViewUnsortedLinkedPriorityQueueAction extends AbstractViewPriorityQueueAction {
    public static final int INITIAL_X = 100;
    public static final int INITIAL_Y = 50;
    public static final int WIDTH_NODE = (Config.WIDTH - INITIAL_X * 2) / 15;
    public static final int HEIGHT_NODE = (WIDTH_NODE - 20) * 2 + 30;
    public static final int GAP_X = WIDTH_NODE;
    public static final int GAP_Y = 200;

    public ViewUnsortedLinkedPriorityQueueAction(AbstractPriorityQueueScreen rootScreen) {
        super(rootScreen);
        drawElements();
        repaint();
    }

    public UnsortedLinkedPriorityQueueScreen getRootScreen() {
        return (UnsortedLinkedPriorityQueueScreen) rootScreen;
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
        UnsortedLinkedPriorityQueuePanelNode prevNode;
        UnsortedLinkedPriorityQueuePanelNode node = null;
        while (iterator.hasNext()) {
            prevNode = node;
            node = (UnsortedLinkedPriorityQueuePanelNode) iterator.next().getValue();
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
        new UnsortedLinkedPriorityQueueActionInsert(
                key, value, getRootScreen(), null
        ).start();
    }

    @Override
    public void actionRemoveMin() {
        new UnsortedLinkedPriorityQueueActionRemoveMin(
                getRootScreen(), null
        ).start();
    }
}