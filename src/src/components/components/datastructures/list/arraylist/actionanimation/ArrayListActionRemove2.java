package src.components.components.datastructures.list.arraylist.actionanimation;

import src.Config;
import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.list.AbstractListAnimation;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.arraylist.ArrayListScreen;
import src.services.ServiceAnimation;

public class ArrayListActionRemove2 extends AbstractArrayListAnimation {
    private final int value;
    private int index;
    public ArrayListActionRemove2(
            int value, AbstractListScreen rootScreen,
            AbstractListAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
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
            new ArrayListActionRemove1(index, getRootScreen(), null).start();
        }
    }

    public void checkElement(int i) {
        AbstractPanelDataStructureNode node = getRootScreen().list.get(i);
        getRootScreen().setDescription(
                String.format(
                        "[CHECK] Checking a[%d] = %d", i, node.getValue()
                        )
        );
        ServiceAnimation.transitionColor(
                node,
                Config.COLOR_WHITE,
                Config.COLOR_YELLOW,
                10,
                period - 10
        );
    }

    public void uncheckElement(int i) {
        AbstractPanelDataStructureNode node = getRootScreen().list.get(i);
        getRootScreen().setDescription(
                String.format(
                        "[CHECK] removeValue = %d is not equals to a[%d] = %d", value, i, node.getValue()
                )
        );
        ServiceAnimation.transitionColor(
                node,
                Config.COLOR_YELLOW,
                Config.COLOR_WHITE,
                10,
                period - 10
        );
    }

    public void flagElement(int i) {
        AbstractPanelDataStructureNode node = getRootScreen().list.get(i);
        getRootScreen().setDescription(
                String.format(
                        "[CHECK] removeValue = %d is equals to a[%d] = %d", value, i, node.getValue()
                )
        );
        ServiceAnimation.transitionColor(
                node,
                Config.COLOR_YELLOW,
                Config.COLOR_BAR_FLAG,
                10,
                period - 10
        );
    }
}
