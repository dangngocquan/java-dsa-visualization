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

public class LinkedStackActionPush extends AbstractLinkedStackAnimation {
    public int value;
    public final int index = 0;
    public int x;
    public int y;
    public LinkedStackPanelNode panelNode;
    public LinkedStackPanelNode nextNode;

    public LinkedStackActionPush(int value, AbstractStackScreen rootScreen,
            AbstractStackAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        this.value = value;
        panelNode = new LinkedStackPanelNode(index, value);
        x = ViewLinkedStackAction.INITIAL_X;
        y = ViewLinkedStackAction.INITIAL_Y;
        panelNode.setXY(
                x + 1500,
                y + ViewLinkedStackAction.HEIGHT_NODE + ViewLinkedStackAction.GAP_Y
        );
        rootScreen.viewAction.add(panelNode);
    }

    public LinkedStackScreen getRootScreen() {
        return (LinkedStackScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            createNewElement();
            animationStep++;
        } else if (animationStep == 1) {
            createArrowWithNextNode();
            animationStep++;
        } else if (animationStep == 2) {
            addToDataOfLinkedStack();
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
        for (AbstractPanelStackNode node : getRootScreen().stack) {
            ServiceAnimation.translate(
                    node,
                    new Location(node.getX(), node.getY()),
                    ViewLinkedStackAction.GAP_X + ViewLinkedStackAction.WIDTH_NODE,
                    0,
                    10, period - 10
            );
            if (node.nextArrow != null) {
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        node.nextArrow,
                        new int[] {
                                node.nextArrow[0]
                                        + ViewLinkedStackAction.GAP_X
                                        + ViewLinkedStackAction.WIDTH_NODE,
                                node.nextArrow[1],
                                node.nextArrow[2]
                                        + ViewLinkedStackAction.GAP_X
                                        + ViewLinkedStackAction.WIDTH_NODE,
                                node.nextArrow[3]
                        },
                        10,
                        period - 10

                );
            }
        }
    }

    public void createArrowWithNextNode() {
        panelNode.setVisible(false);
        getRootScreen().stack.push(panelNode);
        if (1 < getRootScreen().stack.size()) {
            panelNode = (LinkedStackPanelNode) getRootScreen().stack.pop();
            nextNode = (LinkedStackPanelNode) getRootScreen().stack.pop();
            getRootScreen().stack.push(nextNode);
            getRootScreen().stack.push(panelNode);
        }
        if (nextNode != null) {
            panelNode.setNextArrow(panelNode.getDefaultNextArrow());
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    panelNode.nextArrow,
                    new int[] {
                            panelNode.nextArrow[0],
                            panelNode.nextArrow[1],
                            nextNode.getX(),
                            nextNode.getY() + nextNode.getHeightPanel()/2
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    panelNode.panels[1],
                    panelNode.panels[1].getBackgroundColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    nextNode,
                    getRootScreen(),
                    nextNode.getBorderColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
        }
    }

    public void addToDataOfLinkedStack() {
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                0,
                y - panelNode.getY(),
                10,
                period - 10
        );
        if (nextNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    panelNode.nextArrow,
                    new int[] {
                            panelNode.nextArrow[0],
                            ViewLinkedStackAction.INITIAL_Y
                                    + panelNode.panels[1].getY()
                                    + panelNode.panels[1].getHeightPanel()/2,
                            panelNode.nextArrow[2],
                            panelNode.nextArrow[3]
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    panelNode.panels[1],
                    panelNode.panels[1].getBackgroundColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    nextNode,
                    getRootScreen(),
                    nextNode.getBorderColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
        }
    }
}
