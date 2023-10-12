package src.components.components.datastructures.list.arraylist.actionanimation;

import src.Config;
import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.arraylist.ArrayListScreen;
import src.components.components.datastructures.list.arraylist.ViewArrayListAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class ArrayListActionRemove1 extends AbstractListAnimation {
    private int index;
    private AbstractPanelDataStructureNode node;

    public ArrayListActionRemove1(
            int index, AbstractListScreen rootScreen,
            int period, AbstractListAnimation nextAnimation) {
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
        } else if (animationStep <= getRootScreen().list.size() - 1 - index) {
            movePanelNodeToLeft(index + animationStep);
            animationStep++;
        } else if (animationStep == getRootScreen().list.size() - index) {
            returnElement(index);
            animationStep++;
        } else if (animationStep == getRootScreen().list.size() - index + 1) {
            solveEnd();
            animationStep = 100000;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void pickUpElement(int index) {
        node = getRootScreen().list.get(index);
        ServiceAnimation.transitionColor(
                node,
                Config.COLOR_BAR_PLAIN,
                Config.COLOR_BAR_FLAG,
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

    public void movePanelNodeToLeft(int i) {
        AbstractPanelDataStructureNode node = getRootScreen().list.get(i);
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                -ViewArrayListAction.SIZE_PER_NODE - ViewArrayListAction.GAP_X,
                0,
                10,
                period - 10
        );
    }

    public void returnElement(int index) {
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
        getRootScreen().list.remove(index);
    }
}
