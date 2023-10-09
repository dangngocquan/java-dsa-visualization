package src.components.components.datastructures.list.arraylist.actionanimation;

import src.components.components.datastructures.list.abstractlistscreen.AbstractListAnimation;
import src.components.components.datastructures.list.abstractlistscreen.AbstractListScreen;

public class ArrayListActionRemove1 extends AbstractListAnimation {
    private int index;

    public ArrayListActionRemove1(
            int index, AbstractListScreen rootScreen,
            int period, AbstractListAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.index = index;
    }

    @Override
    public void run() {

    }
}
