package src.components.components.datastructures.list;

import src.Config;
import src.components.base.Button;
import src.components.base.Dialog;
import src.components.base.TextField;
import src.components.components.datastructures.AbstractDataStructureScreen;
import src.models.datastructures.list.MyList;

import java.awt.*;
import java.util.Map;

public abstract class AbstractListScreen extends AbstractDataStructureScreen {
    public static final Map<Integer, String> ACTIONS = Map.of(
            0, "add(value)",
            1, "add(index, value)",
            2, "get(index)",
            3, "remove(index)",
            4, "remove(value)"
    );
    protected int index = -1;
    protected int value = -1;
    public MyList<AbstractPanelListNode> list;

    public AbstractListScreen() {
        super();
    }

    public AbstractViewListAction getViewAction() {
        return (AbstractViewListAction) viewAction;
    }

    public abstract void createList();

    @Override
    public void createViewController() {
        createList();
        viewController = new ViewListController(this);
        add(viewController);
    }

    @Override
    public void setIndexActionSelected(int indexActionSelected) {
        this.indexActionSelected = indexActionSelected;
        switch (indexActionSelected) {
            case 0, 4 -> new DialogGetValue(
                    (Config.WIDTH - 300) / 2,
                    (Config.HEIGHT - 300) / 2,
                    300, 300
            );
            case 1 -> {
                new DialogGetIndex(
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
            case 2, 3 -> new DialogGetIndex(
                    (Config.WIDTH - 300) / 2,
                    (Config.HEIGHT - 300) / 2,
                    300, 300
            );
            default -> {
            }
        }
        if (indexActionSelected == -1) {
            viewController.buttons[1].setText("Choose Action");
            viewController.buttons[2].setEnabledButton(false);
        } else {
            viewController.buttons[1].setText(
                ACTIONS.get(indexActionSelected)
                        .replace("index", "index=" + index)
                        .replace("value", "value=" + value)
            );
            viewController.buttons[2].setEnabledButton(true);
        }
    }

    @Override
    public void runAction() {
        setEnabledAllButtons(false);
        switch (indexActionSelected) {
            case 0 -> getViewAction().actionAdd(value);
            case 1 -> getViewAction().actionAdd(index, value);
            case 2 -> getViewAction().actionGet(index);
            case 3 -> getViewAction().actionRemove(index);
            case 4 -> getViewAction().actionRemove(Integer.valueOf(value));
            default -> {
            }
        }
    }

    private class DialogGetIndex extends DialogWithFieldText {

        public DialogGetIndex(int x, int y, int width, int height) {
            super(
                    x, y, width, height,
                    String.format(
                            "Set index in range [ 0 - %d]",
                            indexActionSelected == 1? list.size() : list.size()-1
                    )
            );
        }

        @Override
        public void addListeners() {
            button.addActionListener(e -> {
                String data = textField.getText();
                if (data.matches("[0-9]+")) {
                    index = Integer.parseInt(data);
                    if (indexActionSelected == 1) {
                        if (index > list.size()) index = list.size();
                    } else {
                        if (index >= list.size()) index = list.size()-1;
                    }
                } else {
                    index = 0;
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
            int totalWidth = buttonWidth * numberObjectPerRow + (numberObjectPerRow - 1) * gapWidth;
            int initialY = (getHeightDialog() - totalHeight) / 2;
            int initialX = (getWidthDialog() - totalWidth) / 2;

            button = new Button(
                    initialX + (gapWidth + buttonWidth) * (numberObjectPerRow - 1),
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
