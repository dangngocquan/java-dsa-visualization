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

public class SortedLinkedPriorityQueueActionRemoveMin extends AbstractSortedLinkedPriorityQueueAnimation {
    private final int index;
    private SortedLinkedPriorityQueuePanelNode node;
    private SortedLinkedPriorityQueuePanelNode prevNode;
    private SortedLinkedPriorityQueuePanelNode nextNode;

    public SortedLinkedPriorityQueueActionRemoveMin(
            AbstractPriorityQueueScreen rootScreen, int period,
            AbstractPriorityQueueAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.index = 0;
    }

    public SortedLinkedPriorityQueueScreen getRootScreen() {
        return (SortedLinkedPriorityQueueScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep <= index * 2) {
            if (animationStep % 2 == 0) {
                checkNode(animationStep / 2);
            } else {
                uncheckNode(animationStep/2);
            }
            animationStep += 1;
        } else if (animationStep == index * 2 + 1) {
            pickUpElement(index);
            animationStep += 1;
        } else if (animationStep == index * 2 + 2) {
            createArrowFromPrevToNext();
            animationStep += 1;
        } else if (animationStep == index * 2 + 3) {
            returnElement();
            animationStep++;
        } else if (animationStep == index * 2 + 4) {
            solveEnd();
            animationStep++;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void checkNode(int i) {
        SortedLinkedPriorityQueuePanelNode node0 = getPanelNode(i);
        ServiceAnimation.transitionColor(
                node0,
                Config.COLOR_WHITE,
                Config.COLOR_YELLOW,
                10,
                period - 10
        );
        ServiceAnimation.transitionBorderColor1(
                node0,
                getRootScreen().viewAction,
                Config.COLOR_BLUE,
                Config.COLOR_YELLOW,
                10,
                period - 10
        );
    }

    public void uncheckNode(int i) {
        SortedLinkedPriorityQueuePanelNode node0 = getPanelNode(i);
        ServiceAnimation.transitionColor(
                node0,
                Config.COLOR_YELLOW,
                Config.COLOR_WHITE,
                10,
                period - 10
        );
        ServiceAnimation.transitionBorderColor1(
                node0,
                getRootScreen().viewAction,
                Config.COLOR_YELLOW,
                Config.COLOR_BLUE,
                10,
                period - 10
        );
    }

    public void pickUpElement(int i) {
        node = getPanelNode(i);
        if (i-1 >= 0) {
            prevNode = getPanelNode(i-1);
        }
        if (i+1 < getRootScreen().queue.size()) {
            nextNode = getPanelNode(i+1);
        }
        // Move node
        ServiceAnimation.transitionColor(
                node,
                Config.COLOR_YELLOW,
                Config.COLOR_RED,
                10,
                period - 10
        );
        ServiceAnimation.transitionBorderColor1(
                node,
                getRootScreen().viewAction,
                Config.COLOR_YELLOW,
                Config.COLOR_BLUE,
                10,
                period - 10
        );
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                0,
                ViewSortedLinkedPriorityQueueAction.GAP_Y,
                10, period - 10
        );
        // Move arrow
        if (prevNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevNode.nextArrow,
                    new int[] {
                            prevNode.nextArrow[0],
                            prevNode.nextArrow[1],
                            prevNode.nextArrow[2],
                            prevNode.nextArrow[3] + ViewSortedLinkedPriorityQueueAction.GAP_Y
                    },
                    10,
                    period - 10
            );
        }

        if (nextNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    node.nextArrow,
                    new int[] {
                            node.nextArrow[0],
                            node.nextArrow[1] + ViewSortedLinkedPriorityQueueAction.GAP_Y,
                            node.nextArrow[2],
                            node.nextArrow[3]
                    },
                    10,
                    period - 10
            );
        }

    }

    public void createArrowFromPrevToNext() {
        if (prevNode != null && nextNode != null) {
            ServiceAnimation.transformArrowPanelNode(
                    getRootScreen().viewAction,
                    prevNode.nextArrow,
                    new int[] {
                            prevNode.nextArrow[0],
                            prevNode.nextArrow[1],
                            prevNode.nextArrow[2]
                                    + ViewSortedLinkedPriorityQueueAction.GAP_X
                                    + ViewSortedLinkedPriorityQueueAction.WIDTH_NODE,
                            prevNode.nextArrow[3] - ViewSortedLinkedPriorityQueueAction.GAP_Y
                    },
                    10,
                    period - 10
            );
            ServiceAnimation.transitionColor(
                    prevNode.panels[1],
                    Config.COLOR_BLUE,
                    Config.COLOR_GREEN,
                    10,
                    period - 10
            );
            ServiceAnimation.transitionBorderColor1(
                    nextNode,
                    getRootScreen().viewAction,
                    Config.COLOR_BLUE,
                    Config.COLOR_GREEN,
                    10,
                    period - 10
            );
        } else if (prevNode != null) {
            prevNode.nextArrow = null;
        } else if (nextNode != null) {
            node.nextArrow = null;
        }
    }

    public void returnElement() {
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                1500,
                0,
                10, period - 10
        );
        node.nextArrow = null;
        for (int i = index+1; i < getRootScreen().queue.size(); i++) {
            SortedLinkedPriorityQueuePanelNode node = getPanelNode(i);
            ServiceAnimation.translate(
                    node,
                    new Location(node.getX(), node.getY()),
                    -ViewSortedLinkedPriorityQueueAction.GAP_X - ViewSortedLinkedPriorityQueueAction.WIDTH_NODE,
                    0,
                    10, period - 10
            );
            if (node.nextArrow != null) {
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        node.nextArrow,
                        new int[] {
                                node.nextArrow[0]
                                        - ViewSortedLinkedPriorityQueueAction.GAP_X
                                        - ViewSortedLinkedPriorityQueueAction.WIDTH_NODE,
                                node.nextArrow[1],
                                node.nextArrow[2]
                                        - ViewSortedLinkedPriorityQueueAction.GAP_X
                                        - ViewSortedLinkedPriorityQueueAction.WIDTH_NODE,
                                node.nextArrow[3]
                        },
                        10,
                        period - 10

                );
            }
        }
        if (prevNode != null) {
            if (nextNode != null) {
                ServiceAnimation.transitionColor(
                        prevNode.panels[1],
                        Config.COLOR_GREEN,
                        Config.COLOR_BLUE,
                        10,
                        period - 10
                );
                ServiceAnimation.transitionBorderColor1(
                        nextNode,
                        getRootScreen().viewAction,
                        Config.COLOR_GREEN,
                        Config.COLOR_BLUE,
                        10,
                        period - 10
                );
                ServiceAnimation.transformArrowPanelNode(
                        getRootScreen().viewAction,
                        prevNode.nextArrow,
                        new int[] {
                                prevNode.nextArrow[0],
                                prevNode.nextArrow[1],
                                prevNode.nextArrow[2]
                                        - ViewSortedLinkedPriorityQueueAction.GAP_X
                                        - ViewSortedLinkedPriorityQueueAction.WIDTH_NODE,
                                prevNode.nextArrow[3]
                        },
                        10,
                        period - 10

                );
            } else {
                prevNode.nextArrow = null;
            }
        }
    }

    public void solveEnd() {
        getRootScreen().viewAction.remove(node);
        getRootScreen().queue.removeMin();
    }

    public SortedLinkedPriorityQueuePanelNode getPanelNode(int i) {
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator =
                getRootScreen().queue.iterator();
        AbstractPanelPriorityQueueNode panel = null;
        while (i-- >= 0) panel = iterator.next().getValue();
        return (SortedLinkedPriorityQueuePanelNode) panel;
    }
}