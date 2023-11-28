package src.components.components.datastructures.queue.linkedqueue.actionanimation;

import src.Config;
import src.components.components.datastructures.queue.AbstractQueueAnimation;
import src.components.components.datastructures.queue.AbstractQueueScreen;
import src.components.components.datastructures.queue.linkedqueue.LinkedQueuePanelNode;
import src.components.components.datastructures.queue.linkedqueue.LinkedQueueScreen;
import src.components.components.datastructures.queue.linkedqueue.ViewLinkedQueueAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class LinkedQueueActionEnqueue extends AbstractLinkedQueueAnimation {
    public int value;
    public int index;
    public int x;
    public int y;
    public LinkedQueuePanelNode panelNode;
    public LinkedQueuePanelNode prevPanelNode;

    public LinkedQueueActionEnqueue(
            int value, AbstractQueueScreen rootScreen,
            AbstractQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        this.value = value;
        index = rootScreen.queue.size();
        panelNode = new LinkedQueuePanelNode(index, value);
        x = ViewLinkedQueueAction.INITIAL_X
                + ViewLinkedQueueAction.GAP_X * index
                + ViewLinkedQueueAction.WIDTH_NODE * index;
        y = ViewLinkedQueueAction.INITIAL_Y;
        panelNode.setXY(
                x + 1500,
                y + ViewLinkedQueueAction.HEIGHT_NODE + ViewLinkedQueueAction.GAP_Y
        );
        rootScreen.viewAction.add(panelNode);
    }

    public LinkedQueueScreen getRootScreen() {
        return (LinkedQueueScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            createNewElement();
            animationStep++;
        } else if (animationStep == 1) {
            createArrowWithPrevNode();
            animationStep++;
        } else if (animationStep == 2) {
            addToDataOfArrayQueue();
            animationStep++;
        } else if (animationStep == 3) {
            reformatPanelNodes();
            animationStep++;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void createNewElement() {
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                -1500,
                0,
                10,
                period - 10
        );
        ServiceAnimation.transitionBorderColor1(
                panelNode,
                getRootScreen(),
                panelNode.getBorderColor(),
                Config.COLOR_GREEN,
                10, period - 10
        );
    }

    public void createArrowWithPrevNode() {
        if (index == 0) {
            getRootScreen().queue.enqueue(panelNode);
            panelNode.setVisible(false);
        } else if (index > 0) {
            prevPanelNode = (LinkedQueuePanelNode) getRootScreen().queue.last();
            getRootScreen().queue.enqueue(panelNode);
            panelNode.setVisible(false);
            int[] startData = prevPanelNode.getDefaultNextArrow();
            int[] endData = new int[] {
                    startData[0],
                    startData[1],
                    panelNode.getX(),
                    panelNode.getY() + panelNode.getHeightPanel()/2
            };
            prevPanelNode.setNextArrow(startData);
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevPanelNode.nextArrow,
                    endData,
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    prevPanelNode.panels[1],
                    prevPanelNode.panels[1].getBackgroundColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
        }
    }

    public void addToDataOfArrayQueue() {
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                0,
                y - panelNode.getY(),
                10,
                period - 10
        );
        if (index > 0) {
            int[] endData = prevPanelNode.getDefaultNextArrow();
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevPanelNode.nextArrow,
                    endData,
                    10,
                    period - 10
            );
        }

    }

    public void reformatPanelNodes() {
        ServiceAnimation.transitionBorderColor1(
                panelNode,
                getRootScreen(),
                panelNode.getBorderColor(),
                Config.COLOR_BLUE,
                10, period - 10
        );
        if (index > 0) {
            ServiceAnimation.transitionColor1(
                    prevPanelNode.panels[1],
                    getRootScreen(),
                    prevPanelNode.panels[1].getBackgroundColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
        }
    }
}