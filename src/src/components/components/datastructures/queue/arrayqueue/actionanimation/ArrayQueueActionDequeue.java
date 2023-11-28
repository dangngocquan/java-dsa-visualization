package src.components.components.datastructures.queue.arrayqueue.actionanimation;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.AbstractPanelDataStructureNode;
import src.components.components.datastructures.queue.AbstractQueueAnimation;
import src.components.components.datastructures.queue.AbstractQueueScreen;
import src.components.components.datastructures.queue.arrayqueue.ArrayQueueScreen;
import src.components.components.datastructures.queue.arrayqueue.ViewArrayQueueAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class ArrayQueueActionDequeue extends AbstractArrayQueueAnimation {
    private final int index;
    private AbstractPanelDataStructureNode node;

    public ArrayQueueActionDequeue(
            AbstractQueueScreen rootScreen,
            AbstractQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
        this.index = getRootScreen().queue.size()-1;
    }

    public ArrayQueueScreen getRootScreen() {
        return (ArrayQueueScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            pickUpElement();
            animationStep++;
        } else if (animationStep <= getRootScreen().queue.size() - 1 - index) {
            movePanelNodeToLeft();
            animationStep++;
        } else if (animationStep == getRootScreen().queue.size() - index) {
            returnElement();
            animationStep++;
        } else if (animationStep == getRootScreen().queue.size() - index + 1) {
            solveEnd();
            animationStep = 100000;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void pickUpElement() {
        node = getRootScreen().queue.first();
        ServiceAnimation.transitionColor(
                node,
                Config.COLOR_BAR_PLAIN,
                Config.COLOR_BAR_FLAG,
                10,
                period - 10
        );
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                0,
                ViewArrayQueueAction.GAP_Y + 20,
                10, period - 10
        );
    }

    public void movePanelNodeToLeft() {
        AbstractPanelDataStructureNode node = getRootScreen().queue.first();
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                -ViewArrayQueueAction.SIZE_PER_NODE - ViewArrayQueueAction.GAP_X,
                0,
                10,
                period - 10
        );
    }

    public void returnElement() {
        ServiceAnimation.translate(
                node,
                new Location(node.getX(), node.getY()),
                1500,
                0,
                10, period - 10
        );
    }

    public void solveEnd() {
        getRootScreen().viewAction.remove(node);
        getRootScreen().queue.dequeue();
        Panel panelFirst = ((ViewArrayQueueAction) getRootScreen().viewAction).panelFirst;
        if (getRootScreen().queue.first() == null) {
            panelFirst.setVisible(false);
        } else {
            ServiceAnimation.translate(
                    panelFirst,
                    new Location(panelFirst.getX(), panelFirst.getY()),
                    getRootScreen().queue.first().getX() - panelFirst.getX(),
                    0,
                    10, period - 10
            );
        }

    }
}
