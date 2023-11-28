package src.components.components.datastructures.stack.arraystack.actionanimation;

import src.components.components.datastructures.stack.AbstractStackAnimation;
import src.components.components.datastructures.stack.AbstractStackScreen;
import src.components.components.datastructures.stack.arraystack.ArrayStackPanelNode;
import src.components.components.datastructures.stack.arraystack.ArrayStackScreen;
import src.components.components.datastructures.stack.arraystack.ViewArrayStackAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class ArrayStackActionPush extends AbstractArrayStackAnimation {
    public int value;
    public int index;
    public int x;
    public int y;
    public ArrayStackPanelNode panelNode;

    public ArrayStackActionPush(
            int value, AbstractStackScreen rootScreen,
            AbstractStackAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        this.value = value;
        index = rootScreen.stack.size();
        panelNode = new ArrayStackPanelNode(index, value);
        x = ViewArrayStackAction.INITIAL_X
                + ViewArrayStackAction.GAP_X * (index + 1)
                + ViewArrayStackAction.SIZE_PER_NODE * index;
        y = ViewArrayStackAction.INITIAL_Y + 10;
        panelNode.setXY(
                x + 1500,
                y + ViewArrayStackAction.SIZE_PER_NODE + 10 + ViewArrayStackAction.GAP_Y
        );
        rootScreen.viewAction.add(panelNode);
    }

    public ArrayStackScreen getRootScreen() {
        return (ArrayStackScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            createNewElement();
            animationStep++;
        } else if (animationStep == 1) {
            addToDataOfArrayStack();
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

    public void addToDataOfArrayStack() {
        rootScreen.viewAction.setComponentZOrder(panelNode, 0);
        getRootScreen().stack.push(panelNode);
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