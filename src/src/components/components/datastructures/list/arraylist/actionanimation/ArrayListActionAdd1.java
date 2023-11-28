package src.components.components.datastructures.list.arraylist.actionanimation;

import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.arraylist.ArrayListPanelNode;
import src.components.components.datastructures.list.arraylist.ArrayListScreen;
import src.components.components.datastructures.list.arraylist.ViewArrayListAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class ArrayListActionAdd1 extends AbstractArrayListAnimation {
    public int value;
    public int index;
    public int x;
    public int y;
    public ArrayListPanelNode panelNode;

    public ArrayListActionAdd1(
            int value, AbstractListScreen rootScreen,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        this.value = value;
        index = rootScreen.list.size();
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
        } else if (animationStep == 1) {
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
    }

    public void addToDataOfArrayList() {
        rootScreen.viewAction.setComponentZOrder(panelNode, 0);
        getRootScreen().list.add(panelNode);
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                0,
                y -panelNode.getY(),
                10,
                period - 10
        );
    }
}
