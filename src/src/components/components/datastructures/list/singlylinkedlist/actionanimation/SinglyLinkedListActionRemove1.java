package src.components.components.datastructures.list.singlylinkedlist.actionanimation;

import src.Config;
import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.AbstractPanelListNode;
import src.components.components.datastructures.list.singlylinkedlist.SinglyLinkedListPanelNode;
import src.components.components.datastructures.list.singlylinkedlist.SinglyLinkedListScreen;
import src.components.components.datastructures.list.singlylinkedlist.ViewSinglyLinkedListAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class SinglyLinkedListActionRemove1 extends AbstractSinglyLinkedListAnimation {
    private final int index;
    private SinglyLinkedListPanelNode node;
    private SinglyLinkedListPanelNode prevNode;
    private SinglyLinkedListPanelNode nextNode;

    public SinglyLinkedListActionRemove1(
            int index,
            AbstractListScreen rootScreen, int period,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.index = index;
    }

    public SinglyLinkedListScreen getRootScreen() {
        return (SinglyLinkedListScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep <= index * 2) {
            if (animationStep % 2 == 0) {
                checkNode(animationStep/2);
            } else {
                uncheckNode(animationStep/2);
            }
            animationStep++;
        } else if (animationStep == index * 2 + 1) {
            pickUpElement(index);
            animationStep++;
        } else if (animationStep == index * 2 + 2) {
            createArrowFromPrevToNext();
            animationStep++;
        } else if (animationStep == index * 2 + 3) {
            returnElement();
            animationStep++;
        } else if (animationStep == index * 2 + 4) {
            solveEnd();
            animationStep++;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void checkNode(int i) {
        AbstractPanelListNode node0 = getRootScreen().list.get(i);
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

    public void uncheckNode(int i) {
        AbstractPanelListNode node0 = getRootScreen().list.get(i);
        ServiceAnimation.transitionColor(
                node0,
                Config.COLOR_YELLOW,
                Config.COLOR_WHITE,
                10,
                period - 10
        );
        ServiceAnimation.transitionBorderColor1(
                node0,
                getRootScreen().viewAction,
                Config.COLOR_YELLOW,
                Config.COLOR_BLUE,
                10,
                period - 10
        );
    }

    public void pickUpElement(int i) {
        node = (SinglyLinkedListPanelNode) getRootScreen().list.get(i);
        if (i-1 >= 0) {
            prevNode = (SinglyLinkedListPanelNode) getRootScreen().list.get(i-1);
        }
        if (i+1 < getRootScreen().list.size()) {
            nextNode = (SinglyLinkedListPanelNode) getRootScreen().list.get(i+1);
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
                ViewSinglyLinkedListAction.GAP_Y,
                10, period - 10
        );
        // Move arrow
        if (prevNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevNode.nextArrow,
                    new int[] {
                            prevNode.nextArrow[0],
                            prevNode.nextArrow[1],
                            prevNode.nextArrow[2],
                            prevNode.nextArrow[3] + ViewSinglyLinkedListAction.GAP_Y
                    },
                    10,
                    period - 10
            );
        }

        if (nextNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    node.nextArrow,
                    new int[] {
                            node.nextArrow[0],
                            node.nextArrow[1] + ViewSinglyLinkedListAction.GAP_Y,
                            node.nextArrow[2],
                            node.nextArrow[3]
                    },
                    10,
                    period - 10
            );
        }

    }

    public void createArrowFromPrevToNext() {
        if (prevNode != null && nextNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevNode.nextArrow,
                    new int[] {
                            prevNode.nextArrow[0],
                            prevNode.nextArrow[1],
                            prevNode.nextArrow[2]
                                    + ViewSinglyLinkedListAction.GAP_X
                                    + ViewSinglyLinkedListAction.WIDTH_NODE,
                            prevNode.nextArrow[3] - ViewSinglyLinkedListAction.GAP_Y
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    prevNode.panels[1],
                    Config.COLOR_BLUE,
                    Config.COLOR_GREEN,
                    10,
                    period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    nextNode,
                    getRootScreen().viewAction,
                    Config.COLOR_BLUE,
                    Config.COLOR_GREEN,
                    10,
                    period - 10
            );
        } else if (prevNode != null) {
            prevNode.nextArrow = null;
        } else if (nextNode != null) {
            node.nextArrow = null;
        }
    }

    public void returnElement() {
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                1500,
                0,
                10, period - 10
        );
        node.nextArrow = null;
        for (int i = index+1; i < getRootScreen().list.size(); i++) {
            AbstractPanelListNode node = getRootScreen().list.get(i);
            ServiceAnimation.translate(
                    node,
                    new Location(node.getX(), node.getY()),
                    -ViewSinglyLinkedListAction.GAP_X - ViewSinglyLinkedListAction.WIDTH_NODE,
                    0,
                    10, period - 10
            );
            if (node.nextArrow != null) {
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        node.nextArrow,
                        new int[] {
                                node.nextArrow[0]
                                        - ViewSinglyLinkedListAction.GAP_X
                                        - ViewSinglyLinkedListAction.WIDTH_NODE,
                                node.nextArrow[1],
                                node.nextArrow[2]
                                        - ViewSinglyLinkedListAction.GAP_X
                                        - ViewSinglyLinkedListAction.WIDTH_NODE,
                                node.nextArrow[3]
                        },
                        10,
                        period - 10

                );
            }
        }
        if (prevNode != null) {
            if (nextNode != null) {
                ServiceAnimation.transitionColor(
                        prevNode.panels[1],
                        Config.COLOR_GREEN,
                        Config.COLOR_BLUE,
                        10,
                        period - 10
                );
                ServiceAnimation.transitionBorderColor1(
                        nextNode,
                        getRootScreen().viewAction,
                        Config.COLOR_GREEN,
                        Config.COLOR_BLUE,
                        10,
                        period - 10
                );
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        prevNode.nextArrow,
                        new int[] {
                                prevNode.nextArrow[0],
                                prevNode.nextArrow[1],
                                prevNode.nextArrow[2]
                                        - ViewSinglyLinkedListAction.GAP_X
                                        - ViewSinglyLinkedListAction.WIDTH_NODE,
                                prevNode.nextArrow[3]
                        },
                        10,
                        period - 10

                );
            } else {
                prevNode.nextArrow = null;
            }
        }
    }

    public void solveEnd() {
        getRootScreen().viewAction.remove(node);
        getRootScreen().list.remove(index);
    }
}
