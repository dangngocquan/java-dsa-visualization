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
    private AbstractPanelDataStructureNode node;

    public ArrayStackActionPop(
            AbstractStackScreen rootScreen,
            AbstractStackAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        int index = getRootScreen().stack.size() - 1;
    }

    public ArrayStackScreen getRootScreen() {
        return (ArrayStackScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            pickUpElement();
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

    public void pickUpElement() {
        node = getRootScreen().stack.top();
        getRootScreen().setDescription(
                String.format(
                        "[POP] Pop element %d from stack", node.getValue()
                )
        );
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
    public void returnElement() {
        getRootScreen().setDescription(
                String.format(
                        "[RETURN] Return piped element %d", node.getValue()
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
        getRootScreen().stack.pop();
    }
}