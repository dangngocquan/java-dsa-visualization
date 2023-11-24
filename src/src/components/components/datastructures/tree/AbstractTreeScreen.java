package src.components.components.datastructures.tree;

import src.Config;
import src.components.base.Button;
import src.components.base.Dialog;
import src.components.base.TextField;
import src.components.components.datastructures.AbstractDataStructureScreen;
import src.models.datastructures.queue.LinkedQueue;
import src.models.datastructures.queue.QueueInterface;
import src.models.datastructures.tree.BinarySearchTree;
import src.models.datastructures.tree.LinkedBinaryTree;

import java.awt.*;
import java.util.Map;

public abstract class AbstractTreeScreen extends AbstractDataStructureScreen {
    public static final Map<Integer, String> ACTIONS = Map.of(
            0, "traversal left-root-right (in-order)",
            1, "traversal root-left-right (pre-order)",
            2, "traversal left-right-root (post-order)",
            3, "traversal right-root-left",
            4, "traversal right-left-root",
            5, "traversal root-right-left",
            6, "insert(value)",
            7, "delete(value)"
    );
    protected int value = -1;
    public BinarySearchTree<TreePanelNode> tree;

    public AbstractTreeScreen() {
        super();
    }

    public AbstractViewTreeAction getViewAction() {
        return (AbstractViewTreeAction) viewAction;
    }

    public abstract void createTree();


    @Override
    public void createViewController() {
        createTree();
        viewController = new ViewTreeController(this);
        add(viewController);
    }

    @Override
    public void setIndexActionSelected(int indexActionSelected) {
        this.indexActionSelected = indexActionSelected;
        if (indexActionSelected >= 6) {
            new DialogGetValue(
                    (Config.WIDTH - 300) / 2,
                    (Config.HEIGHT - 300) / 2,
                    300, 300
            );
        }
        if (indexActionSelected == -1) {
            viewController.buttons[1].setText("Choose Action");
            viewController.buttons[2].setEnabledButton(false);
        } else {
            viewController.buttons[1].setText(
                    ACTIONS.get(indexActionSelected)
                            .replace("value", "value=" + value)
            );
            viewController.buttons[2].setEnabledButton(true);
        }
    }

    @Override
    public void runAction() {
        setEnabledAllButtons(false);
        switch (indexActionSelected) {
            case 0, 1, 2, 3, 4, 5 -> getViewAction().actionTraversal(indexActionSelected);
            case 6 -> getViewAction().actionInsert(value);
            case 7 -> getViewAction().actionDelete(value);
            default -> {}
        }
    }

    @Override
    public void endAction() {
        viewController.buttons[0].setEnabledButton(true);
        viewController.buttons[1].setEnabledButton(true);
        setIndexActionSelected(-1);
    }

    public TreePanelNode[] getPanelNodeArray() {
        TreePanelNode[] array = new TreePanelNode[31];
        int i = 0;
        LinkedBinaryTree.Node<TreePanelNode> node = tree.root;
        QueueInterface<LinkedBinaryTree.Node<TreePanelNode>> queue = new LinkedQueue<>();
        queue.enqueue(node);
        while (i < 31) {
            if (queue.first() == null) {
                queue.enqueue(null);
                queue.enqueue(null);
                array[i++] = null;
                queue.dequeue();
            } else {
                queue.enqueue(queue.first().left);
                queue.enqueue(queue.first().right);
                array[i] = queue.dequeue().element;
                array[i].setXY(
                        AbstractViewTreeAction.getDefaultX(i),
                        AbstractViewTreeAction.getDefaultY(i)
                );
                i++;
            }
        }
        return array;
    }

    private class DialogGetValue extends DialogWithFieldText {
        public DialogGetValue(int x, int y, int width, int height) {
            super(x, y, width, height, "Set value in range [0-99]");
        }

        @Override
        public void addListeners() {
            button.addActionListener(e -> {
                String data = textField.getText();
                if (data.matches("[0-9]+")) {
                    value = Integer.parseInt(data);
                    if (value > 99) value = 99;
                } else {
                    value = 0;
                }
                dialog.dispose();
            });
        }
    }

    private abstract static class DialogWithFieldText extends Dialog {
        protected TextField textField;
        protected Button button;

        public DialogWithFieldText(
                int x, int y, int width, int height, String title) {
            super(x, y, width, height, title);
        }

        @Override
        public void addComponents() {
            addButtonAndTextField();
            addListeners();
        }

        public void addButtonAndTextField() {
            int numberObjectPerColumn = 2;
            int numberObjectPerRow = 1;
            int buttonWidth = 250;
            int buttonHeight = 50;
            int gapHeight = 20;
            int gapWidth = 40;
            int totalHeight = buttonHeight * numberObjectPerColumn + (numberObjectPerColumn - 1) * gapHeight;
            int totalWidth = buttonWidth * numberObjectPerRow;
            int initialY = (getHeightDialog() - totalHeight) / 2;
            int initialX = (getWidthDialog() - totalWidth) / 2;

            button = new Button(
                    initialX,
                    initialY + (gapHeight + buttonHeight) * (numberObjectPerColumn - 1),
                    buttonWidth, buttonHeight,
                    "Save"
            );

            textField = new TextField(
                    initialX, initialY,
                    buttonWidth, buttonHeight,
                    "0", Color.WHITE, 1, 0, 0
            );

            dialog.add(button);
            dialog.add(textField);
        }

        public abstract void addListeners();
    }
}
