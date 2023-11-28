package src.components.components.datastructures.queue.linkedqueue;

import src.Config;
import src.components.components.datastructures.queue.AbstractPanelQueueNode;
import src.components.components.datastructures.queue.AbstractQueueScreen;
import src.components.components.datastructures.queue.AbstractViewQueueAction;
import src.components.components.datastructures.queue.linkedqueue.actionanimation.LinkedQueueActionDequeue;
import src.components.components.datastructures.queue.linkedqueue.actionanimation.LinkedQueueActionEnqueue;
import src.services.ServiceComponent;

import java.awt.*;
import java.util.Iterator;

public class ViewLinkedQueueAction extends AbstractViewQueueAction {
    public static final int INITIAL_X = 100;
    public static final int INITIAL_Y = 50;
    public static final int WIDTH_NODE = (Config.WIDTH - INITIAL_X * 2) / 15;
    public static final int HEIGHT_NODE = (WIDTH_NODE - 20) * 2 + 30;
    public static final int GAP_X = WIDTH_NODE;
    public static final int GAP_Y = 200;

    public ViewLinkedQueueAction(AbstractQueueScreen rootScreen) {
        super(rootScreen);
        drawElements();
        repaint();
    }

    public LinkedQueueScreen getRootScreen() {
        return (LinkedQueueScreen) rootScreen;
    }

    public void drawElements() {
        for (AbstractPanelQueueNode panelNode : getRootScreen().queue) {
            panelNode.setVisible(false);
            add(panelNode);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        Iterator<AbstractPanelQueueNode> iterator = getRootScreen().queue.iterator();
        LinkedQueuePanelNode prevNode;
        LinkedQueuePanelNode node = null;
        while (iterator.hasNext()) {
            prevNode = node;
            node = (LinkedQueuePanelNode) iterator.next();
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
    public void actionEnqueue(int value) {
        new LinkedQueueActionEnqueue(
                value, getRootScreen(), null
        ).start();
    }

    @Override
    public void actionDequeue() {
        new LinkedQueueActionDequeue(
                getRootScreen(), null
        ).start();
    }
}