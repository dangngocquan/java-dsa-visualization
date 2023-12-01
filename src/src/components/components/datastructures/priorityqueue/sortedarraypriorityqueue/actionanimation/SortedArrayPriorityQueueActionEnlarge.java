package src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.actionanimation;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.SortedArrayPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.ViewSortedArrayPriorityQueueAction;
import src.models.datastructures.priorityqueue.EntryInterface;
import src.models.datastructures.priorityqueue.SortedArrayPriorityQueue;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.Iterator;

public class SortedArrayPriorityQueueActionEnlarge extends AbstractSortedArrayPriorityQueueAnimation {
    private Panel title1;
    private Panel newPanelData;
    public SortedArrayPriorityQueueActionEnlarge(
            SortedArrayPriorityQueueScreen rootScreen,
            AbstractSortedArrayPriorityQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
    }

    public SortedArrayPriorityQueueScreen getRootScreen() {
        return (SortedArrayPriorityQueueScreen) rootScreen;
    }
    @Override
    public void run() {
        if (animationStep == 0) {
            createNewData();
            animationStep++;
        } else if (animationStep <= getRootScreen().queue.size()) {
            movePanelNodeToNewData(animationStep-1);
            animationStep++;
        } else if (animationStep == getRootScreen().queue.size() + 1) {
            replaceDataByNewData();
            animationStep++;
        } else if (animationStep == getRootScreen().queue.size() + 2) {
            solveRelations();
            animationStep++;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void createNewData() {
        getRootScreen().setDescription(
                "[ENLARGE] Create new array data b with b.length = 2 * a.length"
        );
        int dataLength = ((SortedArrayPriorityQueue<?, ?>) (getRootScreen().queue)).getSizeData() * 2;
        title1 = new Panel(
                0,
                ViewSortedArrayPriorityQueueAction.INITIAL_Y + 20 + ViewSortedArrayPriorityQueueAction.SIZE_PER_NODE + ViewSortedArrayPriorityQueueAction.GAP_Y,
                ViewSortedArrayPriorityQueueAction.INITIAL_X,
                20 + ViewSortedArrayPriorityQueueAction.SIZE_PER_NODE,
                Config.BACKGROUND_COLOR_APP,
                null, "NEW DATA", 0
        );
        title1.setFont(Config.MONOSPACED_BOLD_18);
        newPanelData = new Panel(
                ViewSortedArrayPriorityQueueAction.INITIAL_X,
                ViewSortedArrayPriorityQueueAction.INITIAL_Y + 20 + ViewSortedArrayPriorityQueueAction.SIZE_PER_NODE + ViewSortedArrayPriorityQueueAction.GAP_Y,
                (dataLength + 1) * ViewSortedArrayPriorityQueueAction.GAP_X + dataLength * ViewSortedArrayPriorityQueueAction.SIZE_PER_NODE,
                20 + ViewSortedArrayPriorityQueueAction.SIZE_PER_NODE,
                Config.COLOR_BAR_PLAIN,
                null, "", 0
        );
        newPanelData.setBorderWidth(2);

        title1.setXY(
                title1.getX() - 200,
                title1.getY()
        );

        newPanelData.setXY(
                newPanelData.getX() + 1500,
                newPanelData.getY()
        );

        rootScreen.viewAction.add(title1);
        rootScreen.viewAction.add(newPanelData);

        ServiceAnimation.translate(
                newPanelData,
                new Location(newPanelData.getX(), newPanelData.getY()),
                -1500,
                0,
                10,
                period - 10 + 1 - 1
        );

        ServiceAnimation.translate(
                title1,
                new Location(title1.getX(), title1.getY()),
                200,
                0,
                10,
                period - 10
        );

    }

    public void movePanelNodeToNewData(int index) {
        getRootScreen().setDescription(
                "[ENLARGE] Copy elements to new array data."
        );
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator
                = getRootScreen().queue.iterator();
        while (index-- > 0) iterator.next();
        AbstractPanelDataStructureNode panelNode = iterator.next().getValue();
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                0,
                10 + ViewSortedArrayPriorityQueueAction.SIZE_PER_NODE + ViewSortedArrayPriorityQueueAction.GAP_Y + 10,
                10,
                period - 10
        );
    }

    public void replaceDataByNewData() {
        getRootScreen().setDescription(
                "[ENLARGE] Update array data of PriorityQueue a := b"
        );
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator = getRootScreen().queue.iterator();
        while (iterator.hasNext()) {
            AbstractPanelDataStructureNode panel = iterator.next().getValue();
            rootScreen.viewAction.remove(panel);
            panel.setXY(
                    panel.getX() - ViewSortedArrayPriorityQueueAction.INITIAL_X,
                    10
            );
            newPanelData.add(panel);
        }
        ServiceAnimation.translate(
                newPanelData,
                new Location(newPanelData.getX(), newPanelData.getY()),
                0,
                -ViewSortedArrayPriorityQueueAction.GAP_Y - ViewSortedArrayPriorityQueueAction.SIZE_PER_NODE - 20,
                10,
                period - 10
        );

        rootScreen.viewAction.remove(title1);
        rootScreen.viewAction.remove(((ViewSortedArrayPriorityQueueAction) rootScreen.viewAction).panelData);
        rootScreen.viewAction.repaint();
    }

    public void solveRelations() {
        ((ViewSortedArrayPriorityQueueAction) rootScreen.viewAction).panelData = newPanelData;
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator = getRootScreen().queue.iterator();
        while (iterator.hasNext()) {
            AbstractPanelDataStructureNode panel = iterator.next().getValue();
            newPanelData.remove(panel);
            panel.setXY(
                    panel.getX() + ViewSortedArrayPriorityQueueAction.INITIAL_X,
                    ViewSortedArrayPriorityQueueAction.INITIAL_Y + 10
            );
            rootScreen.viewAction.add(panel);
            rootScreen.viewAction.setComponentZOrder(panel, 0);
        }
    }
}