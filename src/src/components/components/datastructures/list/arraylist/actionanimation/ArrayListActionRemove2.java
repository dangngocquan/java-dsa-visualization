package src.components.components.datastructures.list.arraylist.actionanimation;

import src.Config;
import src.components.components.datastructures.list.abstractlistscreen.AbstractListAnimation;
import src.components.components.datastructures.list.abstractlistscreen.AbstractListScreen;
import src.components.components.datastructures.list.abstractlistscreen.AbstractPanelListNode;
import src.components.components.datastructures.list.arraylist.ArrayListScreen;
import src.services.animation.Animation;
import src.services.animation.Location;

public class ArrayListActionRemove2 extends AbstractListAnimation {
    private int value;
    private int index;
    public ArrayListActionRemove2(
            int value, AbstractListScreen rootScreen,
            int period, AbstractListAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.value = value;
        this.index = -1;
    }

    public ArrayListScreen getRootScreen() {
        return (ArrayListScreen) rootScreen;
    }

    @Override
    public void run() {
        if (index < 0) {
            if (animationStep < getRootScreen().list.size() * 2) {
                if (animationStep % 2 == 0) {
                    checkElement(animationStep / 2);
                    animationStep++;
                } else {
                    if (getRootScreen().list.get(animationStep / 2).getValue() == value) {
                        index = animationStep / 2;
                        flagElement(index);
                        animationStep = 0;
                    } else {
                        uncheckElement(animationStep / 2);
                        animationStep++;
                    }
                }
            } else {
                animationStep = 0;
                end();
            }
        } else {
            end();
            getRootScreen().setEnabledAllButtons(false);
            new ArrayListActionRemove1(index, getRootScreen(), period, null).start();
        }
    }

    public void checkElement(int i) {
        AbstractPanelListNode node = getRootScreen().list.get(i);
        Animation.transitionColor(
                node,
                Config.COLOR_BAR_PLAIN,
                Config.COLOR_BAR_CHECKING,
                10,
                period - 10
        );
    }

    public void uncheckElement(int i) {
        AbstractPanelListNode node = getRootScreen().list.get(i);
        Animation.transitionColor(
                node,
                Config.COLOR_BAR_CHECKING,
                Config.COLOR_BAR_PLAIN,
                10,
                period - 10
        );
    }

    public void flagElement(int i) {
        AbstractPanelListNode node = getRootScreen().list.get(i);
        Animation.transitionColor(
                node,
                Config.COLOR_BAR_CHECKING,
                Config.COLOR_BAR_FLAG,
                10,
                period - 10
        );
    }
}
