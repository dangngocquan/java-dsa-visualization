package src.components.components.datastructures.tree.actionanimation;

import src.Config;
import src.components.components.datastructures.AbstractDataStructureScreen;
import src.components.components.datastructures.tree.AbstractTreeAnimation;
import src.components.components.datastructures.tree.AbstractTreeScreen;
import src.components.components.datastructures.tree.TreePanelNode;
import src.models.datastructures.queue.LinkedQueue;
import src.models.datastructures.queue.QueueInterface;
import src.models.datastructures.stack.LinkedStack;
import src.models.datastructures.stack.StackInterface;
import src.models.datastructures.tree.LinkedBinaryTree;
import src.services.ServiceAnimation;

import java.util.Iterator;

public class TreeActionTraversal extends AbstractTreeAnimation {
    public int typeTraversal;
    /*
    * 0: left-root-right
    * 1: root-left-right
    * 2: left-right-root
    * 3: right-root-left
    * 4: right-left-root
    * 5: root-right-left
    * */

    public QueueInterface<LinkedBinaryTree.Node<TreePanelNode>> queueTraversal;
    public QueueInterface<LinkedBinaryTree.Node<TreePanelNode>> queueCheck;
    public StackInterface<LinkedBinaryTree.Node<TreePanelNode>> stack;

    public TreeActionTraversal(
            int typeTraversal,
            AbstractDataStructureScreen rootScreen,
            int period,
            AbstractTreeAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.typeTraversal = typeTraversal;
        queueCheck = new LinkedQueue<>();
        queueTraversal = new LinkedQueue<>();
        stack = new LinkedStack<>();
        fillQueueCheck(getRootScreen().tree.root);
        fillQueueTraversal(getRootScreen().tree.root);
    }

    public AbstractTreeScreen getRootScreen() {
        return (AbstractTreeScreen) rootScreen;
    }

    @Override
    public void run() {
        if (!stack.isEmpty() && stack.top().element.compareTo(queueTraversal.first().element) == 0) {
            traversal();
        } else if (!queueCheck.isEmpty()) {
            check();
        } else {
            end();
        }
    }

    public void fillQueueCheck(LinkedBinaryTree.Node<TreePanelNode> node) {
        if (node == null) return;
        switch (typeTraversal) {
            case 0, 1, 2 -> {
                queueCheck.enqueue(node);
                fillQueueCheck(node.left);
                fillQueueCheck(node.right);
            }
            case 3, 4, 5 -> {
                queueCheck.enqueue(node);
                fillQueueCheck(node.right);
                fillQueueCheck(node.left);
            }
            default -> {}
        }
    }

    public void fillQueueTraversal(LinkedBinaryTree.Node<TreePanelNode> node) {
        if (node == null) return;
        switch (typeTraversal) {
            case 0 -> {
                fillQueueTraversal(node.left);
                queueTraversal.enqueue(node);
                fillQueueTraversal(node.right);
            }
            case 1 -> {
                queueTraversal.enqueue(node);
                fillQueueTraversal(node.left);
                fillQueueTraversal(node.right);
            }
            case 2 -> {
                fillQueueTraversal(node.left);
                fillQueueTraversal(node.right);
                queueTraversal.enqueue(node);
            }
            case 3 -> {
                fillQueueTraversal(node.right);
                queueTraversal.enqueue(node);
                fillQueueTraversal(node.left);
            }
            case 4 -> {
                fillQueueTraversal(node.right);
                fillQueueTraversal(node.left);
                queueTraversal.enqueue(node);
            }
            case 5 -> {
                queueTraversal.enqueue(node);
                fillQueueTraversal(node.right);
                fillQueueTraversal(node.left);
            }
            default -> {}
        }
    }



    public void traversal() {
        TreePanelNode panel = queueTraversal.dequeue().element;
        stack.pop();
        ServiceAnimation.transitionColor(
                panel,
                panel.getBackgroundColor(),
                Config.COLOR_BLUE,
                10, period - 10
        );
        animationStep = 0;
    }

    public void check() {
        TreePanelNode panel = queueCheck.first().element;
        ServiceAnimation.transitionColor(
                panel,
                panel.getBackgroundColor(),
                animationStep == 0? Config.COLOR_YELLOW : Config.COLOR_WHITE,
                10, period - 10
        );
        if (animationStep == 0) {
            animationStep = 1;
        } else {
            animationStep = 0;
            stack.push(queueCheck.dequeue());
        }
    }
}
