package src.components.components.datastructures.list.arraylist.actionanimation;

import src.components.components.datastructures.list.abstractlistscreen.AbstractListScreen;
import src.components.components.datastructures.list.arraylist.ArrayListPanelNode;
import src.components.components.datastructures.list.arraylist.ViewArrayListAction;
import src.services.animation.Animation;
import src.services.animation.Location;

public class ActionAdd1 extends ArrayListAnimation {
    public int value;
    public int index;
    public int x;
    public int y;
    public ArrayListPanelNode panelNode;

    public ActionAdd1(int value, AbstractListScreen rootScreen, int period) {
        super(rootScreen, period);
        this.value = value;
        index = ((ViewArrayListAction) rootScreen.viewAction).panelElements.size();
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
        Animation.translate(
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
        rootScreen.list.add(value);
        rootScreen.viewAction.addPanelNode(panelNode);
        Animation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                0,
                y -panelNode.getY(),
                10,
                period - 10
        );
    }
}
