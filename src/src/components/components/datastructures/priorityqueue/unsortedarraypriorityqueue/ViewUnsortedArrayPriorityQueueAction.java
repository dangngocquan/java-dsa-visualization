package src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.priorityqueue.AbstractPanelPriorityQueueNode;
import src.components.components.datastructures.priorityqueue.AbstractPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.AbstractViewPriorityQueueAction;
import src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue.actionanimation.UnsortedArrayPriorityQueueActionEnlarge;
import src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue.actionanimation.UnsortedArrayPriorityQueueActionInsert;
import src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue.actionanimation.UnsortedArrayPriorityQueueActionRemoveMin;
import src.models.datastructures.priorityqueue.EntryInterface;
import src.models.datastructures.priorityqueue.UnsortedArrayPriorityQueue;

import java.util.Iterator;

public class ViewUnsortedArrayPriorityQueueAction extends AbstractViewPriorityQueueAction {
    public Panel panelData;
    public static final int INITIAL_X = 200;
    public static final int INITIAL_Y = 150;
    public static final int GAP_X = 20;
    public static final int GAP_Y = 200;
    public static final int SIZE_PER_NODE =
            (Config.WIDTH - 2 * INITIAL_X - (Config.MAX_NODE_TYPE_1 + 1) * GAP_X)
                    / Config.MAX_NODE_TYPE_1;

    public ViewUnsortedArrayPriorityQueueAction(AbstractPriorityQueueScreen rootScreen) {
        super(rootScreen);
        drawTitle();
        drawElements();
        drawData();
        addDescriptionPanel();
        repaint();
    }

    public UnsortedArrayPriorityQueueScreen getRootScreen() {
        return (UnsortedArrayPriorityQueueScreen) rootScreen;
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
                Config.COLOR_WHITE,
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
        if (getRootScreen().queue.size() == ((UnsortedArrayPriorityQueue<?, ?>) getRootScreen().queue).getSizeData()) {
            new UnsortedArrayPriorityQueueActionEnlarge(
                    getRootScreen(),
                    new UnsortedArrayPriorityQueueActionInsert(
                            key, value, getRootScreen(), null
                    )
            ).start();
        } else {
            new UnsortedArrayPriorityQueueActionInsert(
                    key, value, getRootScreen(), null
            ).start();
        }
    }

    @Override
    public void actionRemoveMin() {
        new UnsortedArrayPriorityQueueActionRemoveMin(getRootScreen(), null).start();
    }
}