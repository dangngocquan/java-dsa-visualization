package src.components.components.datastructures.stack.arraystack;

import src.components.components.datastructures.stack.AbstractStackScreen;
import src.models.datastructures.stack.ArrayStack;

public class ArrayStackScreen extends AbstractStackScreen {
    @Override
    public void createStack() {
        stack = new ArrayStack<>();
        stack.push(new ArrayStackPanelNode(stack.size(), 1));
        stack.push(new ArrayStackPanelNode(stack.size(), 0));
        stack.push(new ArrayStackPanelNode(stack.size(), 4));
    }

    @Override
    public void createViewAction() {
        viewAction = new ViewArrayStackAction(this);
        add(viewAction);
    }
}
