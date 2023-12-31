package src.components.components.datastructures.list.singlylinkedlist.actionanimation;

import src.Config;
import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.AbstractPanelListNode;
import src.components.components.datastructures.list.singlylinkedlist.SinglyLinkedListPanelNode;
import src.components.components.datastructures.list.singlylinkedlist.SinglyLinkedListScreen;
import src.components.components.datastructures.list.singlylinkedlist.ViewSinglyLinkedListAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class SinglyLinkedListActionGet extends AbstractSinglyLinkedListAnimation {
    private final int index;
    private AbstractPanelListNode node;

    public SinglyLinkedListActionGet(
            int index,
            AbstractListScreen rootScreen,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        this.index = index;
    }

    public SinglyLinkedListScreen getRootScreen() {
        return (SinglyLinkedListScreen) rootScreen;
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
        getRootScreen().setDescription(
                String.format(
                        "[CHECK] Checking node(element=%d), index = %d", node0.getValue(), i
                )
        );
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
        node = new SinglyLinkedListPanelNode(i, node0.getValue());
        getRootScreen().setDescription(
                String.format(
                        "[GET] Get node(%d) with index = %d", node.getValue(), index
                )
        );
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
                ViewSinglyLinkedListAction.HEIGHT_NODE + ViewSinglyLinkedListAction.GAP_Y,
                10, period - 10
        );
    }

    public void returnElement() {
        getRootScreen().setDescription(
                String.format(
                        "[RETURN] Return element %d", node.getValue()
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
