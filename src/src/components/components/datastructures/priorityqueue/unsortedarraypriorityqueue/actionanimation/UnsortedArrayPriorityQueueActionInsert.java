package src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue.actionanimation;

import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue.UnsortedArrayPriorityQueuePanelNode;
import src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue.UnsortedArrayPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue.ViewUnsortedArrayPriorityQueueAction;
import src.models.datastructures.priorityqueue.EntryInterface;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.Iterator;

public class UnsortedArrayPriorityQueueActionInsert extends AbstractUnsortedArrayPriorityQueueAnimation {
    public int value;
    public int index;
    public int key;
    public int x;
    public int y;
    public UnsortedArrayPriorityQueuePanelNode panelNode;

    public UnsortedArrayPriorityQueueActionInsert(
            int key, int value, AbstractPriorityQueueScreen rootScreen,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        this.index = getRootScreen().queue.size();
        this.key = key;
        this.value = value;
        panelNode = new UnsortedArrayPriorityQueuePanelNode(index, key, value);
        x = ViewUnsortedArrayPriorityQueueAction.INITIAL_X
                + ViewUnsortedArrayPriorityQueueAction.GAP_X * (index + 1)
                + ViewUnsortedArrayPriorityQueueAction.SIZE_PER_NODE * index;
        y = ViewUnsortedArrayPriorityQueueAction.INITIAL_Y + 10;
        panelNode.setXY(
                x + 1500,
                y + ViewUnsortedArrayPriorityQueueAction.SIZE_PER_NODE + 10 + ViewUnsortedArrayPriorityQueueAction.GAP_Y
        );
        rootScreen.viewAction.add(panelNode);
    }

    public UnsortedArrayPriorityQueueScreen getRootScreen() {
        return (UnsortedArrayPriorityQueueScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            createNewElement();
            animationStep = 1;
        } else if (animationStep == 1) {
            addToDataOfSortedArrayPriorityQueue();
            animationStep = 2;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void createNewElement() {
        getRootScreen().setDescription(
                String.format(
                        "[CREATE] Create new Entry(key=%d, value=%d)",
                        key, value
                )
        );
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                -1500,
                0,
                10,
                period - 10
        );
    }

    public void addToDataOfSortedArrayPriorityQueue() {
        getRootScreen().setDescription(
                String.format(
                        "[INSERT] Inserted element %d to Priority Queue", value
                )
        );
        rootScreen.viewAction.setComponentZOrder(panelNode, 0);
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                0,
                y -panelNode.getY(),
                10,
                period - 10
        );
        getRootScreen().queue.insert(key, panelNode);
    }
}