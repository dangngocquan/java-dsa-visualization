package src.components.components.datastructures.tree.avltree;

import src.components.components.datastructures.tree.*;
import src.components.components.datastructures.tree.actionanimation.TreeActionInsert;
import src.models.datastructures.queue.LinkedQueue;
import src.models.datastructures.queue.QueueInterface;
import src.models.datastructures.stack.LinkedStack;
import src.models.datastructures.stack.StackInterface;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Stack;

public class AVLTreeActionInsert extends TreeActionInsert {
    public int stepBalance = 0;
    public QueueInterface<Integer> queueRootBalance = new LinkedQueue<>();

    public AVLTreeActionInsert(
            int value,
            AbstractTreeScreen rootScreen,
            int period,
            AbstractTreeAnimation nextAnimation) {
        super(value, rootScreen, period, nextAnimation);
    }

    @Override
    public void run() {
        AbstractViewTreeAction.movableArrow = false;
        if (animationStep == 0) {
            if (i < panels.length) {
                if (panels[i] == null) {
                    newRootPanel();
                    animationStep = 3;
                } else {
                    checkNode();
                }
            } else {
                getRootScreen().setDescription(
                        "[INSERT] Finished"
                );
                end();
                getRootScreen().tree.insert(panelInsert);
                getViewAction().resetPanelsClone();
            }
        } else if (animationStep == 1) {
            insert();
            if (animationStep == 2 && i > -1) queueRootBalance.enqueue(i);
        } else if (animationStep == 2) {
            balance();
        } else {
            getRootScreen().setDescription(
                    "[INSERT] Finished"
            );
            end();
            getRootScreen().tree.insert(panelInsert);
            if (panelInsert == null && i != -10) new ViewTreeController.DialogNotifyMaxHeight();
            getViewAction().resetPanelsClone();
        }
    }

    protected void balance() {
        if (stepBalance == 0) {
            if (!queueRootBalance.isEmpty()) {
                i = queueRootBalance.dequeue();
                getRootScreen().setDescription("[BALANCE] Check sub tree with root " + panels[i].getValue());
                yellowFlag();
                stepBalance = 1;
                return;
            }
            animationStep = 3;
        } else if (stepBalance == 1) {
            whiteFlag();
            int heightL = height(2 * i + 1);
            int heightR = height(2 * i + 2);
            if (heightL - heightR > 1) {
                int heightLL = height((2 * i + 1) * 2 + 1);
                int heightLR = height((2 * i + 1) * 2 + 2);
                if (heightLR > heightLL) {
                    stepBalance = 2;
                    return;
                }
                stepBalance = 3;
            } else if (heightR - heightL > 1) {
                int heightRL = height((2 * i + 2) * 2 + 1);
                int heightRR = height((2 * i + 2) * 2 + 2);
                if (heightRL > heightRR) {
                    stepBalance = 4;
                    return;
                }
                stepBalance = 5;
            } else {
                if (i != 0) queueRootBalance.enqueue((i-1) / 2);
                stepBalance = 0;
            }
        } else if(stepBalance == 2) {
            getRootScreen().setDescription("[BALANCE] Sub left tree - rotate left");
            rotateLeft(2 * i + 1);
            stepBalance = 3;
        } else if (stepBalance == 3) {
            getRootScreen().setDescription("[BALANCE] Current tree - rotate right");
            rotateRight(i);
            if (i != 0) queueRootBalance.enqueue((i-1) / 2);
            stepBalance = 0;
        }else if (stepBalance == 4) {
            getRootScreen().setDescription("[BALANCE] Sub right tree - rotate right");
            rotateRight(2 * i + 2);
            stepBalance = 5;
        } else if (stepBalance == 5) {
            getRootScreen().setDescription("[BALANCE] current tree - rotate left");
            rotateLeft(i);
            if (i != 0) queueRootBalance.enqueue((i-1) / 2);
            stepBalance = 0;
        }
    }

    protected void rotateLeft(int j) {
        int i0 = i;
        i = j;
        translateRootBecomeLeftTree();
        translateLeftOfTreeRightToRightOfTreeLeft();
        translateRightTreeBecomeRoot();
        i = i0;
    }

