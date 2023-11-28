package src.components.components.datastructures.queue.arrayqueue;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.queue.AbstractPanelQueueNode;
import src.components.components.datastructures.queue.AbstractQueueScreen;
import src.components.components.datastructures.queue.AbstractViewQueueAction;
import src.components.components.datastructures.queue.arrayqueue.actionanimation.ArrayQueueActionDequeue;
import src.components.components.datastructures.queue.arrayqueue.actionanimation.ArrayQueueActionEnlarge;
import src.components.components.datastructures.queue.arrayqueue.actionanimation.ArrayQueueActionEnqueue;
import src.models.datastructures.queue.ArrayQueue;

public class ViewArrayQueueAction extends AbstractViewQueueAction {
    public Panel panelData;
    public Panel panelFirst;
    public static final int INITIAL_X = 200;
    public static final int INITIAL_Y = 150;
    public static final int GAP_X = 40;
    public static final int GAP_Y = 200;
    public static final int SIZE_PER_NODE = (Config.WIDTH - 2 * INITIAL_X - 9 * GAP_X) / 8;

    public ViewArrayQueueAction(AbstractQueueScreen rootScreen) {
        super(rootScreen);
        drawTitle();
        drawElements();
        drawData();
        repaint();
    }

    public ArrayQueueScreen getRootScreen() {
        return (ArrayQueueScreen) rootScreen;
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
        panelFirst = new Panel(
                INITIAL_X + GAP_X,
                INITIAL_Y - 60,
                SIZE_PER_NODE,
                40,
                Config.COLOR_RED,
                null,
                "First",
                0
        );
        panelFirst.setBorderWidth(2);
        panelFirst.setFont(Config.MONOSPACED_BOLD_14);
        add(panelData);
        add(panelFirst);
    }

    public void drawElements() {
        for (AbstractPanelQueueNode panelNode : getRootScreen().queue) {
            add(panelNode);
        }
    }

    @Override
    public void actionEnqueue(int value) {
        if (getRootScreen().queue.size() == ((ArrayQueue<?>) getRootScreen().queue).getSizeData()) {
            new ArrayQueueActionEnlarge(
                    getRootScreen(),
                    new ArrayQueueActionEnqueue(
                            value, getRootScreen(), null)
            ).start();
        } else {
            new ArrayQueueActionEnqueue(
                    value, getRootScreen(), null).start();
        }
    }

    @Override
    public void actionDequeue() {
        new ArrayQueueActionDequeue(getRootScreen(), null).start();
    }
}