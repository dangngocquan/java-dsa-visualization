package src.components.components.datastructures.stack.arraystack.actionanimation;

import src.Config;
import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.stack.AbstractStackAnimation;
import src.components.components.datastructures.stack.AbstractStackScreen;
import src.components.components.datastructures.stack.arraystack.ArrayStackScreen;
import src.components.components.datastructures.stack.arraystack.ViewArrayStackAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class ArrayStackActionPop extends AbstractArrayStackAnimation {
    private final int index;
    private AbstractPanelDataStructureNode node;

    public ArrayStackActionPop(
            AbstractStackScreen rootScreen,
            AbstractStackAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        this.index = getRootScreen().stack.size()-1;
    }

    public ArrayStackScreen getRootScreen() {
        return (ArrayStackScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            pickUpElement();
            animationStep++;
        } else if (animationStep <= getRootScreen().stack.size() - 1 - index) {
            movePanelNodeToLeft();
            animationStep++;
        } else if (animationStep == getRootScreen().stack.size() - index) {
            returnElement();
            animationStep++;
        } else if (animationStep == getRootScreen().stack.size() - index + 1) {
            solveEnd();
            animationStep = 100000;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void pickUpElement() {
        node = getRootScreen().stack.top();
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
                ViewArrayStackAction.GAP_Y + 20,
                10, period - 10
        );
    }

    public void movePanelNodeToLeft() {
        AbstractPanelDataStructureNode node = getRootScreen().stack.top();
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                -ViewArrayStackAction.SIZE_PER_NODE - ViewArrayStackAction.GAP_X,
                0,
                10,
                period - 10
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
        getRootScreen().stack.pop();
    }
}