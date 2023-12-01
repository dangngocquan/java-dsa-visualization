package src.components.components.datastructures.list.arraylist.actionanimation;

import src.Config;
import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.AbstractPanelListNode;
import src.components.components.datastructures.list.arraylist.ArrayListPanelNode;
import src.components.components.datastructures.list.arraylist.ArrayListScreen;
import src.components.components.datastructures.list.arraylist.ViewArrayListAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class ArrayListActionGet extends AbstractArrayListAnimation {
    private final int index;
    private AbstractPanelListNode node;

    public ArrayListActionGet(
            int index,
            AbstractListScreen rootScreen,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        this.index = index;
    }

    public ArrayListScreen getRootScreen() {
        return (ArrayListScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            pickUpElement(index);
            animationStep++;
        } else if (animationStep == 1) {
            returnElement();
            animationStep++;
        } else if (animationStep == 2) {
            solveEnd();
            animationStep++;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void pickUpElement(int index) {
        AbstractPanelListNode node0 = getRootScreen().list.get(index);
        getRootScreen().setDescription(
                String.format(
                        "[GET] Get a[%d] = %d", index, node0.getValue()
                )
        );
        node = new ArrayListPanelNode(node0.getIndex(), node0.getValue());
        getRootScreen().viewAction.add(node);
        ServiceAnimation.transitionColor(
                node,
                Config.COLOR_BAR_PLAIN,
                Config.COLOR_BAR_DONE,
                10,
                period - 10
        );
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                0,
                ViewArrayListAction.GAP_Y + 20,
                10, period - 10
        );
    }

    public void returnElement() {
        getRootScreen().setDescription(
                String.format(
                        "[RETURN] Return a[%d] = %d", index, node.getValue()
                )
        );
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                1500,
                0,
                10, period - 10
        );
    }

    public void solveEnd() {
        getRootScreen().viewAction.remove(node);
    }
}
