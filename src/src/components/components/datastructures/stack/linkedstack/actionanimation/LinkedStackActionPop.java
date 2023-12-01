package src.components.components.datastructures.stack.linkedstack.actionanimation;

import src.Config;
import src.components.components.datastructures.stack.AbstractPanelStackNode;
import src.components.components.datastructures.stack.AbstractStackAnimation;
import src.components.components.datastructures.stack.AbstractStackScreen;
import src.components.components.datastructures.stack.linkedstack.LinkedStackPanelNode;
import src.components.components.datastructures.stack.linkedstack.LinkedStackScreen;
import src.components.components.datastructures.stack.linkedstack.ViewLinkedStackAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.Iterator;

public class LinkedStackActionPop extends AbstractLinkedStackAnimation {
    private LinkedStackPanelNode node;
    private LinkedStackPanelNode nextNode;

    public LinkedStackActionPop(
            AbstractStackScreen rootScreen,
            AbstractStackAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        animationStep = 1;
    }

    public LinkedStackScreen getRootScreen() {
        return (LinkedStackScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 1) {
            pickUpElement();
            animationStep++;
        } else if (animationStep == 2) {
            createArrowFromPrevToNext();
            animationStep++;
        } else if (animationStep == 3) {
            returnElement();
            animationStep++;
        } else if (animationStep == 4) {
            solveEnd();
            animationStep++;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void pickUpElement() {
        node = (LinkedStackPanelNode) getRootScreen().stack.top();
        getRootScreen().setDescription(
                "[POP] Pop this node"
        );
        if (1 < getRootScreen().stack.size()) {
            node = (LinkedStackPanelNode) getRootScreen().stack.pop();
            nextNode = (LinkedStackPanelNode) getRootScreen().stack.pop();
            getRootScreen().stack.push(nextNode);
            getRootScreen().stack.push(node);
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
                ViewLinkedStackAction.GAP_Y,
                10, period - 10
        );
        // Move arrow
        if (nextNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    node.nextArrow,
                    new int[] {
                            node.nextArrow[0],
                            node.nextArrow[1] + ViewLinkedStackAction.GAP_Y,
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
                    "[UPDATE] head := node.next; node.next := null"
            );
            getRootScreen().viewAction.repaint();
        }
    }

    public void returnElement() {
        getRootScreen().setDescription(
                String.format(
                        "[POP] Piped element %d from stack", node.getValue()
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
        Iterator<AbstractPanelStackNode> iterator = getRootScreen().stack.iterator();
        iterator.next();
        while (iterator.hasNext()) {
            AbstractPanelStackNode node = iterator.next();
            ServiceAnimation.translate(
                    node,
                    new Location(node.getX(), node.getY()),
                    -ViewLinkedStackAction.GAP_X - ViewLinkedStackAction.WIDTH_NODE,
                    0,
                    10, period - 10
            );
            if (node.nextArrow != null) {
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        node.nextArrow,
                        new int[] {
                                node.nextArrow[0]
                                        - ViewLinkedStackAction.GAP_X
                                        - ViewLinkedStackAction.WIDTH_NODE,
                                node.nextArrow[1],
                                node.nextArrow[2]
                                        - ViewLinkedStackAction.GAP_X
                                        - ViewLinkedStackAction.WIDTH_NODE,
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
        getRootScreen().stack.pop();
    }
}
