package src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue.actionanimation;

import src.Config;
import src.components.components.datastructures.list.arraylist.ViewArrayListAction;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue.UnsortedArrayPriorityQueuePanelNode;
import src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue.UnsortedArrayPriorityQueueScreen;
import src.models.datastructures.priorityqueue.EntryInterface;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.Iterator;

public class UnsortedArrayPriorityQueueActionRemoveMin extends AbstractPriorityQueueAnimation {
    private AbstractPanelPriorityQueueNode node;
    private int index;

    public UnsortedArrayPriorityQueueActionRemoveMin(
            AbstractPriorityQueueScreen rootScreen,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator
                = getRootScreen().queue.iterator();
        int currentKey = iterator.next().getKey();
        int minKey = currentKey;
        int i = 0;
        while (iterator.hasNext()) {
            i++;
            currentKey = iterator.next().getKey();
            if (currentKey < minKey) {
                this.index = i;
                minKey = currentKey;
            }

        }
    }

    public UnsortedArrayPriorityQueueScreen getRootScreen() {
        return (UnsortedArrayPriorityQueueScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            pickUpElement(index);
            animationStep++;
        } else if (animationStep <= getRootScreen().queue.size() - 1 - index) {
            movePanelNodeToLeft(index + animationStep);
            animationStep++;
        } else if (animationStep == getRootScreen().queue.size() - index) {
            returnElement();
            animationStep++;
        } else if (animationStep == getRootScreen().queue.size() - index + 1) {
            solveEnd();
            animationStep = 100000;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void pickUpElement(int index) {
        node = getPanelNode(index);
        getRootScreen().setDescription(
                "[REMOVE MIN] Remove this element."
        );
        ServiceAnimation.transitionColor(
                node,
                Config.COLOR_WHITE,
                Config.COLOR_RED,
                10,
                period - 10
        );
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                0,
                ViewArrayListAction.GAP_Y + 20,
                10, period - 10
        );
    }

    public void movePanelNodeToLeft(int i) {
        int i1 = i;
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator
                = getRootScreen().queue.iterator();
        while (i-- > 0) iterator.next();
        AbstractPanelPriorityQueueNode node = iterator.next().getValue();
        getRootScreen().setDescription(
                String.format(
                        "[UPDATE] Update a[%d] := a[%d] = Entry(key=%d, value=%d)",
                        i1-1, i1, node.key, node.getValue()
                )
        );
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                -ViewArrayListAction.SIZE_PER_NODE - ViewArrayListAction.GAP_X,
                0,
                10,
                period - 10
        );
    }

    public void returnElement() {
        getRootScreen().setDescription(
                String.format(
                        "[RETURN] Return min entry Entry(key=%d, value=%d)",
                        node.key, node.getValue()
                )
        );
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                1500,
                0,
                10, period - 10
        );
    }

    public void solveEnd() {
        getRootScreen().viewAction.remove(node);
        getRootScreen().queue.removeMin();
    }

    public UnsortedArrayPriorityQueuePanelNode getPanelNode(int i) {
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator =
                getRootScreen().queue.iterator();
        AbstractPanelPriorityQueueNode panel = null;
        while (i-- >= 0) panel = iterator.next().getValue();
        return (UnsortedArrayPriorityQueuePanelNode) panel;
    }
}