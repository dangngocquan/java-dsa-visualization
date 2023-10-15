package src.components.components.datastructures.list.doublelinkedlist.actionanimation;

import src.Config;
import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.AbstractPanelListNode;
import src.components.components.datastructures.list.doublelinkedlist.DoubleLinkedListPanelNode;
import src.components.components.datastructures.list.doublelinkedlist.DoubleLinkedListScreen;
import src.components.components.datastructures.list.doublelinkedlist.ViewDoubleLinkedListAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class DoubleLinkedListActionGet extends AbstractListAnimation {
    private final int index;
    private AbstractPanelListNode node;

    public DoubleLinkedListActionGet(
            int index,
            AbstractListScreen rootScreen, int period,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.index = index;
    }

    public DoubleLinkedListScreen getRootScreen() {
        return (DoubleLinkedListScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep <= index * 2) {
            if (animationStep % 2 == 0) {
                checkNode(animationStep/2);
            } else {
                uncheckNode(animationStep/2);
            }
            animationStep++;
        } else if (animationStep == index * 2 + 1) {
            pickUpElement(index);
            animationStep++;
        } else if (animationStep == index * 2 + 2) {
            returnElement();
            animationStep++;
        } else if (animationStep == index * 2 + 3) {
            solveEnd();
            animationStep++;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void checkNode(int i) {
        AbstractPanelListNode node0 = getRootScreen().list.get(i);
        ServiceAnimation.transitionColor(
                node0,
                Config.COLOR_WHITE,
                Config.COLOR_YELLOW,
                10,
                period - 10
        );
        ServiceAnimation.transitionBorderColor1(
                node0,
                getRootScreen().viewAction,
                Config.COLOR_BLUE,
                Config.COLOR_YELLOW,
                10,
                period - 10
        );
    }

    public void uncheckNode(int i) {
        AbstractPanelListNode node0 = getRootScreen().list.get(i);
        ServiceAnimation.transitionColor(
                node0,
                Config.COLOR_YELLOW,
                Config.COLOR_WHITE,
                10,
                period - 10
        );
        ServiceAnimation.transitionBorderColor1(
                node0,
                getRootScreen().viewAction,
                Config.COLOR_YELLOW,
                Config.COLOR_BLUE,
                10,
                period - 10
        );
    }

    public void pickUpElement(int i) {
        AbstractPanelListNode node0 = getRootScreen().list.get(i);
        uncheckNode(i);
        node = new DoubleLinkedListPanelNode(node0.getIndex(), node0.getValue());
        getRootScreen().viewAction.add(node);
        ServiceAnimation.transitionColor(
                node,
                Config.COLOR_YELLOW,
                Config.COLOR_GREEN,
                10,
                period - 10
        );
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                0,
                ViewDoubleLinkedListAction.HEIGHT_NODE + ViewDoubleLinkedListAction.GAP_Y,
                10, period - 10
        );
    }

    public void returnElement() {
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
