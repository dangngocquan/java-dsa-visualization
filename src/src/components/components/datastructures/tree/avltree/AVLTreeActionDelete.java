package src.components.components.datastructures.tree.avltree;

import src.components.components.datastructures.tree.AbstractTreeAnimation;
import src.components.components.datastructures.tree.AbstractTreeScreen;
import src.components.components.datastructures.tree.AbstractViewTreeAction;
import src.components.components.datastructures.tree.TreePanelNode;
import src.components.components.datastructures.tree.actionanimation.TreeActionDelete;
import src.models.datastructures.queue.LinkedQueue;
import src.models.datastructures.queue.QueueInterface;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.LinkedHashMap;
import java.util.Map;

public class AVLTreeActionDelete extends TreeActionDelete {
    public int indexCheckBalance = -1;
    public int stepBalance = 0;

    public AVLTreeActionDelete(
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
                checkNode();
            } else {
                getRootScreen().setDescription("[DELETE] Finished.");
                end();
                getRootScreen().tree.delete(panelRemove);
                getViewAction().resetPanelsClone();
            }
        } else if (animationStep == 1) {
            delete();
        } else if (animationStep == 2) {
            getRootScreen().getViewAction().drawElements();
            getRootScreen().repaint();
            balance();
        } else {
            getRootScreen().setDescription("[DELETE] Finished.");
            end();
            getRootScreen().tree.delete(panelRemove);
            getViewAction().resetPanelsClone();
        }
    }

    @Override
    public void delete() {
        int compare = 0;
        if (panels[i] != null) compare = value - panels[i].getValue();
        if (!hasChildLeft(i) && !hasChildRight(i)) { // case 1: has none child
            if (compare == 0) {
                if (subStep == 0) {
                    redFlag();
                    subStep = 1;
                    if (panelRemove == null) panelRemove = panels[i];
                } else if (subStep == 1) {
                    if (prevRoot == -1) {
                        removePanelFromView();
                    } else {
                        translatePanelToPrevRoot();
                    }
                    subStep = 2;
                    indexCheckBalance = i == 0? -1 : (i - 1) / 2;
                } else if (subStep == 2) {
                    if (prevRoot > -1) panels[prevRoot] = panels[i];
                    panels[i] = null;
                    prevRoot = i;
                    subStep = 0;
                    animationStep = 2;
                    indexCheckBalance = i == 0? -1 : (i - 1) / 2;
                }
            } else {
                whiteFlag();
                animationStep = 2;
            }
        } else if (hasChildLeft(i) && !hasChildRight(i)) { // case 2: only has child left
            if (compare == 0) {
                if (subStep == 0) {
                    redFlag();
                    subStep = 1;
                    if (panelRemove == null) panelRemove = panels[i];
                } else if (subStep == 1) {
                    if (prevRoot == -1) {
                        removePanelFromView();
                    } else {
                        translatePanelToPrevRoot();
                    }
                    subStep = 2;
                } else if (subStep == 2) {
                    if (prevRoot > -1) panels[prevRoot] = panels[i];
                    panels[i] = null;
                    prevRoot = i;
                    translateLeftTreeBecomeRoot();
                    subStep = 0;
                    animationStep = 2;
                    indexCheckBalance = i == 0? -1 : (i - 1) / 2;
                }
            } else {
                whiteFlag();
                i = 2 * i + 1;
                animationStep = compare < 0? 0 : 2;
            }
        } else if (!hasChildLeft(i) && hasChildRight(i)) { // case 3: only has child right
            if (compare == 0) {
                if (subStep == 0) {
                    redFlag();
                    subStep = 1;
                    if (panelRemove == null) panelRemove = panels[i];
                } else if (subStep == 1) {
                    if (prevRoot == -1) {
                        removePanelFromView();
                    } else {
                        translatePanelToPrevRoot();
                    }
                    subStep = 2;
                } else if (subStep == 2) {
                    if (prevRoot > -1) panels[prevRoot] = panels[i];
                    panels[i] = null;
                    prevRoot = i;
                    translateRightTreeBecomeRoot();
                    subStep = 0;
                    animationStep = 2;
                    indexCheckBalance = i == 0? -1 : (i - 1) / 2;
                }
            } else {
                whiteFlag();
                i = 2 * i + 2;
                animationStep = compare < 0? 2 : 0;
            }
        } else { // case 4: has two children
            if (compare == 0) {
                if (subStep == 0) {
                    redFlag();
                    subStep = 1;
                    if (panelRemove == null) panelRemove = panels[i];
                } else if (subStep == 1) {
                    if (prevRoot == -1) {
                        removePanelFromView();
                    } else {
                        translatePanelToPrevRoot();
                    }
                    subStep = 2;
                } else if (subStep == 2) {
                    if (prevRoot > -1) panels[prevRoot] = panels[i];
                    prevRoot = i;
                    i = 2 * i + 2;
                    int indexMinNode = getIndexMinNode(i);
                    indexCheckBalance = indexMinNode == 0? -1 : (indexMinNode - 1) / 2;
                    value = panels[indexMinNode].getValue();
                    animationStep = 0;
                    subStep = 0;
                }
            } else {
                whiteFlag();
                i = compare < 0? 2 * i + 1 : 2 * i + 2;
                animationStep = 0;
            }
        }
    }

    protected void balance() {
        if (stepBalance == 0) {
            if (indexCheckBalance > -1) {
                i = indexCheckBalance;
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
                indexCheckBalance = i > 0?  (i-1) / 2 : -1;
                stepBalance = 0;
            }
        } else if(stepBalance == 2) {
            getRootScreen().setDescription("[BALANCE] Sub left tree - rotate left");
            rotateLeft(2 * i + 1);
            stepBalance = 3;
        } else if (stepBalance == 3) {
            getRootScreen().setDescription("[BALANCE] Current tree - rotate right");
            rotateRight(i);
            indexCheckBalance = i == 0? -1 : (i - 1) / 2;
            stepBalance = 0;
        }else if (stepBalance == 4) {
            getRootScreen().setDescription("[BALANCE] Sub right tree - rotate right");
            rotateRight(2 * i + 2);
            stepBalance = 5;
        } else if (stepBalance == 5) {
            getRootScreen().setDescription("[BALANCE] Current tree - rotate left");
            rotateLeft(i);
            indexCheckBalance = i == 0? -1 : (i - 1) / 2;
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
