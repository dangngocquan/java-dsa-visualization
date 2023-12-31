package src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.actionanimation;

import src.Config;
import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.list.arraylist.ViewArrayListAction;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.SortedArrayPriorityQueueScreen;
import src.models.datastructures.priorityqueue.EntryInterface;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.Iterator;

public class SortedArrayPriorityQueueActionRemoveMin extends AbstractPriorityQueueAnimation {
    private AbstractPanelPriorityQueueNode node;

    public SortedArrayPriorityQueueActionRemoveMin(
            AbstractPriorityQueueScreen rootScreen,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    public SortedArrayPriorityQueueScreen getRootScreen() {
        return (SortedArrayPriorityQueueScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            pickUpElement();
            animationStep++;
        } else if (animationStep <= getRootScreen().queue.size() - 1) {
            movePanelNodeToLeft(animationStep);
            animationStep++;
        } else if (animationStep == getRootScreen().queue.size()) {
            returnElement();
            animationStep++;
        } else if (animationStep == getRootScreen().queue.size() + 1) {
            solveEnd();
            animationStep = 100000;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void pickUpElement() {
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator
                = getRootScreen().queue.iterator();
        node = iterator.next().getValue();
        getRootScreen().setDescription(
                String.format(
                        "[REMOVE MIN] Remove this Entry(key=%d, value=%d)",
                        node.key, node.getValue()
                )
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
                        "[RETURN] Return Entry(key=%d, value=%d)",
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
}