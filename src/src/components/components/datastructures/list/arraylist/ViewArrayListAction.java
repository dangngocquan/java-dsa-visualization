package src.components.components.datastructures.list.arraylist;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.list.AbstractListScreen;
import src.components.components.datastructures.list.AbstractViewListAction;
import src.components.components.datastructures.list.arraylist.actionanimation.*;
import src.models.datastructures.list.MyArrayList;

public class ViewArrayListAction extends AbstractViewListAction {
    public Panel panelData;
    public static final int INITIAL_X = 200;
    public static final int INITIAL_Y = 150;
    public static final int GAP_X = 40;
    public static final int GAP_Y = 200;
    public static final int SIZE_PER_NODE = (Config.WIDTH - 2 * INITIAL_X - 9 * GAP_X) / 8;

    public ViewArrayListAction(AbstractListScreen rootScreen) {
        super(rootScreen);
        drawTitle();
        drawElements();
        drawData();
        repaint();
    }

    public ArrayListScreen getRootScreen() {
        return (ArrayListScreen) rootScreen;
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

    public void drawElements() {;
        for (int i = 0; i < getRootScreen().list.size(); i++) {
            AbstractPanelDataStructureNode panelNode = getRootScreen().list.get(i);
            add(panelNode);
        }
    }

    @Override
    public void actionAdd(int value) {
        if (getRootScreen().list.size() == ((MyArrayList<?>) getRootScreen().list).getSizeData()) {
            new ArrayListActionEnlarge(
                    getRootScreen(),
                    1000,
                    new ArrayListActionAdd1(value, getRootScreen(), 1000, null)
            ).start();
        } else {
            new ArrayListActionAdd1(value, getRootScreen(), 1000, null).start();
        }
    }

    @Override
    public void actionAdd(int index, int value) {
        if (getRootScreen().list.size() == ((MyArrayList) getRootScreen().list).getSizeData()) {
            new ArrayListActionEnlarge(
                    getRootScreen(),
                    1000,
                    new ArrayListActionAdd2(index, value, getRootScreen(), 1000, null)
            ).start();
        } else {
            new ArrayListActionAdd2(index, value, getRootScreen(), 1000, null).start();
        }
    }

    @Override
    public void actionGet(int index) {
        new ArrayListActionGet(index, getRootScreen(), 2000, null).start();
    }

    @Override
    public void actionRemove(int index) {
        new ArrayListActionRemove1(index, getRootScreen(), 1000, null).start();
    }

    @Override
    public void actionRemove(Integer value) {
        new ArrayListActionRemove2(value, getRootScreen(), 1000, null).start();
    }
}
