package src.components.components.datastructures.list.arraylist.actionanimation;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.list.abstractlistscreen.AbstractListScreen;
import src.components.components.datastructures.list.abstractlistscreen.AbstractPanelNode;
import src.components.components.datastructures.list.arraylist.ViewArrayListAction;
import src.models.datastructures.list.MyArrayList;
import src.models.datastructures.list.MyList;
import src.services.animation.Animation;
import src.services.animation.Location;

public class ActionEnlarge extends ArrayListAnimation {
    private Panel title1;
    private Panel newPanelData;
    public ActionEnlarge(AbstractListScreen rootScreen, int period) {
        super(rootScreen, period);
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            createNewData();
            animationStep++;
        } else if (animationStep <= rootScreen.list.size()) {
            movePanelNodeToNewData(animationStep-1);
            animationStep++;
        } else if (animationStep == rootScreen.list.size() + 1) {
            replaceDataByNewData();
            animationStep++;
        } else if (animationStep == rootScreen.list.size() + 2) {
            solveRelations();
            animationStep++;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void createNewData() {
        int dataLength = ((MyArrayList) (rootScreen.list)).getSizeData() * 2;
        title1 = new Panel(
                0,
                ViewArrayListAction.INITIAL_Y + 20 + ViewArrayListAction.SIZE_PER_NODE + ViewArrayListAction.GAP_Y,
                ViewArrayListAction.INITIAL_X,
                20 + ViewArrayListAction.SIZE_PER_NODE,
                Config.BACKGROUND_COLOR_APP,
                null, "NEW DATA", 0
        );
        title1.setFont(Config.ARIAL_BOLD_18);
        newPanelData = new Panel(
                ViewArrayListAction.INITIAL_X,
                ViewArrayListAction.INITIAL_Y + 20 + ViewArrayListAction.SIZE_PER_NODE + ViewArrayListAction.GAP_Y,
                (dataLength + 1) * ViewArrayListAction.GAP_X + dataLength * ViewArrayListAction.SIZE_PER_NODE,
                20 + ViewArrayListAction.SIZE_PER_NODE,
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

        Animation.translate(
                newPanelData,
                new Location(newPanelData.getX(), newPanelData.getY()),
                -1500,
                0,
                10,
                period - 10
        );

        Animation.translate(
                title1,
                new Location(title1.getX(), title1.getY()),
                200,
                0,
                10,
                period - 10
        );

    }

    public void movePanelNodeToNewData(int index) {
        AbstractPanelNode panelNode = rootScreen.viewAction.getPanelNode(index);
        Animation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                0,
                10 + ViewArrayListAction.SIZE_PER_NODE + ViewArrayListAction.GAP_Y + 10,
                10,
                period - 10
        );
    }

    public void replaceDataByNewData() {
        MyList<AbstractPanelNode> panelElements = ((ViewArrayListAction) rootScreen.viewAction).panelElements;
        for (int i = 0; i < panelElements.size(); i++) {
            AbstractPanelNode panel = panelElements.get(i);
            rootScreen.viewAction.remove(panel);
            panel.setXY(
                    panel.getX() - ViewArrayListAction.INITIAL_X,
                    10
            );
            newPanelData.add(panel);
        }
        Animation.translate(
                newPanelData,
                new Location(newPanelData.getX(), newPanelData.getY()),
                0,
                -ViewArrayListAction.GAP_Y - ViewArrayListAction.SIZE_PER_NODE - 20,
                10,
                period - 10
        );

        rootScreen.viewAction.remove(title1);
        rootScreen.viewAction.remove(((ViewArrayListAction) rootScreen.viewAction).panelData);
        rootScreen.viewAction.repaint();
    }

    public void solveRelations() {
        ((ViewArrayListAction) rootScreen.viewAction).panelData = newPanelData;
        MyList<AbstractPanelNode> panelElements = ((ViewArrayListAction) rootScreen.viewAction).panelElements;
        for (int i = 0; i < panelElements.size(); i++) {
            AbstractPanelNode panel = panelElements.get(i);
            newPanelData.remove(panel);
            panel.setXY(
                    panel.getX() + ViewArrayListAction.INITIAL_X,
                    ViewArrayListAction.INITIAL_Y + 10
            );
            rootScreen.viewAction.add(panel);
        }
    }
}
