package src.components.components.datastructures.list.singlylinkedlist;

import src.components.components.datastructures.list.AbstractListScreen;
import src.models.datastructures.list.MySinglyLinkedList;

public class SinglyLinkedListScreen extends AbstractListScreen {
    @Override
    public void createViewAction() {
        viewAction = new ViewSinglyLinkedListAction(this);
        add(viewAction);
    }

    @Override
    public void createList() {
        list = new MySinglyLinkedList<>();
//        list.add(new SinglyLinkedListPanelNode(list.size(), 1));
//        list.add(new SinglyLinkedListPanelNode(list.size(), 0));
//        list.add(new SinglyLinkedListPanelNode(list.size(), 4));
    }
}
