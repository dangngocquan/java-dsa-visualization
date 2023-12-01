package src.components.components.datastructures.queue.arrayqueue.actionanimation;

import src.components.base.Panel;
import src.components.components.datastructures.queue.AbstractQueueAnimation;
import src.components.components.datastructures.queue.AbstractQueueScreen;
import src.components.components.datastructures.queue.arrayqueue.ArrayQueuePanelNode;
import src.components.components.datastructures.queue.arrayqueue.ArrayQueueScreen;
import src.components.components.datastructures.queue.arrayqueue.ViewArrayQueueAction;
import src.models.datastructures.queue.ArrayQueue;
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
            AbstractQueueAnimation nextAnimation) {
        super(rootScreen, nextAnimation);
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
            animationStep = 1;
        } else if (animationStep == 1) {
            addToDataOfArrayQueue();
            animationStep = (getRootScreen().queue.size() > 1)? 3 : 2;
        } else if (animationStep == 2) {
            updatePanelFirst();
            animationStep = 3;
        } else {
            animationStep = 0;
            end();
        }
    }

    public void createNewElement() {
        getRootScreen().setDescription(
                String.format(
                        "[CREATE] Create Node node = new Node(%d)", value
                )
        );
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
        getRootScreen().setDescription(
                String.format(
                        "[ENQUEUE] Enqueue new element %d to queue", value
                )
        );
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
    }

    public void updatePanelFirst() {
        Panel panelFirst = ((ViewArrayQueueAction) getRootScreen().viewAction).panelFirst;
        panelFirst.setVisible(true);
        ServiceAnimation.translate(
                panelFirst,
                new Location(panelFirst.getX(), panelFirst.getY()),
                getRootScreen().queue.first().getX() - panelFirst.getX(),
                0,
                10, period - 10
        );

    }
}