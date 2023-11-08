package src.components.components.datastructures.stack.linkedstack;

import src.Config;
import src.components.components.datastructures.stack.AbstractPanelStackNode;
import src.components.components.datastructures.stack.AbstractStackScreen;
import src.components.components.datastructures.stack.AbstractViewStackAction;
import src.components.components.datastructures.stack.linkedstack.actionanimation.LinkedStackActionPop;
import src.components.components.datastructures.stack.linkedstack.actionanimation.LinkedStackActionPush;
import src.services.ServiceComponent;

import java.awt.*;
import java.util.Iterator;

public class ViewLinkedStackAction extends AbstractViewStackAction {
    public static final int INITIAL_X = 100;
    public static final int INITIAL_Y = 50;
    public static final int WIDTH_NODE = (Config.WIDTH - INITIAL_X * 2) / 15;
    public static final int HEIGHT_NODE = (WIDTH_NODE - 20) * 2 + 30;
    public static final int GAP_X = WIDTH_NODE;
    public static final int GAP_Y = 200;

    public ViewLinkedStackAction(AbstractStackScreen rootScreen) {
        super(rootScreen);
        drawElements();
        repaint();
    }

    public LinkedStackScreen getRootScreen() {
        return (LinkedStackScreen) rootScreen;
    }

    public void drawElements() {
        for (AbstractPanelStackNode panelNode : getRootScreen().stack) {
            panelNode.setVisible(false);
            add(panelNode);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        Iterator<AbstractPanelStackNode> iterator = getRootScreen().stack.iterator();
        LinkedStackPanelNode prevNode;
        LinkedStackPanelNode node = null;
        while (iterator.hasNext()) {
            prevNode = node;
            node = (LinkedStackPanelNode) iterator.next();
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
    public void actionPush(int value) {
        new LinkedStackActionPush(
                value, getRootScreen(), 2000, null
        ).start();
    }

    @Override
    public void actionPop() {
        new LinkedStackActionPop(
                getRootScreen(), 2000, null
        ).start();
    }
}