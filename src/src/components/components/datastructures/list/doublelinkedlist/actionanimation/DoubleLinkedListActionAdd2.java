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

public class DoubleLinkedListActionAdd2 extends AbstractDoubleLinkedListAnimation {
    public int value;
    public int index;
    public int x;
    public int y;
    public DoubleLinkedListPanelNode panelNode;
    public DoubleLinkedListPanelNode prevNode;
    public DoubleLinkedListPanelNode nextNode;

    public DoubleLinkedListActionAdd2(
            int index, int value, AbstractListScreen rootScreen,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        this.index = index;
        this.value = value;
        panelNode = new DoubleLinkedListPanelNode(index, value);
        x = ViewDoubleLinkedListAction.INITIAL_X
                + ViewDoubleLinkedListAction.GAP_X * index
                + ViewDoubleLinkedListAction.WIDTH_NODE * index;
        y = ViewDoubleLinkedListAction.INITIAL_Y;
        panelNode.setXY(
                x + 1500,
                y + ViewDoubleLinkedListAction.HEIGHT_NODE + ViewDoubleLinkedListAction.GAP_Y
        );
        rootScreen.viewAction.add(panelNode);
    }

    public DoubleLinkedListScreen getRootScreen() {
        return (DoubleLinkedListScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            createNewElement();
            animationStep++;
        } else if (animationStep == 1) {
            createArrowFromPrevNode();
            animationStep++;
        } else if (animationStep == 2) {
            createArrowToNextNode();
            animationStep++;
        } else if (animationStep == 3) {
            createArrowFromNextNode();
            animationStep++;
        } else if (animationStep == 4) {
            createArrowToPrevNode();
            animationStep++;
        } else if (animationStep == 5) {
            addToDataOfArrayList();
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
        for (int i = index; i < getRootScreen().list.size(); i++) {
            AbstractPanelListNode node = getRootScreen().list.get(i);
            ServiceAnimation.translate(
                    node,
                    new Location(node.getX(), node.getY()),
                    ViewDoubleLinkedListAction.GAP_X + ViewDoubleLinkedListAction.WIDTH_NODE,
                    0,
                    10, period - 10
            );
            if (node.nextArrow != null) {
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        node.nextArrow,
                        new int[] {
                                node.nextArrow[0]
                                        + ViewDoubleLinkedListAction.GAP_X
                                        + ViewDoubleLinkedListAction.WIDTH_NODE,
                                node.nextArrow[1],
                                node.nextArrow[2]
                                        + ViewDoubleLinkedListAction.GAP_X
                                        + ViewDoubleLinkedListAction.WIDTH_NODE,
                                node.nextArrow[3]
                        },
                        10,
                        period - 10

                );
            }
            if (node.prevArrow != null && i > index) {
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        node.prevArrow,
                        new int[] {
                                node.prevArrow[0]
                                        + ViewDoubleLinkedListAction.GAP_X
                                        + ViewDoubleLinkedListAction.WIDTH_NODE,
                                node.prevArrow[1],
                                node.prevArrow[2]
                                        + ViewDoubleLinkedListAction.GAP_X
                                        + ViewDoubleLinkedListAction.WIDTH_NODE,
                                node.prevArrow[3]
                        },
                        10,
                        period - 10

                );
            }
        }
        if (index > 0 && index < getRootScreen().list.size()) {
            prevNode = (DoubleLinkedListPanelNode) getRootScreen().list.get(index - 1);
            nextNode = (DoubleLinkedListPanelNode) getRootScreen().list.get(index);
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
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    nextNode.prevArrow,
                    new int[] {
                            nextNode.prevArrow[0]
                                    + ViewDoubleLinkedListAction.GAP_X
                                    + ViewDoubleLinkedListAction.WIDTH_NODE,
                            nextNode.prevArrow[1],
                            nextNode.prevArrow[2],
                            nextNode.prevArrow[3]
                    },
                    10,
                    period - 10
            );
        }
    }

    public void createArrowFromPrevNode() {
        getRootScreen().list.add(index, panelNode);
        if (index > 0) {
            prevNode = (DoubleLinkedListPanelNode) getRootScreen().list.get(index-1);
        }
        if (prevNode != null) {
            if (prevNode.nextArrow == null) prevNode.setNextArrow(prevNode.getDefaultNextArrow());
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevNode.nextArrow,
                    new int[] {
                            prevNode.nextArrow[0],
                            prevNode.nextArrow[1],
                            panelNode.getX(),
                            panelNode.getY() + panelNode.getHeightPanel()/2
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    prevNode.panels[2],
                    prevNode.panels[2].getBackgroundColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    panelNode,
                    getRootScreen(),
                    panelNode.getBorderColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
        }
    }

    public void createArrowToNextNode() {
        if (prevNode != null) {
            ServiceAnimation.transitionColor(
                    prevNode.panels[2],
                    prevNode.panels[2].getBackgroundColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    panelNode,
                    getRootScreen(),
                    panelNode.getBorderColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
        }

        panelNode.setVisible(false);
        if (index + 1 < getRootScreen().list.size()) {
            nextNode = (DoubleLinkedListPanelNode) getRootScreen().list.get(index + 1);
        }
        if (nextNode != null) {
            panelNode.setNextArrow(panelNode.getDefaultNextArrow());
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    panelNode.nextArrow,
                    new int[] {
                            panelNode.nextArrow[0],
                            panelNode.nextArrow[1],
                            panelNode.nextArrow[2],
                            panelNode.nextArrow[3]
                                    - ViewDoubleLinkedListAction.HEIGHT_NODE
                                    - ViewDoubleLinkedListAction.GAP_Y
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    panelNode.panels[2],
                    panelNode.panels[2].getBackgroundColor(),
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

    public void createArrowFromNextNode() {
        if (nextNode != null) {
            ServiceAnimation.transitionColor(
                    panelNode.panels[2],
                    panelNode.panels[2].getBackgroundColor(),
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
            if (nextNode.prevArrow == null) {
                nextNode.prevArrow = nextNode.getDefaultPrevArrow();
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
            } else {
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        nextNode.prevArrow,
                        new int[] {
                                nextNode.prevArrow[0],
                                nextNode.prevArrow[1],
                                nextNode.prevArrow[2]
                                        + ViewDoubleLinkedListAction.WIDTH_NODE
                                        + ViewDoubleLinkedListAction.GAP_X,
                                nextNode.prevArrow[3]
                                        + ViewDoubleLinkedListAction.HEIGHT_NODE
                                        + ViewDoubleLinkedListAction.GAP_Y
                        },
                        10,
                        period - 10
                );
            }
            ServiceAnimation.transitionColor(
                    nextNode.panels[0],
                    nextNode.panels[0].getBackgroundColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    panelNode,
                    getRootScreen(),
                    panelNode.getBorderColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
        }
    }

    public void createArrowToPrevNode() {
        if (nextNode != null) {
            ServiceAnimation.transitionColor(
                    nextNode.panels[0],
                    nextNode.panels[0].getBackgroundColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    panelNode,
                    getRootScreen(),
                    panelNode.getBorderColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
        }

        if (prevNode != null) {
            panelNode.setPrevArrow(panelNode.getDefaultPrevArrow());
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    panelNode.prevArrow,
                    new int[] {
                            panelNode.prevArrow[0],
                            panelNode.prevArrow[1],
                            panelNode.prevArrow[2],
                            panelNode.prevArrow[3]
                                    - ViewDoubleLinkedListAction.HEIGHT_NODE
                                    - ViewDoubleLinkedListAction.GAP_Y
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    panelNode.panels[0],
                    panelNode.panels[0].getBackgroundColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    prevNode,
                    getRootScreen(),
                    prevNode.getBorderColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
        }
    }

    public void addToDataOfArrayList() {
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                0,
                y - panelNode.getY(),
                10,
                period - 10
        );
        if (prevNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevNode.nextArrow,
                    new int[] {
                            prevNode.nextArrow[0],
                            prevNode.nextArrow[1],
                            prevNode.nextArrow[2],
                            prevNode.nextArrow[3]
                                    - ViewDoubleLinkedListAction.HEIGHT_NODE
                                    - ViewDoubleLinkedListAction.GAP_Y
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    panelNode.prevArrow,
                    new int[] {
                            panelNode.prevArrow[0],
                            panelNode.prevArrow[1]
                                    - ViewDoubleLinkedListAction.HEIGHT_NODE
                                    - ViewDoubleLinkedListAction.GAP_Y,
                            panelNode.prevArrow[2],
                            panelNode.prevArrow[3]
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    panelNode.panels[0],
                    panelNode.panels[0].getBackgroundColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    prevNode,
                    getRootScreen(),
                    prevNode.getBorderColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
        }
        if (nextNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    panelNode.nextArrow,
                    new int[] {
                            panelNode.nextArrow[0],
                            panelNode.nextArrow[1]
                                    - ViewDoubleLinkedListAction.HEIGHT_NODE
                                    - ViewDoubleLinkedListAction.GAP_Y,
                            panelNode.nextArrow[2],
                            panelNode.nextArrow[3]
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
                                    - ViewDoubleLinkedListAction.HEIGHT_NODE
                                    - ViewDoubleLinkedListAction.GAP_Y
                    },
                    10,
                    period - 10
            );
        }
    }
}
