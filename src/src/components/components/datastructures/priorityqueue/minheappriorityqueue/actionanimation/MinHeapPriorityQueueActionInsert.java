package src.components.components.datastructures.priorityqueue.minheappriorityqueue.actionanimation;

import src.Config;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.minheappriorityqueue.MinHeapPriorityQueuePanelNode;
import src.components.components.datastructures.priorityqueue.minheappriorityqueue.MinHeapPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.minheappriorityqueue.ViewMinHeapPriorityQueueAction;
import src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.actionanimation.AbstractSortedArrayPriorityQueueAnimation;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.awt.*;

public class MinHeapPriorityQueueActionInsert extends AbstractSortedArrayPriorityQueueAnimation {
    public int key;
    public int value;
    public MinHeapPriorityQueuePanelNode[] data;
    public int i;

    public MinHeapPriorityQueueActionInsert(
            int key, int value,
            AbstractPriorityQueueScreen rootScreen,
            int period,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.key = key;
        this.value = value;
        data = ((ViewMinHeapPriorityQueueAction) getRootScreen().getViewAction()).dataClone;
        i = getRootScreen().queue.size();
        data[i] = new MinHeapPriorityQueuePanelNode(
                MinHeapPriorityQueueScreen.indexRows[i],
                MinHeapPriorityQueueScreen.indexColumns[i],
                key, value
        );
        data[i].setX(data[i].getX() + 1500);
        getRootScreen().getViewAction().add(data[i]);
        getRootScreen().getViewAction().repaint();
    }

    public MinHeapPriorityQueueScreen getRootScreen() {
        return (MinHeapPriorityQueueScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            createNewElement();
            animationStep = 1;
        } else if (animationStep == 1) {
            if (i == 0) {
                check(i, Config.COLOR_WHITE);
                animationStep = 5;
            } else {
                check((i-1) / 2, Config.COLOR_YELLOW);
                animationStep = 2;
            }
        } else if (animationStep == 2) {
            upHeap();
        } else if (animationStep == 3) {
            check(i, Config.COLOR_WHITE);
            i = (i - 1) / 2;
            animationStep = 1;
        } else if (animationStep == 4) {
            check(i, Config.COLOR_WHITE);
            animationStep = 5;
        } else if (animationStep == 5) {
            getRootScreen().queue.insert(key, data[i]);
            ((ViewMinHeapPriorityQueueAction) getRootScreen().getViewAction()).resetDataClone();
            end();
        }
    }

    public void createNewElement() {
        ServiceAnimation.translate(
                data[i],
                new Location(data[i].getX(), data[i].getY()),
                -1500,
                0,
                10,
                period - 10
        );
        check(i, Config.COLOR_BLUE);
    }

    public void check(int i1, Color color) {
        ServiceAnimation.transitionColor(
                data[i1],
                data[i1].getBackgroundColor(),
                color,
                10, period - 10
        );
    }

    public void upHeap() {
        if (i > 0 && data[i].key < data[(i - 1) / 2].key) {
            swap(i, (i - 1) / 2);
            animationStep = 3;
        } else {
            check((i - 1) / 2, Config.COLOR_WHITE);
            animationStep = 4;
        }
    }

    public void swap(int i1, int i2) {
        int x1 = data[i1].getX();
        int y1 = data[i1].getY();
        int x2 = data[i2].getX();
        int y2 = data[i2].getY();
        ServiceAnimation.translate(
                data[i1],
                new Location(x1, y1),
                x2 - x1,
                y2 - y1,
                10,
                period - 10
        );
        ServiceAnimation.translate(
                data[i2],
                new Location(x2, y2),
                x1 - x2,
                y1 - y2,
                10,
                period - 10
        );

        MinHeapPriorityQueuePanelNode temp = data[i1];
        data[i1] = data[i2];
        data[i2] = temp;
    }
}
