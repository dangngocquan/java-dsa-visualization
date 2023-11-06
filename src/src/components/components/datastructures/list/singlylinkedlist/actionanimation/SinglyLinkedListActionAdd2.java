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

public class SinglyLinkedListActionAdd2 extends AbstractSinglyLinkedListAnimation {
    public int value;
    public int index;
    public int x;
    public int y;
    public SinglyLinkedListPanelNode panelNode;
    public SinglyLinkedListPanelNode prevNode;
    public SinglyLinkedListPanelNode nextNode;

    public SinglyLinkedListActionAdd2(
            int index, int value, AbstractListScreen rootScreen,
            int period, AbstractListAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.index = index;
        this.value = value;
        panelNode = new SinglyLinkedListPanelNode(index, value);
        x = ViewSinglyLinkedListAction.INITIAL_X
                + ViewSinglyLinkedListAction.GAP_X * index
                + ViewSinglyLinkedListAction.WIDTH_NODE * index;
        y = ViewSinglyLinkedListAction.INITIAL_Y;
        panelNode.setXY(
                x + 1500,
                y + ViewSinglyLinkedListAction.HEIGHT_NODE + ViewSinglyLinkedListAction.GAP_Y
        );
        rootScreen.viewAction.add(panelNode);
    }

    public SinglyLinkedListScreen getRootScreen() {
        return (SinglyLinkedListScreen) rootScreen;
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
            createArrowWithNextNode();
            animationStep++;
        } else if (animationStep == 3) {
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
                    ViewSinglyLinkedListAction.GAP_X + ViewSinglyLinkedListAction.WIDTH_NODE,
                    0,
                    10, period - 10
            );
            if (node.nextArrow != null) {
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        node.nextArrow,
                        new int[] {
                                node.nextArrow[0]
                                        + ViewSinglyLinkedListAction.GAP_X
                                        + ViewSinglyLinkedListAction.WIDTH_NODE,
                                node.nextArrow[1],
                                node.nextArrow[2]
                                        + ViewSinglyLinkedListAction.GAP_X
                                        + ViewSinglyLinkedListAction.WIDTH_NODE,
                                node.nextArrow[3]
                        },
                        10,
                        period - 10

                );
            }
        }
        if (index > 0 && index < getRootScreen().list.size()) {
            prevNode = (SinglyLinkedListPanelNode) getRootScreen().list.get(index - 1);
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevNode.nextArrow,
                    new int[] {
                            prevNode.nextArrow[0],
                            prevNode.nextArrow[1],
                            prevNode.nextArrow[2]
                                    + ViewSinglyLinkedListAction.GAP_X
                                    + ViewSinglyLinkedListAction.WIDTH_NODE,
                            prevNode.nextArrow[3]
                    },
                    10,
                    period - 10

            );
        }
    }

    public void createArrowWithPrevNode() {
        getRootScreen().list.add(index, panelNode);
        if (index > 0) {
            prevNode = (SinglyLinkedListPanelNode) getRootScreen().list.get(index-1);
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
                    prevNode.panels[1],
                    prevNode.panels[1].getBackgroundColor(),
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

    public void createArrowWithNextNode() {
        if (prevNode != null) {
            ServiceAnimation.transitionColor(
                    prevNode.panels[1],
                    prevNode.panels[1].getBackgroundColor(),
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
            nextNode = (SinglyLinkedListPanelNode) getRootScreen().list.get(index + 1);
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
                            prevNode.getDefaultNextArrow()[3]
                    },
                    10,
                    period - 10
            );
        }
        if (nextNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    panelNode.nextArrow,
                    new int[] {
                            panelNode.nextArrow[0],
                            ViewSinglyLinkedListAction.INITIAL_Y
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
