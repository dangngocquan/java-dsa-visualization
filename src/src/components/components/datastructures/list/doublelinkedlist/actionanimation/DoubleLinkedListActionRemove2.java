package src.components.components.datastructures.list.doublelinkedlist.actionanimation;

import src.Config;
import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.AbstractPanelListNode;
import src.components.components.datastructures.list.doublelinkedlist.DoubleLinkedListPanelNode;
import src.components.components.datastructures.list.doublelinkedlist.DoubleLinkedListScreen;
import src.components.components.datastructures.list.doublelinkedlist.ViewDoubleLinkedListAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class DoubleLinkedListActionRemove2 extends AbstractDoubleLinkedListAnimation {
    private int index;
    private final int value;
    private DoubleLinkedListPanelNode node;
    private DoubleLinkedListPanelNode prevNode;
    private DoubleLinkedListPanelNode nextNode;

    public DoubleLinkedListActionRemove2(
            Integer value,
            AbstractListScreen rootScreen,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        this.value = value;
        this.index = -1;
    }

    public DoubleLinkedListScreen getRootScreen() {
        return (DoubleLinkedListScreen) rootScreen;
    }

    @Override
    public void run() {
        if (index == -1) {
            if (animationStep < getRootScreen().list.size() * 2) {
                if (animationStep % 2 == 0) {
                    checkNode(animationStep++/2);
                } else {
                    uncheckNode(animationStep++/2);
                }
            } else {
                animationStep = 0;
                end();
            }
        } else {
            if (animationStep == 0) {
                pickUpElement(index);
                animationStep++;
            } else if (animationStep == 1) {
                createArrowFromPrevToNext();
                animationStep++;
            } else if (animationStep == 2) {
                createArrowFromNextNodeToPrevNode();
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

    }

    public void checkNode(int i) {
        DoubleLinkedListPanelNode node0 = (DoubleLinkedListPanelNode) getRootScreen().list.get(i);
        getRootScreen().setDescription(
                String.format(
                        "[CHECK] Check node(%d) with index = %d", node0.getValue(), i
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
        if (node0.getValue() == value) {
            index = i;
            animationStep = 0;
            node = node0;
        }
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
        node = (DoubleLinkedListPanelNode) getRootScreen().list.get(i);
        getRootScreen().setDescription(
                "[CHECK] Remove this node."
        );
        if (i-1 >= 0) {
            prevNode = (DoubleLinkedListPanelNode) getRootScreen().list.get(i-1);
        }
        if (i+1 < getRootScreen().list.size()) {
            nextNode = (DoubleLinkedListPanelNode) getRootScreen().list.get(i+1);
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
                ViewDoubleLinkedListAction.HEIGHT_NODE + ViewDoubleLinkedListAction.GAP_Y,
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
                            prevNode.nextArrow[3]
                                    + ViewDoubleLinkedListAction.HEIGHT_NODE
                                    + ViewDoubleLinkedListAction.GAP_Y
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    node.prevArrow,
                    new int[] {
                            node.prevArrow[0],
                            node.prevArrow[1]
                                    + ViewDoubleLinkedListAction.HEIGHT_NODE
                                    + ViewDoubleLinkedListAction.GAP_Y,
                            node.prevArrow[2],
                            node.prevArrow[3]
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
                            node.nextArrow[1]
                                    + ViewDoubleLinkedListAction.HEIGHT_NODE
                                    + ViewDoubleLinkedListAction.GAP_Y,
                            node.nextArrow[2],
                            node.nextArrow[3]
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    nextNode.prevArrow,
                    new int[] {
                            nextNode.prevArrow[0],
                            nextNode.prevArrow[1],
                            nextNode.prevArrow[2],
                            nextNode.prevArrow[3]
                                    + ViewDoubleLinkedListAction.HEIGHT_NODE
                                    + ViewDoubleLinkedListAction.GAP_Y
                    },
                    10,
                    period - 10
            );
        }

    }

    public void createArrowFromPrevToNext() {
        if (prevNode != null && nextNode != null) {
            getRootScreen().setDescription(
                    "[UPDATE] prevNode.next := nextNode"
            );
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevNode.nextArrow,
                    new int[] {
                            prevNode.nextArrow[0],
                            prevNode.nextArrow[1],
                            prevNode.nextArrow[2]
                                    + ViewDoubleLinkedListAction.GAP_X
                                    + ViewDoubleLinkedListAction.WIDTH_NODE,
                            prevNode.nextArrow[3]
                                    - ViewDoubleLinkedListAction.HEIGHT_NODE
                                    - ViewDoubleLinkedListAction.GAP_Y
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    prevNode.panels[2],
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
            getRootScreen().setDescription(
                    "[UPDATE] prevNode.next := null"
            );
            getRootScreen().viewAction.repaint();
        } else if (nextNode != null) {
            node.nextArrow = null;
            getRootScreen().setDescription(
                    "[UPDATE] node.next := null"
            );
            getRootScreen().viewAction.repaint();
        }
    }

    public void createArrowFromNextNodeToPrevNode() {
        if (prevNode != null && nextNode != null) {
            getRootScreen().setDescription(
                    "[UPDATE] nextNode.prev := prevNode"
            );
            ServiceAnimation.transitionColor(
                    prevNode.panels[2],
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
                    nextNode.prevArrow,
                    new int[] {
                            nextNode.prevArrow[0],
                            nextNode.prevArrow[1],
                            nextNode.prevArrow[2]
                                    - ViewDoubleLinkedListAction.GAP_X
                                    - ViewDoubleLinkedListAction.WIDTH_NODE,
                            nextNode.prevArrow[3]
                                    - ViewDoubleLinkedListAction.HEIGHT_NODE
                                    - ViewDoubleLinkedListAction.GAP_Y
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    nextNode.panels[2],
                    Config.COLOR_BLUE,
                    Config.COLOR_GREEN,
                    10,
                    period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    prevNode,
                    getRootScreen().viewAction,
                    Config.COLOR_BLUE,
                    Config.COLOR_GREEN,
                    10,
                    period - 10
            );
        } else if (prevNode != null) {
            node.prevArrow = null;
            getRootScreen().setDescription(
                    "[UPDATE] node.prev := null"
            );
            getRootScreen().viewAction.repaint();
        } else if (nextNode != null) {
            nextNode.prevArrow = null;
            getRootScreen().setDescription(
                    "[UPDATE] nextNode.prev := null"
            );
            getRootScreen().viewAction.repaint();
        }
    }

    public void returnElement() {
        getRootScreen().setDescription(
                String.format(
                        "[RETURN] Return removed element %d", value
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
        node.prevArrow = null;
        for (int i = index+1; i < getRootScreen().list.size(); i++) {
            AbstractPanelListNode node0 = getRootScreen().list.get(i);
            ServiceAnimation.translate(
                    node0,
                    new Location(node0.getX(), node0.getY()),
                    -ViewDoubleLinkedListAction.GAP_X - ViewDoubleLinkedListAction.WIDTH_NODE,
                    0,
                    10, period - 10
            );
            if (node0.nextArrow != null) {
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        node0.nextArrow,
                        new int[] {
                                node0.nextArrow[0]
                                        - ViewDoubleLinkedListAction.GAP_X
                                        - ViewDoubleLinkedListAction.WIDTH_NODE,
                                node0.nextArrow[1],
                                node0.nextArrow[2]
                                        - ViewDoubleLinkedListAction.GAP_X
                                        - ViewDoubleLinkedListAction.WIDTH_NODE,
                                node0.nextArrow[3]
                        },
                        10,
                        period - 10

                );
            }
            if (node0.prevArrow != null && i > index + 1) {
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        node0.prevArrow,
                        new int[] {
                                node0.prevArrow[0]
                                        - ViewDoubleLinkedListAction.GAP_X
                                        - ViewDoubleLinkedListAction.WIDTH_NODE,
                                node0.prevArrow[1],
                                node0.prevArrow[2]
                                        - ViewDoubleLinkedListAction.GAP_X
                                        - ViewDoubleLinkedListAction.WIDTH_NODE,
                                node0.prevArrow[3]
                        },
                        10,
                        period - 10

                );
            }
        }
        if (prevNode != null && nextNode != null) {
            ServiceAnimation.transitionColor(
                    nextNode.panels[2],
                    Config.COLOR_GREEN,
                    Config.COLOR_BLUE,
                    10,
                    period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    prevNode,
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
                                    - ViewDoubleLinkedListAction.GAP_X
                                    - ViewDoubleLinkedListAction.WIDTH_NODE,
                            prevNode.nextArrow[3]
                    },
                    10,
                    period - 10

            );
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    nextNode.prevArrow,
                    new int[] {
                            nextNode.prevArrow[0]
                                    - ViewDoubleLinkedListAction.GAP_X
                                    - ViewDoubleLinkedListAction.WIDTH_NODE,
                            nextNode.prevArrow[1],
                            nextNode.prevArrow[2],
                            nextNode.prevArrow[3]
                    },
                    10,
                    period - 10
            );
        } else if (prevNode != null) {
            prevNode.nextArrow = null;
        } else if (nextNode != null) {
            nextNode.prevArrow = null;
        }
    }

    public void solveEnd() {
        getRootScreen().viewAction.remove(node);
        getRootScreen().list.remove(index);
    }
}
