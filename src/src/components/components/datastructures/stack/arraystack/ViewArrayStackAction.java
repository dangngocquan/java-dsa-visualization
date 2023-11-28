package src.components.components.datastructures.stack.arraystack;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.stack.AbstractPanelStackNode;
import src.components.components.datastructures.stack.AbstractStackScreen;
import src.components.components.datastructures.stack.AbstractViewStackAction;
import src.components.components.datastructures.stack.arraystack.actionanimation.ArrayStackActionEnlarge;
import src.components.components.datastructures.stack.arraystack.actionanimation.ArrayStackActionPop;
import src.components.components.datastructures.stack.arraystack.actionanimation.ArrayStackActionPush;
import src.models.datastructures.stack.ArrayStack;

public class ViewArrayStackAction extends AbstractViewStackAction {
    public Panel panelData;
    public static final int INITIAL_X = 200;
    public static final int INITIAL_Y = 150;
    public static final int GAP_X = 40;
    public static final int GAP_Y = 200;
    public static final int SIZE_PER_NODE = (Config.WIDTH - 2 * INITIAL_X - 9 * GAP_X) / 8;

    public ViewArrayStackAction(AbstractStackScreen rootScreen) {
        super(rootScreen);
        drawTitle();
        drawElements();
        drawData();
        repaint();
    }

    public ArrayStackScreen getRootScreen() {
        return (ArrayStackScreen) rootScreen;
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
        for (AbstractPanelStackNode panelNode : getRootScreen().stack) {
            add(panelNode);
        }
    }

    @Override
    public void actionPush(int value) {
        if (getRootScreen().stack.size() == ((ArrayStack<?>) getRootScreen().stack).getSizeData()) {
            new ArrayStackActionEnlarge(
                    getRootScreen(),
                    new ArrayStackActionPush(value, getRootScreen(), null)
            ).start();
        } else {
            new ArrayStackActionPush(value, getRootScreen(), null).start();
        }
    }

    @Override
    public void actionPop() {
        new ArrayStackActionPop(getRootScreen(), null).start();
    }
}
