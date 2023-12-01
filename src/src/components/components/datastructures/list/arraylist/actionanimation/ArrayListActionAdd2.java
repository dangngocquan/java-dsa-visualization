package src.components.components.datastructures.list.arraylist.actionanimation;

import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.arraylist.ArrayListPanelNode;
import src.components.components.datastructures.list.arraylist.ArrayListScreen;
import src.components.components.datastructures.list.arraylist.ViewArrayListAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class ArrayListActionAdd2 extends AbstractArrayListAnimation {
    public int value;
    public int index;
    public int x;
    public int y;
    public ArrayListPanelNode panelNode;

    public ArrayListActionAdd2(
            int index, int value, AbstractListScreen rootScreen,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
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
        getRootScreen().setDescription(
                String.format(
                        "[CREATE] New element %d", value
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
    }

    public void movePanelNodeToRight(int i) {
        AbstractPanelDataStructureNode node = getRootScreen().list.get(i);
        getRootScreen().setDescription(
                String.format(
                        "[UPDATE] a[%d] := a[%d] = %d", i + 1, i, node.getValue()
                )
        );
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                ViewArrayListAction.SIZE_PER_NODE + ViewArrayListAction.GAP_X,
                0,
                10,
                period - 10
        );
    }

    public void addToDataOfArrayList() {
        getRootScreen().setDescription(
                String.format(
                        "[INSERT] a[%d] := %d", index, value
                )
        );
        rootScreen.viewAction.setComponentZOrder(panelNode, 0);
        ServiceAnimation.translate(
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
