package src.components.components.datastructures.list.abstractlistscreen;

import src.components.base.Panel;

import java.awt.*;

public abstract class AbstractViewListAction extends Panel {
    public AbstractListScreen rootScreen;

    public AbstractViewListAction(
            int x, int y,
            int width, int height,
            Color backgroundColor,
            AbstractListScreen rootScreen) {
        super(x, y, width, height, backgroundColor, null, "", 0);
        this.rootScreen = rootScreen;
    }

    public abstract void actionAdd(int value);
    public abstract void actionAdd(int index, int value);
    public abstract void actionGet(int index);
    public abstract void actionRemove(int index);
    public abstract void actionRemove(Integer value);

    public abstract void addPanelNode(AbstractPanelNode node);
    public abstract void addPanelNode(int index, AbstractPanelNode node);
    public abstract AbstractPanelNode getPanelNode(int index);
    public abstract AbstractPanelNode removePanelNode(int index);
    public abstract boolean removePanelNode(AbstractPanelNode node);
}
