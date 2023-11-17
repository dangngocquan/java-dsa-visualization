package src.components.components.datastructures.queue.arrayqueue.actionanimation;

import src.components.components.datastructures.queue.AbstractQueueAnimation;
import src.components.components.datastructures.queue.AbstractQueueScreen;
import src.components.components.datastructures.queue.arrayqueue.ArrayQueuePanelNode;
import src.components.components.datastructures.queue.arrayqueue.ArrayQueueScreen;
import src.components.components.datastructures.queue.arrayqueue.ViewArrayQueueAction;
import src.models.datastructures.queue.queue.ArrayQueue;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

public class ArrayQueueActionEnqueue extends AbstractArrayQueueAnimation {
    public int value;
    public int index;
    public int x;
    public int y;
    public ArrayQueuePanelNode panelNode;

    public ArrayQueueActionEnqueue(
            int value, AbstractQueueScreen rootScreen,
            int period, AbstractQueueAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.value = value;
        int size = getRootScreen().queue.size();
        int firstIndex = ((ArrayQueue<?>) getRootScreen().queue).getFirstIndex();
        int dataSize = ((ArrayQueue<?>) getRootScreen().queue).getSizeData();
        if (size < dataSize) {
            index = (firstIndex + size) % dataSize;
        } else {
            index = size;
        }

//        System.out.println(size + " " + firstIndex + " " + dataSize + " " + index);
        panelNode = new ArrayQueuePanelNode(index, value);
        x = ViewArrayQueueAction.INITIAL_X
                + ViewArrayQueueAction.GAP_X * (index + 1)
                + ViewArrayQueueAction.SIZE_PER_NODE * index;
        y = ViewArrayQueueAction.INITIAL_Y + 10;
        panelNode.setXY(
                x + 1500,
                y + ViewArrayQueueAction.SIZE_PER_NODE + 10 + ViewArrayQueueAction.GAP_Y
        );
        rootScreen.viewAction.add(panelNode);
    }

    public ArrayQueueScreen getRootScreen() {
        return (ArrayQueueScreen) rootScreen;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            createNewElement();
            animationStep++;
            System.out.println(getRootScreen().queue.size());
        } else if (animationStep == 1) {
            addToDataOfArrayQueue();
            animationStep++;
            System.out.println(getRootScreen().queue.size());
        } else {
            animationStep = 0;
            end();
            System.out.println(getRootScreen().queue.size() + "\n");
        }
    }

    public void createNewElement() {
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                -1500,
                0,
                10,
                period - 10
        );
    }

    public void addToDataOfArrayQueue() {
        rootScreen.viewAction.setComponentZOrder(panelNode, 0);
        getRootScreen().queue.enqueue(panelNode);
        ServiceAnimation.translate(
                panelNode,
                new Location(panelNode.getX(), panelNode.getY()),
                0,
                y -panelNode.getY(),
                10,
                period - 10
        );
        if (getRootScreen().queue.size() == 1) {
            ((ViewArrayQueueAction) getRootScreen().viewAction).panelFirst.setVisible(true);
        }
    }
}