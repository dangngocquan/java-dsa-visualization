package src.components.components.datastructures.priorityqueue.minheappriorityqueue.actionanimation;

import src.Config;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.minheappriorityqueue.MinHeapPriorityQueuePanelNode;
import src.components.components.datastructures.priorityqueue.minheappriorityqueue.ViewMinHeapPriorityQueueAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.awt.*;

public class MinHeapPriorityQueueActionRemoveMin extends AbstractMinHeapPriorityQueueAnimation {
    public MinHeapPriorityQueuePanelNode[] data;
    public int i;
    public int subAnimationStep;

    public MinHeapPriorityQueueActionRemoveMin(
            AbstractPriorityQueueScreen rootScreen,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        data = ((ViewMinHeapPriorityQueueAction) getRootScreen().getViewAction()).dataClone;
        i = 0;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            swap(i, getRootScreen().queue.size() - 1);
            animationStep = 1;
        } else if (animationStep == 1) {
            i = getRootScreen().queue.size() - 1;
            removeMin();
            i = 0;
            animationStep = 3;
        } else if (animationStep == 3) {
            check(i, Config.COLOR_BLUE);
            animationStep = 4;
        } else if (animationStep == 4) {
            downHeap();
        } else if (animationStep == 5) {
            check(i, Config.COLOR_WHITE);
            animationStep = 6;
        } else if (animationStep == 6) {
            getRootScreen().queue.removeMin();
            ((ViewMinHeapPriorityQueueAction) getRootScreen().getViewAction()).resetDataClone();
            end();
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
                period - 10 + 1 - 1
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

    public void check(int i1, Color color) {
        if (data[i1] == null) return;
        ServiceAnimation.transitionColor(
                data[i1],
                data[i1].getBackgroundColor(),
                color,
                10, period - 10
        );
    }

    public void removeMin() {
        ServiceAnimation.translate(
                data[i],
                new Location(data[i].getX(), data[i].getY()),
                1500,
                0,
                10,
                period - 10
        );
        data[i] = null;
    }

    public void downHeap() {
        if (subAnimationStep == 0) {
            if (2 * i + 1 >= 15 || data[2 * i + 1] == null) { // has 0 child
                check(i, Config.COLOR_WHITE);
                animationStep = 6;
                return;
            }
            if (2 * i + 2 >= 15 || data[2 * i + 2] == null) { // has 1 child
                check(2 * i + 1, Config.COLOR_YELLOW);
                subAnimationStep = 1;
                return;
            }
            // has 2 children
            check(2 * i + 1, Config.COLOR_YELLOW);
            check(2 * i + 2, Config.COLOR_YELLOW);
            subAnimationStep = 2;
        } else if (subAnimationStep == 1) { // case 1 child continue
            if (data[i].key < data[2 * i + 1].key) {
                check(2 * i + 1, Config.COLOR_WHITE);
                subAnimationStep = 0;
                animationStep = 5;
            } else {
                swap(i, 2 * i + 1);
                subAnimationStep = 3;
            }
        } else if (subAnimationStep == 2) { // case 2 children continue
            if (data[i].key < data[2 * i + 1].key && data[i].key < data[2 * i + 2].key) {
                check(2 * i + 1, Config.COLOR_WHITE);
                check(2 * i + 2, Config.COLOR_WHITE);
                animationStep = 5;
                subAnimationStep = 0;
            } else {
                if (data[2 * i + 1].key <= data[2 * i + 2].key) {
                    check(2 * i + 2, Config.COLOR_WHITE);
                    swap(i, 2 * i + 1);
                    subAnimationStep = 4;
                } else {
                    check(2 * i + 1, Config.COLOR_WHITE);
                    swap(i, 2 * i + 2);
                    subAnimationStep = 5;
                }
            }
        } else if (subAnimationStep == 3) {
            check(i, Config.COLOR_WHITE);
            i = 2 * i + 1;
            subAnimationStep = 0;
        } else if (subAnimationStep == 4) { // case 2 children continue, left <= right
            check(i, Config.COLOR_WHITE);
            i = 2 * i + 1;
            subAnimationStep = 0;
        } else if (subAnimationStep == 5) { // case 2 children continue, left > right
            check(i, Config.COLOR_WHITE);
            i = 2 * i + 2;
            subAnimationStep = 0;
        }
    }
}
