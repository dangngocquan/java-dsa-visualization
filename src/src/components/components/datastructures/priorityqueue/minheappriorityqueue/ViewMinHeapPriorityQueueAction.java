package src.components.components.datastructures.priorityqueue.minheappriorityqueue;

import src.Config;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.AbstractViewPriorityQueueAction;
import src.components.components.datastructures.priorityqueue.minheappriorityqueue.actionanimation.MinHeapPriorityQueueActionInsert;
import src.components.components.datastructures.priorityqueue.minheappriorityqueue.actionanimation.MinHeapPriorityQueueActionRemoveMin;
import src.models.datastructures.priorityqueue.EntryInterface;
import src.services.ServiceComponent;

import java.awt.*;
import java.util.Iterator;

public class ViewMinHeapPriorityQueueAction extends AbstractViewPriorityQueueAction {
    public static final int INITIAL_X = 10;
    public static final int INITIAL_Y = 30;
    public static final int GAP_X = 30;
    public static final int GAP_Y = 60;
    public static final int SIZE_PER_NODE = (Config.WIDTH - 2 * INITIAL_X - 16 * GAP_X) / 15;
    public MinHeapPriorityQueuePanelNode[] dataClone;

    public ViewMinHeapPriorityQueueAction(AbstractPriorityQueueScreen rootScreen) {
        super(rootScreen);
        dataClone = getRootScreen().getCloneData();
        drawElements();
        repaint();
    }

    public MinHeapPriorityQueueScreen getRootScreen() {
        return (MinHeapPriorityQueueScreen) rootScreen;
    }

    public void resetDataClone() {
        dataClone = getRootScreen().getCloneData();
    }

    public void drawElements() {
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator
                = getRootScreen().queue.iterator();
        while (iterator.hasNext()) {
            add(iterator.next().getValue());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        g2d.setStroke(new BasicStroke(2));
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        for (int i = 0; i < 7; i++) {
            if (dataClone[i] == null) break;
            if (dataClone[2 * i + 1] != null) {
                ServiceComponent.drawPatternArrow2(
                        g2d,
                        getDefaultX(i),
                        getDefaultY(i) + dataClone[i].getHeightPanel() / 2,
                        getDefaultX(2 * i + 1) + dataClone[2 * i + 1].getWidthPanel() / 2,
                        getDefaultY(2 * i + 1),
                        Color.BLACK
                );
            }
            if (dataClone[2 * i + 2] != null) {
                ServiceComponent.drawPatternArrow2(
                        g2d,
                        getDefaultX(i) + dataClone[i].getWidthPanel(),
                        getDefaultY(i) + dataClone[i].getHeightPanel() / 2,
                        getDefaultX(2 * i + 2) + dataClone[2 * i + 1].getWidthPanel() / 2,
                        getDefaultY(2 * i + 2),
                        Color.BLACK
                );
            }
        }
    }

    public int getDefaultX(int i) {
        return ViewMinHeapPriorityQueueAction.INITIAL_X
                + ViewMinHeapPriorityQueueAction.GAP_X * (MinHeapPriorityQueueScreen.indexColumns[i] + 1)
                + ViewMinHeapPriorityQueueAction.SIZE_PER_NODE * MinHeapPriorityQueueScreen.indexColumns[i];
    }

    public int getDefaultY(int i) {
        return ViewMinHeapPriorityQueueAction.INITIAL_Y
                + ViewMinHeapPriorityQueueAction.GAP_Y * (MinHeapPriorityQueueScreen.indexRows[i] + 1)
                + ViewMinHeapPriorityQueueAction.SIZE_PER_NODE * MinHeapPriorityQueueScreen.indexRows[i];
    }

    @Override
    public void actionInsert(Integer key, Integer value) {
        new MinHeapPriorityQueueActionInsert(
                key, value, getRootScreen(), 1000, null
        ).start();
    }

    @Override
    public void actionRemoveMin() {
        new MinHeapPriorityQueueActionRemoveMin(
                getRootScreen(), 1000, null
        ).start();
    }
}