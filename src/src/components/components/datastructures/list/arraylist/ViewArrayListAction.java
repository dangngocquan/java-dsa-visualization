package src.components.components.datastructures.list.arraylist;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.list.abstractlistscreen.AbstractListScreen;
import src.components.components.datastructures.list.abstractlistscreen.AbstractPanelNode;
import src.components.components.datastructures.list.abstractlistscreen.AbstractViewListAction;
import src.components.components.datastructures.list.arraylist.actionanimation.ArrayListAnimationFactory;
import src.models.datastructures.list.MyArrayList;
import src.models.datastructures.list.MyList;

import java.awt.*;

public class ViewArrayListAction extends AbstractViewListAction {
    private Panel title0;
    public Panel panelData;
    public MyList<AbstractPanelNode> panelElements;
    public static final int SIZE_PER_NODE = 60;
    public static final int GAP_X = 40;
    public static final int GAP_Y = 200;
    public static final int INITIAL_X = 200;
    public static final int INITIAL_Y = 150;


    public ViewArrayListAction(
            int x, int y, int width, int height, Color backgroundColor,
            AbstractListScreen rootScreen) {
        super(x, y, width, height, backgroundColor, rootScreen);
        drawTitle();
        drawElements();
        drawData();
        repaint();
    }

    public void drawTitle() {
        title0 = new Panel(
                0,
                INITIAL_Y,
                INITIAL_X,
                SIZE_PER_NODE + 20,
                getBackgroundColor(),
                null,
                "DATA",
                0
        );
        title0.setFont(Config.ARIAL_BOLD_18);
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
        panelElements = new MyArrayList<>();
        for (int i = 0; i < rootScreen.list.size(); i++) {
            ArrayListPanelNode panelNode = new ArrayListPanelNode(i, rootScreen.list.get(i));
            panelElements.add(panelNode);
            add(panelNode);
        }
    }

    @Override
    public void actionAdd(int value) {
//        ArrayListAnimationFactory.actionAdd(value, rootScreen, 2000);
        ArrayListAnimationFactory.actionEnlarge(rootScreen, 2000);
    }

    @Override
    public void actionAdd(int index, int value) {

    }

    @Override
    public void actionGet(int index) {

    }

    @Override
    public void actionRemove(int index) {

    }

    @Override
    public void actionRemove(Integer value) {

    }

    @Override
    public void addPanelNode(AbstractPanelNode node) {
        panelElements.add(node);
    }

    @Override
    public void addPanelNode(int index, AbstractPanelNode node) {
        panelElements.add(index, node);
    }

    @Override
    public AbstractPanelNode getPanelNode(int index) {
        return panelElements.get(index);
    }

    @Override
    public AbstractPanelNode removePanelNode(int index) {
        return panelElements.remove(index);
    }

    @Override
    public boolean removePanelNode(AbstractPanelNode node) {
        return panelElements.remove(node);
    }
}
