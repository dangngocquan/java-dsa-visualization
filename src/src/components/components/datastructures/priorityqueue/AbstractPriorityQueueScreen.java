package src.components.components.datastructures.priorityqueue;

import src.Config;
import src.components.base.Button;
import src.components.base.Dialog;
import src.components.base.TextField;
import src.components.components.datastructures.AbstractDataStructureScreen;
import src.models.datastructures.priorityqueue.PriorityQueueInterface;

import java.awt.*;
import java.util.Map;

public abstract class AbstractPriorityQueueScreen extends AbstractDataStructureScreen {
    public static final Map<Integer, String> ACTIONS = Map.of(
            0, "insert(key, value)",
            1, "removeMin()"
    );
    protected Integer key = -1;
    protected Integer value = -1;
    public PriorityQueueInterface<Integer, AbstractPanelPriorityQueueNode> queue;

    public AbstractPriorityQueueScreen() {
        super();
    }

    public AbstractViewPriorityQueueAction getViewAction() {
        return (AbstractViewPriorityQueueAction) viewAction;
    }

    public abstract void createPriorityQueue();

    @Override
    public void createViewController() {
        createPriorityQueue();
        viewController = new ViewPriorityQueueController(this);
        add(viewController);
    }

    @Override
    public void setIndexActionSelected(int indexActionSelected) {
        this.indexActionSelected = indexActionSelected;
        if (indexActionSelected == 0) {
            new DialogGetKey(
                    (Config.WIDTH - 300) / 2,
                    (Config.HEIGHT - 300) / 2,
                    300, 300
            );
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
                            .replace("key", "key=" + key)
                            .replace("value", "value=" + value)
            );
            viewController.buttons[2].setEnabledButton(true);
        }
    }

    @Override
    public void runAction() {
        setEnabledAllButtons(false);
        switch (indexActionSelected) {
            case 0 -> getViewAction().actionInsert(key, value);
            case 1 -> getViewAction().actionRemoveMin();
            default -> {}
        }
    }

    private class DialogGetKey extends DialogWithFieldText {
        public DialogGetKey(int x, int y, int width, int height) {
            super(x, y, width, height, "Set key in range [0-99]");
        }

        @Override
        public void addListeners() {
            button.addActionListener(e -> {
                String data = textField.getText();
                if (data.matches("[0-9]+")) {
                    key = Integer.parseInt(data);
                    if (key > 99) key = 99;
                } else {
                    key = 1;
                }
                dialog.dispose();
            });
        }
    }

    private class DialogGetValue extends DialogWithFieldText {
        public DialogGetValue(int x, int y, int width, int height) {
            super(x, y, width, height, "Set value in range [0-99]");
        }

        @Override
        public void addListeners() {
            button.addActionListener(e -> {
                String data;
                data = textField.getText();
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
            int totalHeight = buttonHeight * numberObjectPerColumn + (numberObjectPerColumn - 1) * gapHeight;
            int totalWidth = buttonWidth * numberObjectPerRow + 1 - 1;
            int initialY = (getHeightDialog() - totalHeight) / 2;
            int initialX = (getWidthDialog() - totalWidth) / 2;

            button = new Button(
                    initialX,
                    initialY + (gapHeight + buttonHeight) * (numberObjectPerColumn - 1),
                    buttonWidth, buttonHeight,
                    "Save" + " "
            );

            textField = new TextField(
                    initialX, initialY,
                    buttonWidth, buttonHeight,
                    "0", Color.WHITE, 1, 0, 0
            );

            dialog.add(textField);
            dialog.add(button);

        }

        public abstract void addListeners();
    }
}