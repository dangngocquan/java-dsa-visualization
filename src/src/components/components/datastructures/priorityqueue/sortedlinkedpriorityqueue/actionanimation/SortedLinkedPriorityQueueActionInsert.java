package src.components.components.datastructures.priorityqueue.sortedlinkedpriorityqueue.actionanimation;

import src.Config;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueAnimation;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.sortedlinkedpriorityqueue.SortedLinkedPriorityQueuePanelNode;
import src.components.components.datastructures.priorityqueue.sortedlinkedpriorityqueue.SortedLinkedPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.sortedlinkedpriorityqueue.ViewSortedLinkedPriorityQueueAction;
import src.models.datastructures.priorityqueue.EntryInterface;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.Iterator;

public class SortedLinkedPriorityQueueActionInsert extends AbstractSortedLinkedPriorityQueueAnimation {
    public int value;
    public int key;
    public int index;
    public int x;
    public int y;
    public SortedLinkedPriorityQueuePanelNode panelNode;
    public SortedLinkedPriorityQueuePanelNode prevNode;
    public SortedLinkedPriorityQueuePanelNode nextNode;

    public SortedLinkedPriorityQueueActionInsert(
            int key, int value, AbstractPriorityQueueScreen rootScreen,
            int period, AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
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
        this.value = value;
        this.key = key;
        panelNode = new SortedLinkedPriorityQueuePanelNode(index, key, value);
        x = ViewSortedLinkedPriorityQueueAction.INITIAL_X
                + ViewSortedLinkedPriorityQueueAction.GAP_X * index
                + ViewSortedLinkedPriorityQueueAction.WIDTH_NODE * index;
        y = ViewSortedLinkedPriorityQueueAction.INITIAL_Y;
        panelNode.setXY(
                x + 1500,
                y + ViewSortedLinkedPriorityQueueAction.HEIGHT_NODE + ViewSortedLinkedPriorityQueueAction.GAP_Y
        );
        rootScreen.viewAction.add(panelNode);
    }

    public SortedLinkedPriorityQueueScreen getRootScreen() {
        return (SortedLinkedPriorityQueueScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            createNewElement();
            animationStep = 1;
        } else if (animationStep == 1) {
            createArrowWithPrevNode();
            animationStep = 2;
        } else if (animationStep == 2) {
            createArrowWithNextNode();
            animationStep++;
        } else if (animationStep == 3) {
            addToDataOfArrayList();
            animationStep++;
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
        for (int i = index; i < getRootScreen().queue.size(); i++) {
            AbstractPanelPriorityQueueNode node = getPanelNode(i);
            ServiceAnimation.translate(
                    node,
                    new Location(node.getX(), node.getY()),
                    ViewSortedLinkedPriorityQueueAction.GAP_X + ViewSortedLinkedPriorityQueueAction.WIDTH_NODE,
                    0,
                    10, period - 10
            );
            if (node.nextArrow != null) {
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        node.nextArrow,
                        new int[] {
                                node.nextArrow[0]
                                        + ViewSortedLinkedPriorityQueueAction.GAP_X
                                        + ViewSortedLinkedPriorityQueueAction.WIDTH_NODE,
                                node.nextArrow[1],
                                node.nextArrow[2]
                                        + ViewSortedLinkedPriorityQueueAction.GAP_X
                                        + ViewSortedLinkedPriorityQueueAction.WIDTH_NODE,
                                node.nextArrow[3]
                        },
                        10,
                        period - 10

                );
            }
        }
        if (index > 0 && index < getRootScreen().queue.size()) {
            prevNode = getPanelNode(index - 1);
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevNode.nextArrow,
                    new int[] {
                            prevNode.nextArrow[0],
                            prevNode.nextArrow[1],
                            prevNode.nextArrow[2]
                                    + ViewSortedLinkedPriorityQueueAction.GAP_X
                                    + ViewSortedLinkedPriorityQueueAction.WIDTH_NODE,
                            prevNode.nextArrow[3]
                    },
                    10,
                    period - 10

            );
        }
    }

    public void createArrowWithPrevNode() {
        getRootScreen().queue.insert(key, panelNode);
        if (index > 0) {
            prevNode = getPanelNode(index-1);
        }
        if (prevNode == null) return;
        if (prevNode.nextArrow == null) prevNode.setNextArrow(prevNode.getDefaultNextArrow());
        ServiceAnimation.transformArrowPanelNode(
                getRootScreen().viewAction,
                prevNode.nextArrow,
                new int[] {
                        prevNode.nextArrow[0],
                        prevNode.nextArrow[1],
                        panelNode.getX(),
                        panelNode.getY() + panelNode.getHeightPanel()/2
                },
                10,
                period - 10 + 1 - 1
        );
        ServiceAnimation.transitionColor(
                prevNode.panels[2],
                prevNode.panels[2].getBackgroundColor(),
                Config.COLOR_GREEN,
                10, period - 10
        );
        ServiceAnimation.transitionBorderColor1(
                panelNode,
                getRootScreen(),
                panelNode.getBorderColor(),
                Config.COLOR_GREEN,
                10, period - 10
        );

    }

    public void createArrowWithNextNode() {
        if (prevNode != null) {
            ServiceAnimation.transitionColor(
                    prevNode.panels[2],
                    prevNode.panels[2].getBackgroundColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    panelNode,
                    getRootScreen(),
                    panelNode.getBorderColor(),
                    Config.COLOR_BLUE,
                    10, period - 10 + 1 - 1
            );
        }

        panelNode.setVisible(false);
        if (index + 1 < getRootScreen().queue.size()) {
            nextNode = getPanelNode(index + 1);
        }
        if (nextNode != null) {
            panelNode.setNextArrow(panelNode.getDefaultNextArrow());
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    panelNode.nextArrow,
                    new int[] {
                            panelNode.nextArrow[0],
                            panelNode.nextArrow[1],
                            nextNode.getX(),
                            nextNode.getY() + nextNode.getHeightPanel()/2
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    panelNode.panels[2],
                    panelNode.panels[2].getBackgroundColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    nextNode,
                    getRootScreen(),
                    nextNode.getBorderColor(),
                    Config.COLOR_GREEN,
                    10, period - 10
            );
        }
    }

    public void addToDataOfArrayList() {
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                0,
                y - panelNode.getY(),
                10,
                period - 10
        );
        if (prevNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevNode.nextArrow,
                    new int[] {
                            prevNode.nextArrow[0],
                            prevNode.nextArrow[1],
                            prevNode.nextArrow[2],
                            prevNode.getDefaultNextArrow()[3]
                    },
                    10,
                    period - 10 + 1 - 1
            );
        }
        if (nextNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    panelNode.nextArrow,
                    new int[] {
                            panelNode.nextArrow[0],
                            ViewSortedLinkedPriorityQueueAction.INITIAL_Y
                                    + panelNode.panels[2].getY()
                                    + panelNode.panels[2].getHeightPanel()/2,
                            panelNode.nextArrow[2],
                            panelNode.nextArrow[3]
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    panelNode.panels[2],
                    panelNode.panels[2].getBackgroundColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    nextNode,
                    getRootScreen(),
                    nextNode.getBorderColor(),
                    Config.COLOR_BLUE,
                    10, period - 10
            );
        }
    }

    public SortedLinkedPriorityQueuePanelNode getPanelNode(int i) {
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator =
                getRootScreen().queue.iterator();
        AbstractPanelPriorityQueueNode panel = null;
        while (i-- >= 0) panel = iterator.next().getValue();
        return (SortedLinkedPriorityQueuePanelNode) panel;
    }
}