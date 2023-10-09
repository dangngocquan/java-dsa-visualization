package src.components.components.datastructures.list.arraylist.actionanimation;

import src.components.components.datastructures.list.abstractlistscreen.AbstractListAnimation;
import src.components.components.datastructures.list.abstractlistscreen.AbstractListScreen;
import src.components.components.datastructures.list.abstractlistscreen.AbstractPanelListNode;
import src.components.components.datastructures.list.arraylist.ArrayListPanelNode;
import src.components.components.datastructures.list.arraylist.ArrayListScreen;
import src.components.components.datastructures.list.arraylist.ViewArrayListAction;
import src.models.datastructures.list.MyArrayList;
import src.services.animation.Animation;
import src.services.animation.Location;

public class ArrayListActionAdd2 extends AbstractListAnimation {
    public int value;
    public int index;
    public int x;
    public int y;
    public ArrayListPanelNode panelNode;

    public ArrayListActionAdd2(
            int index, int value, AbstractListScreen rootScreen,
            int period, AbstractListAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.index = index;
        this.value = value;
        panelNode = new ArrayListPanelNode(index, value);
        x = ViewArrayListAction.INITIAL_X
                + ViewArrayListAction.GAP_X * (index + 1)
                + ViewArrayListAction.SIZE_PER_NODE * index;
        y = ViewArrayListAction.INITIAL_Y + 10;
        panelNode.setXY(
                x + 1500,
                y + ViewArrayListAction.SIZE_PER_NODE + 10 + ViewArrayListAction.GAP_Y
        );
        rootScreen.viewAction.add(panelNode);
    }

    public ArrayListScreen getRootScreen() {
        return (ArrayListScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            createNewElement();
            animationStep++;
        } else if (animationStep <= getRootScreen().list.size() - index) {
            movePanelNodeToRight(getRootScreen().list.size() - animationStep);
            animationStep++;
        } else if (animationStep == getRootScreen().list.size() - index + 1) {
            addToDataOfArrayList();
            animationStep = 100000;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void createNewElement() {
        Animation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                -1500,
                0,
                10,
                period - 10
        );
    }

    public void movePanelNodeToRight(int i) {
        AbstractPanelListNode node = getRootScreen().list.get(i);
        Animation.translate(
                node,
                new Location(node.getX(), node.getY()),
                ViewArrayListAction.SIZE_PER_NODE + ViewArrayListAction.GAP_X,
                0,
                10,
                period - 10
        );
    }

    public void addToDataOfArrayList() {
        rootScreen.viewAction.setComponentZOrder(panelNode, 0);
        Animation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                0,
                y -panelNode.getY(),
                10,
                period - 10
        );
        getRootScreen().list.add(index, panelNode);
    }
}
