package src.components.components.datastructures.queue.arrayqueue.actionanimation;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.queue.AbstractPanelQueueNode;
import src.components.components.datastructures.queue.AbstractQueueAnimation;
import src.components.components.datastructures.queue.AbstractQueueScreen;
import src.components.components.datastructures.queue.arrayqueue.ArrayQueueScreen;
import src.components.components.datastructures.queue.arrayqueue.ViewArrayQueueAction;
import src.models.datastructures.queue.ArrayQueue;
import src.models.datastructures.queue.QueueInterface;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.Iterator;

public class ArrayQueueActionEnlarge extends AbstractArrayQueueAnimation {
    private Panel title1;
    private Panel newPanelData;
    private final Iterator<AbstractPanelQueueNode> iterator;
    private int tempValue = 0;
    public ArrayQueueActionEnlarge(
            AbstractQueueScreen rootScreen,
            int period, AbstractQueueAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        iterator = getRootScreen().queue.iterator();
    }

    public ArrayQueueScreen getRootScreen() {
        return (ArrayQueueScreen) rootScreen;
    }
    @Override
    public void run() {
        if (animationStep == 0) {
            createNewData();
            animationStep++;
        } else if (animationStep <= getRootScreen().queue.size()) {
            movePanelNodeToNewData(iterator);
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
            System.out.println(getRootScreen().queue.size());
        }
    }

    public void createNewData() {
        int dataLength = ((ArrayQueue<AbstractPanelQueueNode>) (getRootScreen().queue)).getSizeData() * 2;
        title1 = new Panel(
                0,
                ViewArrayQueueAction.INITIAL_Y + 20 + ViewArrayQueueAction.SIZE_PER_NODE + ViewArrayQueueAction.GAP_Y,
                ViewArrayQueueAction.INITIAL_X,
                20 + ViewArrayQueueAction.SIZE_PER_NODE,
                Config.BACKGROUND_COLOR_APP,
                null, "NEW DATA", 0
        );
        title1.setFont(Config.MONOSPACED_BOLD_18);
        newPanelData = new Panel(
                ViewArrayQueueAction.INITIAL_X,
                ViewArrayQueueAction.INITIAL_Y + 20 + ViewArrayQueueAction.SIZE_PER_NODE + ViewArrayQueueAction.GAP_Y,
                (dataLength + 1) * ViewArrayQueueAction.GAP_X + dataLength * ViewArrayQueueAction.SIZE_PER_NODE,
                20 + ViewArrayQueueAction.SIZE_PER_NODE,
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
                period - 10
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

    public void movePanelNodeToNewData(Iterator<AbstractPanelQueueNode> iterator) {
        AbstractPanelDataStructureNode panelNode = iterator.next();
        int x = ViewArrayQueueAction.INITIAL_X + ViewArrayQueueAction.GAP_X
                + (ViewArrayQueueAction.SIZE_PER_NODE + ViewArrayQueueAction.GAP_X) * tempValue;
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                x - panelNode.getX(),
                10 + ViewArrayQueueAction.SIZE_PER_NODE + ViewArrayQueueAction.GAP_Y + 10,
                10,
                period - 10
        );
        tempValue++;
    }

    public void replaceDataByNewData() {
        QueueInterface<AbstractPanelQueueNode> panelElements = getRootScreen().queue;
        for (AbstractPanelQueueNode panel : panelElements) {
            rootScreen.viewAction.remove(panel);
            panel.setXY(
                    panel.getX() - ViewArrayQueueAction.INITIAL_X,
                    10
            );
            newPanelData.add(panel);
        }
        ServiceAnimation.translate(
                newPanelData,
                new Location(newPanelData.getX(), newPanelData.getY()),
                0,
                -ViewArrayQueueAction.GAP_Y - ViewArrayQueueAction.SIZE_PER_NODE - 20,
                10,
                period - 10
        );
        Panel panelFirst = ((ViewArrayQueueAction) getRootScreen().viewAction).panelFirst;
        ServiceAnimation.translate(
                panelFirst,
                new Location(panelFirst.getX(), panelFirst.getY()),
                ViewArrayQueueAction.INITIAL_X + ViewArrayQueueAction.GAP_X - panelFirst.getX(),
                0,
                10,
                period - 10
        );

        rootScreen.viewAction.remove(title1);
        rootScreen.viewAction.remove(((ViewArrayQueueAction) rootScreen.viewAction).panelData);
        rootScreen.viewAction.repaint();
    }

    public void solveRelations() {
        ((ViewArrayQueueAction) rootScreen.viewAction).panelData = newPanelData;
        QueueInterface<AbstractPanelQueueNode> panelElements = getRootScreen().queue;
        for (AbstractPanelQueueNode panel : panelElements) {
            newPanelData.remove(panel);
            panel.setXY(
                    panel.getX() + ViewArrayQueueAction.INITIAL_X,
                    ViewArrayQueueAction.INITIAL_Y + 10
            );
            rootScreen.viewAction.add(panel);
            rootScreen.viewAction.setComponentZOrder(panel, 0);
        }
    }
}