package src.components.components.datastructures.list.abstractlistscreen;

import src.Config;
import src.components.base.Button;
import src.components.base.Dialog;
import src.components.base.TextField;
import src.components.components.AbstractScreen;
import src.models.datastructures.list.MyList;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public abstract class AbstractListScreen extends AbstractScreen {
    public static final Map<Integer, String> ACTIONS = Map.of(
            0, "add(value)",
            1, "add(index, value)",
            2, "get(index)",
            3, "remove(index)",
            4, "remove(value)"
    );
    public ViewController viewController;
    public AbstractViewListAction viewAction;
    protected int indexActionSelected = -1;
    protected int index = -1;
    protected int value = -1;
    public MyList<Integer> list;

    public AbstractListScreen(
            int x, int y, int width, int height,
            Color backgroundColor, ImageIcon backgroundImage) {
        super(x, y, width, height, backgroundColor, backgroundImage, "");

        createList();
        addViewController();
        addViewAction();
        repaint();
    }

    public abstract void createList();

    public void addViewController() {
        viewController = new ViewController(
                0, Config.HEIGHT / 7 * 6,
                Config.WIDTH, Config.HEIGHT - Config.HEIGHT / 7 * 6,
                Config.COLOR_GRAY_2, null, "", 0, this
        );
        add(viewController);
    }

    public abstract void addViewAction();

    public void setIndexActionSelected(int indexActionSelected) {
        this.indexActionSelected = indexActionSelected;
        switch (indexActionSelected) {
            case 0:
            case 4:
                new DialogGetValue(
                        (Config.WIDTH - 300) / 2,
                        (Config.HEIGHT - 300) / 2,
                        300, 300
                );
                break;
            case 1:
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
                break;
            case 2:
            case 3:
                new DialogGetIndex(
                        (Config.WIDTH - 300) / 2,
                        (Config.HEIGHT - 300) / 2,
                        300, 300
                );
                break;
            default:
                break;

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
    public void addButtons() {}

    @Override
    public void createDefaultScreens() {}

    @Override
    public void addActionListenerForButtons() {}

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
                if (data.matches("[0-9]{1,}")) {
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
                if (data.matches("[0-9]{1,}")) {
                    value = Integer.parseInt(data);
                    if (value > 99) value = 99;
                } else {
                    value = 0;
                }
                dialog.dispose();
            });
        }
    }

    private abstract class DialogWithFieldText extends Dialog {
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

    public void setEnabledAllButtons(boolean isEnabled) {
        for (Button button : viewController.buttons) button.setEnabledButton(isEnabled);
    }

    public void runAction() {
        setEnabledAllButtons(false);
        switch (indexActionSelected) {
            case 0:
                viewAction.actionAdd(value);
                break;
            case 1:
                viewAction.actionAdd(index, value);
                break;
            case 2:
                viewAction.actionGet(index);
                break;
            case 3:
                viewAction.actionRemove(index);
                break;
            case 4:
                viewAction.actionRemove(Integer.valueOf(value));
                break;
            default:
                break;
        }
    }

    public void endAction() {
        viewController.buttons[0].setEnabledButton(true);
        viewController.buttons[1].setEnabledButton(true);
        setIndexActionSelected(-1);
    };
}
