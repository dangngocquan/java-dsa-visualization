package src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.actionanimation;

import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.SortedArrayPriorityQueuePanelNode;
import src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.SortedArrayPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.ViewSortedArrayPriorityQueueAction;
import src.models.datastructures.priorityqueue.EntryInterface;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.Iterator;

public class SortedArrayPriorityQueueActionInsert extends AbstractSortedArrayPriorityQueueAnimation {
    public int value;
    public int index;
    public int key;
    public int x;
    public int y;
    public SortedArrayPriorityQueuePanelNode panelNode;

    public SortedArrayPriorityQueueActionInsert(
            int key, int value, AbstractPriorityQueueScreen rootScreen,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        if (rootScreen.queue.isEmpty()) {
            this.index = 0;
        } else {
            Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator
                    = getRootScreen().queue.iterator();
            int currentKey = iterator.next().getKey();
            while (key >= currentKey) {
                this.index = this.index + 1;
                if (!iterator.hasNext()) break;
                currentKey = iterator.next().getKey();
            }
        }
        this.key = key;
        this.value = value;
        panelNode = new SortedArrayPriorityQueuePanelNode(index, key, value);
        x = ViewSortedArrayPriorityQueueAction.INITIAL_X
                + ViewSortedArrayPriorityQueueAction.GAP_X * (index + 1)
                + ViewSortedArrayPriorityQueueAction.SIZE_PER_NODE * index;
        y = ViewSortedArrayPriorityQueueAction.INITIAL_Y + 10;
        panelNode.setXY(
                x + 1500,
                y + ViewSortedArrayPriorityQueueAction.SIZE_PER_NODE + 10 + ViewSortedArrayPriorityQueueAction.GAP_Y
        );
        rootScreen.viewAction.add(panelNode);
    }

    public SortedArrayPriorityQueueScreen getRootScreen() {
        return (SortedArrayPriorityQueueScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            createNewElement();
            animationStep++;
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
                ViewSortedArrayPriorityQueueAction.SIZE_PER_NODE + ViewSortedArrayPriorityQueueAction.GAP_X,
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