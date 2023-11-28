package src.components.components.datastructures.stack.arraystack.actionanimation;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.stack.AbstractPanelStackNode;
import src.components.components.datastructures.stack.AbstractStackAnimation;
import src.components.components.datastructures.stack.AbstractStackScreen;
import src.components.components.datastructures.stack.arraystack.ArrayStackScreen;
import src.components.components.datastructures.stack.arraystack.ViewArrayStackAction;
import src.models.datastructures.stack.ArrayStack;
import src.models.datastructures.stack.StackInterface;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.util.Iterator;

public class ArrayStackActionEnlarge extends AbstractArrayStackAnimation {
    private Panel title1;
    private Panel newPanelData;
    private Iterator<AbstractPanelStackNode> iterator;
    public ArrayStackActionEnlarge(
            AbstractStackScreen rootScreen,
            AbstractStackAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        iterator = getRootScreen().stack.iterator();
    }

    public ArrayStackScreen getRootScreen() {
        return (ArrayStackScreen) rootScreen;
    }
    @Override
    public void run() {
        if (animationStep == 0) {
            createNewData();
            animationStep++;
        } else if (animationStep <= getRootScreen().stack.size()) {
            movePanelNodeToNewData(iterator);
            animationStep++;
        } else if (animationStep == getRootScreen().stack.size() + 1) {
            replaceDataByNewData();
            animationStep++;
        } else if (animationStep == getRootScreen().stack.size() + 2) {
            solveRelations();
            animationStep++;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void createNewData() {
        int dataLength = ((ArrayStack<AbstractPanelStackNode>) (getRootScreen().stack)).getSizeData() * 2;
        title1 = new Panel(
                0,
                ViewArrayStackAction.INITIAL_Y + 20 + ViewArrayStackAction.SIZE_PER_NODE + ViewArrayStackAction.GAP_Y,
                ViewArrayStackAction.INITIAL_X,
                20 + ViewArrayStackAction.SIZE_PER_NODE,
                Config.BACKGROUND_COLOR_APP,
                null, "NEW DATA", 0
        );
        title1.setFont(Config.MONOSPACED_BOLD_18);
        newPanelData = new Panel(
                ViewArrayStackAction.INITIAL_X,
                ViewArrayStackAction.INITIAL_Y + 20 + ViewArrayStackAction.SIZE_PER_NODE + ViewArrayStackAction.GAP_Y,
                (dataLength + 1) * ViewArrayStackAction.GAP_X + dataLength * ViewArrayStackAction.SIZE_PER_NODE,
                20 + ViewArrayStackAction.SIZE_PER_NODE,
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

    public void movePanelNodeToNewData(Iterator<AbstractPanelStackNode> iterator) {
        AbstractPanelDataStructureNode panelNode = iterator.next();
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                0,
                10 + ViewArrayStackAction.SIZE_PER_NODE + ViewArrayStackAction.GAP_Y + 10,
                10,
                period - 10
        );
    }

    public void replaceDataByNewData() {
        StackInterface<AbstractPanelStackNode> panelElements = getRootScreen().stack;
        for (AbstractPanelStackNode panel : panelElements) {
            rootScreen.viewAction.remove(panel);
            panel.setXY(
                    panel.getX() - ViewArrayStackAction.INITIAL_X,
                    10
            );
            newPanelData.add(panel);
        }
        ServiceAnimation.translate(
                newPanelData,
                new Location(newPanelData.getX(), newPanelData.getY()),
                0,
                -ViewArrayStackAction.GAP_Y - ViewArrayStackAction.SIZE_PER_NODE - 20,
                10,
                period - 10
        );

        rootScreen.viewAction.remove(title1);
        rootScreen.viewAction.remove(((ViewArrayStackAction) rootScreen.viewAction).panelData);
        rootScreen.viewAction.repaint();
    }

    public void solveRelations() {
        ((ViewArrayStackAction) rootScreen.viewAction).panelData = newPanelData;
        StackInterface<AbstractPanelStackNode> panelElements = getRootScreen().stack;
        for (AbstractPanelStackNode panel : panelElements) {
            newPanelData.remove(panel);
            panel.setXY(
                    panel.getX() + ViewArrayStackAction.INITIAL_X,
                    ViewArrayStackAction.INITIAL_Y + 10
            );
            rootScreen.viewAction.add(panel);
            rootScreen.viewAction.setComponentZOrder(panel, 0);
        }
    }
}
