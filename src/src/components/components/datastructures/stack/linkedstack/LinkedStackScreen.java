package src.components.components.datastructures.stack.linkedstack;


import src.components.components.datastructures.stack.AbstractStackScreen;
import src.models.datastructures.stack.LinkedListStack;

public class LinkedStackScreen extends AbstractStackScreen {
    @Override
    public void createViewAction() {
        viewAction = new ViewLinkedStackAction(this);
        add(viewAction);
    }

    @Override
    public void createStack() {
        stack = new LinkedListStack<>();
        LinkedStackPanelNode node3 = new LinkedStackPanelNode(2, 4);
        LinkedStackPanelNode node2 = new LinkedStackPanelNode(1, 0);
        LinkedStackPanelNode node1 = new LinkedStackPanelNode(0, 1);
        stack.push(node3);
        stack.push(node2);
        stack.push(node1);
        node1.setNextArrow(node1.getDefaultNextArrow());
        node2.setNextArrow(node2.getDefaultNextArrow());
    }
}