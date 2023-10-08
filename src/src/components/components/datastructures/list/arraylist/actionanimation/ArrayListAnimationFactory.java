package src.components.components.datastructures.list.arraylist.actionanimation;

import src.components.components.datastructures.list.abstractlistscreen.AbstractListScreen;


public class ArrayListAnimationFactory {
    public static void actionAdd(int value, AbstractListScreen rootScreen, int period) {
        new ActionAdd1(value, rootScreen, period).start();
    }

    public static void actionEnlarge(AbstractListScreen rootScreen, int period) {
        new ActionEnlarge(rootScreen, period).start();
    }
}
