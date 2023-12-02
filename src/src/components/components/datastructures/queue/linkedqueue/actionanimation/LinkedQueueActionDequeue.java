package src.components.components.datastructures.queue.linkedqueue.actionanimation;

import src.Config;
import src.components.components.datastructures.queue.AbstractPanelQueueNode;
import src.components.components.datastructures.queue.AbstractQueueAnimation;
import src.components.components.datastructures.queue.AbstractQueueScreen;
import src.components.components.datastructures.queue.linkedqueue.LinkedQueuePanelNode;
import src.components.components.datastructures.queue.linkedqueue.LinkedQueueScreen;
import src.components.components.datastructures.queue.linkedqueue.ViewLinkedQueueAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.Iterator;

public class LinkedQueueActionDequeue extends AbstractLinkedQueueAnimation {
    private LinkedQueuePanelNode node;
    private LinkedQueuePanelNode nextNode;

    public LinkedQueueActionDequeue(
            AbstractQueueScreen rootScreen,
            AbstractQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    public LinkedQueueScreen getRootScreen() {
        return (LinkedQueueScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            checkNode();
            animationStep = 1;
        } else if (animationStep == 1) {
            pickUpElement();
            animationStep = 2;
        } else if (animationStep == 2) {
            createArrowFromPrevToNext();
            animationStep = 3;
        } else if (animationStep == 3) {
            returnElement();
            animationStep = 4;
        } else if (animationStep == 4) {
            solveEnd();
            animationStep++;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void checkNode() {
        AbstractPanelQueueNode node0 = getRootScreen().queue.first();
        getRootScreen().setDescription(
                String.format(
                        "[DEQUEUE] Dequeue element %d from queue", node0.getValue()
                )
        );
        ServiceAnimation.transitionColor(
                node0,
                Config.COLOR_WHITE,
                Config.COLOR_YELLOW,
                10,
                period - 10
        );
        ServiceAnimation.transitionBorderColor1(
                node0,
                getRootScreen().viewAction,
                Config.COLOR_BLUE,
                Config.COLOR_YELLOW,
                10,
                period - 10
        );
    }

    public void pickUpElement() {
        node = (LinkedQueuePanelNode) getRootScreen().queue.first();
        int index = 0;
        if (index +1 < getRootScreen().queue.size()) {
            Iterator<?> iterator= getRootScreen().queue.iterator();
            iterator.next();
            nextNode = (LinkedQueuePanelNode) iterator.next();
        }
        // Move node
        ServiceAnimation.transitionColor(
                node,
                Config.COLOR_YELLOW,
                Config.COLOR_RED,
                10,
                period - 10
        );
        ServiceAnimation.transitionBorderColor1(
                node,
                getRootScreen().viewAction,
                Config.COLOR_YELLOW,
                Config.COLOR_BLUE,
                10,
                period - 10
        );
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                0,
                ViewLinkedQueueAction.GAP_Y + ViewLinkedQueueAction.HEIGHT_NODE,
                10, period - 10
        );

        if (nextNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    node.nextArrow,
                    new int[] {
                            node.nextArrow[0],
                            node.nextArrow[1] + ViewLinkedQueueAction.GAP_Y + ViewLinkedQueueAction.HEIGHT_NODE,
                            node.nextArrow[2],
                            node.nextArrow[3]
                    },
                    10,
                    period - 10
            );
        }

    }

    public void createArrowFromPrevToNext() {
        if (nextNode != null) {
            node.nextArrow = null;
            getRootScreen().setDescription(
                    "[UPDATE] node.next := null"
            );
            getRootScreen().viewAction.repaint();
        }
    }

    public void returnElement() {
        getRootScreen().setDescription(
                String.format(
                        "[RETURN] Return element %d from queue.", node.getValue()
                )
        );
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                1500,
                0,
                10, period - 10
        );
        node.nextArrow = null;
        Iterator<AbstractPanelQueueNode> iterator = getRootScreen().queue.iterator();
        if (iterator.hasNext()) iterator.next();
        while (iterator.hasNext()) {
            AbstractPanelQueueNode node = iterator.next();
            ServiceAnimation.translate(
                    node,
                    new Location(node.getX(), node.getY()),
                    -ViewLinkedQueueAction.GAP_X - ViewLinkedQueueAction.WIDTH_NODE,
                    0,
                    10, period - 10
            );
            if (node.nextArrow != null) {
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        node.nextArrow,
                        new int[] {
                                node.nextArrow[0]
                                        - ViewLinkedQueueAction.GAP_X
                                        - ViewLinkedQueueAction.WIDTH_NODE,
                                node.nextArrow[1],
                                node.nextArrow[2]
                                        - ViewLinkedQueueAction.GAP_X
                                        - ViewLinkedQueueAction.WIDTH_NODE,
                                node.nextArrow[3]
                        },
                        10,
                        period - 10

                );
            }
        }
    }

    public void solveEnd() {
        getRootScreen().viewAction.remove(node);
        getRootScreen().queue.dequeue();
    }
}