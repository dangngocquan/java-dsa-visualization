package src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.AbstractViewPriorityQueueAction;
import src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.actionanimation.SortedArrayPriorityQueueActionEnlarge;
import src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.actionanimation.SortedArrayPriorityQueueActionInsert;
import src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.actionanimation.SortedArrayPriorityQueueActionRemoveMin;
import src.models.datastructures.priorityqueue.EntryInterface;
import src.models.datastructures.priorityqueue.SortedArrayPriorityQueue;

import java.util.Iterator;

public class ViewSortedArrayPriorityQueueAction extends AbstractViewPriorityQueueAction {
    public Panel panelData;
    public static final int INITIAL_X = 200;
    public static final int INITIAL_Y = 150;
    public static final int GAP_X = 40;
    public static final int GAP_Y = 200;
    public static final int SIZE_PER_NODE =
            (Config.WIDTH - 2 * INITIAL_X - (Config.MAX_NODE_TYPE_1) * GAP_X)
                    / Config.MAX_NODE_TYPE_1;

    public ViewSortedArrayPriorityQueueAction(AbstractPriorityQueueScreen rootScreen) {
        super(rootScreen);
        drawTitle();
        drawElements();
        drawData();
        addDescriptionPanel();
        repaint();
    }

    public SortedArrayPriorityQueueScreen getRootScreen() {
        return (SortedArrayPriorityQueueScreen) rootScreen;
    }

    public void drawTitle() {
        Panel title0 = new Panel(
                0,
                INITIAL_Y,
                INITIAL_X,
                SIZE_PER_NODE + 20,
                getBackgroundColor(),
                null,
                "DATA",
                0
        );
        title0.setFont(Config.MONOSPACED_BOLD_18);
        add(title0);
    }

    public void drawData() {
        panelData = new Panel(
                INITIAL_X,
                INITIAL_Y,
                SIZE_PER_NODE * 4 + 5 * GAP_X,
                SIZE_PER_NODE + 20,
                Config.COLOR_BAR_PLAIN,
                null,
                "",
                0
        );
        panelData.setBorderWidth(2);
        add(panelData);
    }

    public void drawElements() {
        Iterator<EntryInterface<Integer, AbstractPanelPriorityQueueNode>> iterator
                = getRootScreen().queue.iterator();
        while (iterator.hasNext()) {
            add(iterator.next().getValue());
        }
    }

    @Override
    public void actionInsert(Integer key, Integer value) {
        if (getRootScreen().queue.size() == ((SortedArrayPriorityQueue<?, ?>) getRootScreen().queue).getSizeData()) {
            new SortedArrayPriorityQueueActionEnlarge(
                    getRootScreen(),
                    new SortedArrayPriorityQueueActionInsert(
                            key, value, getRootScreen(), null
                    )
            ).start();
        } else {
            new SortedArrayPriorityQueueActionInsert(
                    key, value, getRootScreen(), null
            ).start();
        }
    }

    @Override
    public void actionRemoveMin() {
        new SortedArrayPriorityQueueActionRemoveMin(getRootScreen(), null).start();
    }
}