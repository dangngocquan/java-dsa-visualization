package src.components.components.datastructures.list.doublelinkedlist.actionanimation;

import src.Config;
import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.doublelinkedlist.DoubleLinkedListPanelNode;
import src.components.components.datastructures.list.doublelinkedlist.DoubleLinkedListScreen;
import src.components.components.datastructures.list.doublelinkedlist.ViewDoubleLinkedListAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class DoubleLinkedListActionAdd1 extends AbstractDoubleLinkedListAnimation {
    public int value;
    public int index;
    public int x;
    public int y;
    public DoubleLinkedListPanelNode panelNode;
    public DoubleLinkedListPanelNode prevPanelNode;

    public DoubleLinkedListActionAdd1(
            int value, AbstractListScreen rootScreen,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        this.value = value;
        index = rootScreen.list.size();
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
            createArrowFromNodeToPrevNode();
            animationStep++;
        } else if (animationStep == 3) {
            addToDataOfArrayList();
            animationStep++;
        } else if (animationStep == 4) {
            reformatPanelNodes();
            animationStep++;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void createNewElement() {
        getRootScreen().setDescription(
                String.format(
                        "[CREATE] Create Node node = new Node(%d)", value
                )
        );
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

    public void createArrowFromPrevNode() {
        getRootScreen().list.add(panelNode);
        panelNode.setVisible(false);
        if (index > 0) {
            getRootScreen().setDescription(
                    "[UPDATE] prevNode.next := node"
            );
            prevPanelNode = (DoubleLinkedListPanelNode) getRootScreen().list.get(index-1);
            prevPanelNode.setNextArrow(prevPanelNode.getDefaultNextArrow());
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevPanelNode.nextArrow,
                    new int[] {
                            prevPanelNode.nextArrow[0],
                            prevPanelNode.nextArrow[1],
                            prevPanelNode.nextArrow[2],
                            prevPanelNode.nextArrow[3]
                                    + ViewDoubleLinkedListAction.HEIGHT_NODE
                                    + ViewDoubleLinkedListAction.GAP_Y
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    prevPanelNode.panels[2],
                    prevPanelNode.panels[2].getBackgroundColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
        }
    }

    public void createArrowFromNodeToPrevNode() {
        if (index > 0) {
            getRootScreen().setDescription(
                    "[UPDATE] node.prev := prevNode"
            );
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
                                    - ViewDoubleLinkedListAction.GAP_Y,

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
            ServiceAnimation.transitionBorderColor(
                    prevPanelNode,
                    prevPanelNode.getBorderColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
            ServiceAnimation.transitionColor(
                    prevPanelNode.panels[2],
                    prevPanelNode.panels[2].getBackgroundColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
            ServiceAnimation.transitionBorderColor(
                    panelNode,
                    panelNode.getBorderColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
        }
    }

    public void addToDataOfArrayList() {
        getRootScreen().setDescription(
                String.format(
                        "[INSERT] Inserted new element %d", value
                )
        );
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
        }

    }

    public void reformatPanelNodes() {
        if (index > 0) {
            ServiceAnimation.transitionBorderColor1(
                    prevPanelNode,
                    getRootScreen(),
                    prevPanelNode.getBorderColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
            ServiceAnimation.transitionColor(
                    panelNode.panels[0],
                    panelNode.panels[0].getBackgroundColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
        }
    }
}
