package src.components.components.datastructures.list.arraylist;

import src.components.components.datastructures.list.AbstractListScreen;
import src.models.datastructures.list.MyArrayList;

public class ArrayListScreen extends AbstractListScreen {

    @Override
    public void createList() {
        list = new MyArrayList<>();
        list.add(new ArrayListPanelNode(list.size(), 1));
        list.add(new ArrayListPanelNode(list.size(), 0));
        list.add(new ArrayListPanelNode(list.size(), 4));
    }

    @Override
    public void createViewAction() {
        viewAction = new ViewArrayListAction(this);
        add(viewAction);
    }
}