    protected void rotateRight(int j) {
        int i0 = i;
        i = j;
        translateRootBecomeRightTree();
        translateRightOfTreeLeftToLeftOfTreeRight();
        translateLeftTreeBecomeRoot();
        i = i0;
    }

    protected void translateRootBecomeLeftTree() {
        translateRootBecomeSubTree(1);
    }

    protected void translateRootBecomeRightTree() {
        translateRootBecomeSubTree(2);
    }

    protected void translateRootBecomeSubTree(int type) {
        // Solve subtree
        translateTree(2 * i + type, (2 * i + type) * 2 + type);
        // solve root
        translateNode(i, 2 * i + type);
    }

    protected void translateLeftOfTreeRightToRightOfTreeLeft() {
        translateTree((2 * i + 2) * 2 + 1, (2 * i + 1) * 2 + 2);
    }

    protected void translateRightOfTreeLeftToLeftOfTreeRight() {
        translateTree((2 * i + 1) * 2 + 2, (2 * i + 2) * 2 + 1);
    }

    protected void translateLeftTreeBecomeRoot() {
        translateSubTreeBecomeRoot(1);
    }

    protected void translateRightTreeBecomeRoot() {
        translateSubTreeBecomeRoot(2);
    }

    // type == 1: tree left
    // type == 2: tree right
    protected void translateSubTreeBecomeRoot(int type) {
        translateTree(2 * i + type, i);
    }

    protected int height(int indexRoot) {
        if (indexRoot >= panels.length || panels[indexRoot] == null) return 0;
        return 1 + Math.max(
                height(indexRoot * 2 + 1),
                height(indexRoot * 2 + 2)
        );
    }

    protected void translateNode(int i1, int i2) {
        AbstractViewTreeAction.movableArrow = true;
        panels[i2] = panels[i1];
        panels[i1] = null;
        ServiceAnimation.translate(
                panels[i2],
                new Location(panels[i2].getX(), panels[i2].getY()),
                AbstractViewTreeAction.getDefaultX(i2) - panels[i2].getX(),
                AbstractViewTreeAction.getDefaultY(i2) - panels[i2].getY(),
                10,
                period - 10
        );
    }

    protected void translateTree(int i1, int i2) {
        AbstractViewTreeAction.movableArrow = true;
        Map<Integer, Integer> newIndexes = new LinkedHashMap<>();
        QueueInterface<Integer> queueOldIndexes = new LinkedQueue<>();
        if (i1 < panels.length && panels[i1] != null) {
            queueOldIndexes.enqueue(i1);
            newIndexes.put(i1, i2);
        }
        while (!queueOldIndexes.isEmpty()) {
            Integer iRoot = queueOldIndexes.dequeue();
            if (hasChildLeft(iRoot)) {
                queueOldIndexes.enqueue(2 * iRoot + 1);
                newIndexes.put(2 * iRoot + 1, newIndexes.get(iRoot) * 2 + 1);
            }
            if (hasChildRight(iRoot)) {
                queueOldIndexes.enqueue(2 * iRoot + 2);
                newIndexes.put(2 * iRoot + 2, newIndexes.get(iRoot) * 2 + 2);
            }
        }
        Map<Integer, TreePanelNode> nodes = new LinkedHashMap<>();
        for (Integer i : newIndexes.keySet()) {
            nodes.put(newIndexes.get(i), panels[i]);
        }

        for (Integer i : newIndexes.keySet()) panels[i] = null;
        for (Integer i : newIndexes.keySet()) {
            panels[newIndexes.get(i)] = nodes.get(newIndexes.get(i));
        }

        getViewAction().drawElements();
        getViewAction().repaint();

        for (Integer i : newIndexes.keySet()) {
            ServiceAnimation.translate(
                    panels[newIndexes.get(i)],
                    new Location(panels[newIndexes.get(i)].getX(), panels[newIndexes.get(i)].getY()),
                    AbstractViewTreeAction.getDefaultX(newIndexes.get(i)) - panels[newIndexes.get(i)].getX(),
                    AbstractViewTreeAction.getDefaultY(newIndexes.get(i)) - panels[newIndexes.get(i)].getY(),
                    10,
                    period - 10
            );
        }
    }
}
