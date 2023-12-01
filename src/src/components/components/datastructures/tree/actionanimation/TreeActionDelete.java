package src.components.components.datastructures.tree.actionanimation;

import src.Config;
import src.components.components.datastructures.tree.AbstractTreeAnimation;
import src.components.components.datastructures.tree.AbstractTreeScreen;
import src.components.components.datastructures.tree.AbstractViewTreeAction;
import src.components.components.datastructures.tree.TreePanelNode;
import src.models.datastructures.queue.LinkedQueue;
import src.models.datastructures.queue.QueueInterface;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.LinkedHashMap;
import java.util.Map;

public class TreeActionDelete extends AbstractTreeAnimation {
    public int value;
    public TreePanelNode[] panels;
    public int i;
    public int subStep = 0;
    public int prevRoot = -1;
    public TreePanelNode panelRemove = null;

    public TreeActionDelete(
            int value,
            AbstractTreeScreen rootScreen,
            int period,
            AbstractTreeAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.value = value;
        this.panels = getViewAction().panelsClone;
    }

    public AbstractTreeScreen getRootScreen() {
        return (AbstractTreeScreen) rootScreen;
    }

    public AbstractViewTreeAction getViewAction() {
        return (AbstractViewTreeAction) getRootScreen().viewAction;
    }

    @Override
    public void run() {
        AbstractViewTreeAction.movableArrow = false;
        if (animationStep == 0) {
            if (i < panels.length) {
                checkNode();
            } else {
                getRootScreen().setDescription(
                        "[DELETE] Finished."
                );
                end();
                getRootScreen().tree.delete(panelRemove);
                getViewAction().resetPanelsClone();
            }
        } else if (animationStep == 1) {
            delete();
        } else {
            getRootScreen().setDescription(
                    "[DELETE] Finished."
            );
            end();
            getRootScreen().tree.delete(panelRemove);
            getViewAction().resetPanelsClone();
        }
    }

    public void checkNode() {
        getRootScreen().setDescription(
                String.format(
                        "[CHECK] Check element %d", panels[i].getValue()
                )
        );
        ServiceAnimation.transitionColor(
                panels[i],
                panels[i].getBackgroundColor(),
                Config.COLOR_YELLOW,
                10, period - 10
        );
        animationStep = 1;
    }

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
                } else if (subStep == 2) {
                    if (prevRoot > -1) panels[prevRoot] = panels[i];
                    panels[i] = null;
                    prevRoot = i;
                    subStep = 0;
                    animationStep = 2;
                }
            } else {
                whiteFlag();
                animationStep = 2;
            }
        } else if (hasChildLeft(i) && !hasChildRight(i)) { // case 2: only has child left
            if (compare == 0) {
                if (subStep == 0) {
                    redFlag();
                    if (panelRemove == null) panelRemove = panels[i];
                    subStep = 1;
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
                    if (panelRemove == null) panelRemove = panels[i];
                    subStep = 1;
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
                    prevRoot = i;
                    i = 2 * i + 2;
                    value = panels[getIndexMinNode(i)].getValue();
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

    public boolean hasChildLeft(int i) {
        return 2 * i + 1 < panels.length && panels[2 * i + 1] != null;
    }

    public boolean hasChildRight(int i) {
        return 2 * i + 2 < panels.length && panels[2 * i + 2] != null;
    }

    protected void redFlag() {
        ServiceAnimation.transitionColor(
                panels[i],
                panels[i].getBackgroundColor(),
                Config.COLOR_RED,
                10, period - 10
        );
    }

    protected void whiteFlag() {
        ServiceAnimation.transitionColor(
                panels[i],
                panels[i].getBackgroundColor(),
                Config.COLOR_WHITE,
                10, period - 10
        );
    }

    protected void yellowFlag() {
        ServiceAnimation.transitionColor(
                panels[i],
                panels[i].getBackgroundColor(),
                Config.COLOR_YELLOW,
                10, period - 10
        );
    }

    protected void removePanelFromView() {
        getRootScreen().setDescription(
                "[DELETE] Delete this element."
        );
        ServiceAnimation.translate(
                panels[i],
                new Location(panels[i].getX(), panels[i].getY()),
                0,
                1000,
                10,
                period - 10
        );
    }

    protected void translatePanelToPrevRoot() {
        getRootScreen().setDescription(
                "[DELETE] Found min element of sub right tree."
        );
        ServiceAnimation.translate(
                panels[i],
                new Location(panels[i].getX(), panels[i].getY()),
                AbstractViewTreeAction.getDefaultX(prevRoot) - panels[i].getX(),
                AbstractViewTreeAction.getDefaultY(prevRoot) - panels[i].getY(),
                10,
                period - 10
        );
    }

    protected void translateLeftTreeBecomeRoot() {
        getRootScreen().setDescription(
                "[UPDATE] node := node.left"
        );
        translateSubTreeBecomeRoot(1);
    }

    protected void translateRightTreeBecomeRoot() {
        getRootScreen().setDescription(
                "[UPDATE] node := node.right"
        );
        translateSubTreeBecomeRoot(2);
    }

    // type == 1: tree left
    // type == 2: tree right
    protected void translateSubTreeBecomeRoot(int type) {
        AbstractViewTreeAction.movableArrow = true;
        Map<Integer, Integer> newIndexes = new LinkedHashMap<>();
        QueueInterface<Integer> queueOldIndexes = new LinkedQueue<>();
        queueOldIndexes.enqueue(2 * i + type); // add root of subtree
        newIndexes.put(2 * i + type, i);
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

        for (Integer i : newIndexes.keySet()) {
            panels[i] = null;
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

    public int getIndexMinNode(int rootIndex) {
        int index = rootIndex;
        while (hasChildLeft(index)) index = 2 * index + 1;
        return index;
    }
}
