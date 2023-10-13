package src.components.components.datastructures.list.singlylinkedlist.actionanimation;

import src.Config;
import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.singlylinkedlist.SinglyLinkedListPanelNode;
import src.components.components.datastructures.list.singlylinkedlist.SinglyLinkedListScreen;
import src.components.components.datastructures.list.singlylinkedlist.ViewSinglyLinkedListAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class SinglyLinkedListActionAdd1 extends AbstractListAnimation {
    public int value;
    public int index;
    public int x;
    public int y;
    public SinglyLinkedListPanelNode panelNode;
    public SinglyLinkedListPanelNode prevPanelNode;

    public SinglyLinkedListActionAdd1(
            int value, AbstractListScreen rootScreen,
            int period, AbstractListAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.value = value;
        index = rootScreen.list.size();
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
            addToDataOfArrayList();
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
        getRootScreen().list.add(panelNode);
        panelNode.setVisible(false);
        if (index > 0) {
            prevPanelNode = (SinglyLinkedListPanelNode) getRootScreen().list.get(index-1);
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

    public void addToDataOfArrayList() {
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
