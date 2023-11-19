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
            int period, AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
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
        } else if (animationStep <= getRootScreen().queue.size() - index) {
            movePanelNodeToRight(getRootScreen().queue.size() - animationStep);
            animationStep++;
        } else if (animationStep == getRootScreen().queue.size() - index + 1) {
            addToDataOfSortedArrayPriorityQueue();
            animationStep = 100000;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void createNewElement() {
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                -1500,
                0,
                10,
                period - 10
        );
    }

    public void movePanelNodeToRight(int i) {
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator
                = getRootScreen().queue.iterator();
        while (i-- > 0) iterator.next();
        AbstractPanelDataStructureNode node = iterator.next().getValue();
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                ViewUnsortedArrayPriorityQueueAction.SIZE_PER_NODE + ViewUnsortedArrayPriorityQueueAction.GAP_X,
                0,
                10,
                period - 10
        );
    }

    public void addToDataOfSortedArrayPriorityQueue() {
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