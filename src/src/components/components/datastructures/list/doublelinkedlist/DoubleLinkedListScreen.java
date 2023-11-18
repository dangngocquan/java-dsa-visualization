package src.components.components.datastructures.list.doublelinkedlist;

import src.components.components.datastructures.list.AbstractListScreen;
import src.models.datastructures.list.DoubleLinkedList;

public class DoubleLinkedListScreen extends AbstractListScreen {
    @Override
    public void createViewAction() {
        viewAction = new ViewDoubleLinkedListAction(this);
        add(viewAction);
    }

    @Override
    public void createList() {
        list = new DoubleLinkedList();
        list.add(new DoubleLinkedListPanelNode(list.size(), 1));
        list.get(list.size()-1).setNextArrow(list.get(list.size()-1).getDefaultNextArrow());
        list.add(new DoubleLinkedListPanelNode(list.size(), 0));
        list.get(list.size()-1).setPrevArrow(list.get(list.size()-1).getDefaultPrevArrow());
        list.get(list.size()-1).setNextArrow(list.get(list.size()-1).getDefaultNextArrow());
        list.add(new DoubleLinkedListPanelNode(list.size(), 4));
        list.get(list.size()-1).setPrevArrow(list.get(list.size()-1).getDefaultPrevArrow());
    }
}
