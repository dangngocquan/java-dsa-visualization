package src.components.components.datastructures.list.arraylist.actionanimation;

import src.Config;
import src.components.components.datastructures.list.abstractlistscreen.AbstractListAnimation;
import src.components.components.datastructures.list.abstractlistscreen.AbstractListScreen;
import src.components.components.datastructures.list.abstractlistscreen.AbstractPanelListNode;
import src.components.components.datastructures.list.arraylist.ArrayListPanelNode;
import src.components.components.datastructures.list.arraylist.ArrayListScreen;
import src.components.components.datastructures.list.arraylist.ViewArrayListAction;
import src.services.animation.Animation;
import src.services.animation.Location;

public class ArrayListActionGet extends AbstractListAnimation {
    private int index;
    private AbstractPanelListNode node;

    public ArrayListActionGet(
            int index,
            AbstractListScreen rootScreen, int period,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
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
            returnElement(index);
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
        node = new ArrayListPanelNode(node0.getIndex(), node0.getValue());
        getRootScreen().viewAction.add(node);
        Animation.transitionColor(
                node,
                Config.COLOR_BAR_PLAIN,
                Config.COLOR_BAR_DONE,
                10,
                period - 10
        );
        Animation.translate(
                node,
                new Location(node.getX(), node.getY()),
                0,
                ViewArrayListAction.GAP_Y + 20,
                10, period - 10
        );
    }

    public void returnElement(int index) {
        Animation.translate(
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
