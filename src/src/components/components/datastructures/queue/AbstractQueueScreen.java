package src.components.components.datastructures.queue;

import src.Config;
import src.components.base.Button;
import src.components.base.Dialog;
import src.components.base.TextField;
import src.components.components.datastructures.AbstractDataStructureScreen;
import src.models.datastructures.queue.QueueInterface;

import java.awt.*;
import java.util.Map;

public abstract class AbstractQueueScreen extends AbstractDataStructureScreen {
    public static final Map<Integer, String> ACTIONS = Map.of(
            0, "enqueue(value)",
            1, "dequeue()"
    );
    protected int value = -1;
    public QueueInterface<AbstractPanelQueueNode> queue;

    public AbstractQueueScreen() {
        super();
    }

    public AbstractViewQueueAction getViewAction() {
        return (AbstractViewQueueAction) viewAction;
    }

    public abstract void createQueue();

    @Override
    public void createViewController() {
        createQueue();
        viewController = new ViewQueueController(this);
        add(viewController);
    }

    @Override
    public void setIndexActionSelected(int indexActionSelected) {
        this.indexActionSelected = indexActionSelected;
        if (indexActionSelected == 0) {
            new AbstractQueueScreen.DialogGetValue(
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
            case 0 -> getViewAction().actionEnqueue(value);
            case 1 -> getViewAction().actionDequeue();
            default -> {}
        }
    }

    private class DialogGetValue extends AbstractQueueScreen.DialogWithFieldText {
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