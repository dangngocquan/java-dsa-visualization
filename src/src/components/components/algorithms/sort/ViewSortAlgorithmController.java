package src.components.components.algorithms.sort;

import src.Config;
import src.components.base.Button;
import src.components.base.Dialog;
import src.components.base.Panel;
import src.components.base.TextField;
import src.components.components.AbstractScreen;
import src.components.components.algorithms.AbstractViewAlgorithmController;
import src.services.ServiceArray;
import src.services.ServiceGenerateRandom;

import java.awt.*;

public class ViewSortAlgorithmController extends AbstractViewAlgorithmController {
    public Button[] buttons;
    public ViewSortAlgorithmController(AbstractSortAlgorithmScreen rootScreen) {
        super(rootScreen);
        addButtons();
        addActionListenerForButtons();
        repaint();
    }

    public AbstractSortAlgorithmScreen getRootScreen() {
        return (AbstractSortAlgorithmScreen) rootScreen;
    }

    public void addButtons() {
        int numberButtonPerColumn = 2;
        int numberButtonPerRow = 5;
        int buttonWidth = 250;
        int buttonHeight = 40;
        int gapHeight = 10;
        int gapWidth = 40;
        int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
        int totalWidth = buttonWidth * numberButtonPerRow + (numberButtonPerRow - 1) * gapWidth;
        int initialY = (getHeightPanel() - totalHeight) / 2;
        int initialX = (getWidthPanel() - totalWidth) / 2;

        buttons = new Button[7];
        buttons[0] = new Button(
                initialX ,
                initialY ,
                buttonWidth, buttonHeight * 2 + gapHeight,
                "Back"
        );
        buttons[1] = new Button(
                initialX + (buttonWidth + gapWidth),
                initialY,
                buttonWidth, buttonHeight,
                "Shuffle"
        );

        buttons[2] = new Button(
                initialX + (buttonWidth + gapWidth),
                initialY + (buttonHeight + gapHeight),
                buttonWidth, buttonHeight,
                "Generate New"
        );

        buttons[3] = new Button(
                initialX + (buttonWidth + gapWidth) * 2,
                initialY,
                buttonWidth, buttonHeight,
                "Array size"
        );

        buttons[4] = new Button(
                initialX + (buttonWidth + gapWidth) * 2,
                initialY + (buttonHeight + gapHeight),
                buttonWidth, buttonHeight,
                "Elements"
        );

        buttons[5] = new Button(
                initialX + (buttonWidth + gapWidth) * 3,
                initialY ,
                buttonWidth, buttonHeight * 2 + gapHeight,
                "Animation Speed"
        );

        buttons[6] = new Button(
                initialX + (buttonWidth + gapWidth) * 4,
                initialY ,
                buttonWidth, buttonHeight * 2 + gapHeight,
                "Run sort"
        );


        for (Button button : buttons) add(button);
    }

    public void addActionListenerForButtons() {
        // Back
        buttons[0].addActionListener(e -> {
            AbstractScreen mainAlgorithmsScreen = getApp().getScreens().get("MainSortAlgorithmsScreen");
            rootScreen.setHidden(true);
            mainAlgorithmsScreen.setHidden(false);
        });

        // Shuffle Array
        buttons[1].addActionListener(e -> {
            int[] mainArray = getRootScreen().array.clone();
            ServiceArray.shuffleArray(mainArray);
            getRootScreen().setArray(mainArray);
        });

        // Generate New Array
        buttons[2].addActionListener(e -> {
            int[] mainArray = getRootScreen().array;
            getRootScreen().setArray(ServiceGenerateRandom.createRandomArray(mainArray.length, 1, 100));
        });

        // Edit Size Array
        buttons[3].addActionListener(e -> {
            new DialogGetSizeArray(
                    (Config.WIDTH - Config.WIDTH/4) / 2,
                    (Config.HEIGHT - Config.HEIGHT/3) / 2,
                    Config.WIDTH/4,
                    Config.HEIGHT/3,
                    "Set size of array in range [2-128]"
            );
        });

        // Edit Elements
        buttons[4].addActionListener(e -> {
            int gapColumn = 10;
            int gapRow = 10;
            int heightHeader = 60;
            int heightFooter = 80;
            int initialX = 50;
            int widthPerColumn = (Config.WIDTH - 100 - 7 * gapColumn) / 8;
            int heightPerRow = (Config.HEIGHT - heightHeader - heightFooter - 15 * gapRow) / 16;
            int numberColumn = (getRootScreen().array.length + 15) / 16;
            int numberRow = Math.min(16, getRootScreen().array.length);
            int widthDialog = initialX * 2 + (numberColumn - 1) * gapColumn + numberColumn * widthPerColumn;
            int heightDialog = heightHeader + heightFooter + (numberRow - 1) * gapRow + numberRow * heightPerRow;
            new DialogSetElements(
                    (Config.WIDTH - widthDialog) / 2,
                    (Config.HEIGHT - heightDialog) / 2,
                    widthDialog, heightDialog,
                    "Set elements"
            );
        });

        // Animation Speed
        buttons[5].addActionListener(e -> {
            new DialogSetAnimationSpeed(
                    (Config.WIDTH - Config.WIDTH/4) / 2,
                    (Config.HEIGHT - Config.HEIGHT/3) / 2,
                    Config.WIDTH/4,
                    Config.HEIGHT/3,
                    "Set animation speed in range [2 - ?] milliseconds"
            );
        });

        // Run/Pause sort
        buttons[6].addActionListener(e -> {
            if (getRootScreen().sortAnimation == null) {
                getRootScreen().startSort();
                setEnabledAllButtons(false);
                buttons[6].setEnabledButton(true);
            } else {
                if (getRootScreen().sortAnimation.isRunning()) {
                    setEnabledAllButtons(true);
                    getRootScreen().pauseSort();
                } else if (getRootScreen().sortAnimation.isPaused()) {
                    setEnabledAllButtons(false);
                    buttons[6].setEnabledButton(true);
                    getRootScreen().continueSort();
                }
            }
        });
    }

    public void setEnabledAllButtons(boolean enabled) {
        for (Button button : buttons) button.setEnabledButton(enabled);
    }

    public void startSort() {
        buttons[6].setText("Pause sort");
    }

    public void pauseSort() {
        buttons[6].setText("Continue sort");
    }

    public void continueSort() {
        buttons[6].setText("Pause sort");
    }

    public void endSort() {
        buttons[6].setText("Start sort");
    }

    private class DialogGetSizeArray extends DialogWithFieldText {

        public DialogGetSizeArray(int x, int y, int width, int height, String title) {
            super(x, y, width, height, title);
        }

        @Override
        public void addListeners() {
            button.addActionListener(e -> {
                String data = textField.getText();
                int inputSize = 0;
                if (data.matches("[0-9]{1,}")) {
                    inputSize = Integer.parseInt(data);
                    if (inputSize > 128) inputSize = 128;
                    if (inputSize < 2) inputSize = 2;
                } else {
                    inputSize = 30;
                }
                dialog.dispose();
                if (inputSize != getRootScreen().array.length) {
                    getRootScreen().setArray(ServiceGenerateRandom.createRandomArray(inputSize, 1, 100));
                }
            });
        }
    }

    private class DialogSetAnimationSpeed extends DialogWithFieldText {

        public DialogSetAnimationSpeed(int x, int y, int width, int height, String title) {
            super(x, y, width, height, title);
        }

        @Override
        public void addListeners() {
            button.addActionListener(e -> {
                String data = textField.getText();
                int inputAnimationPeriod = 0;
                if (data.matches("[0-9]{1,}")) {
                    inputAnimationPeriod = Integer.parseInt(data);
                    if (inputAnimationPeriod < 2) inputAnimationPeriod = 2;
                } else {
                    inputAnimationPeriod = 200;
                }
                dialog.dispose();
                if (inputAnimationPeriod != getRootScreen().getViewAction().animationPeriod) {
                    getRootScreen().getViewAction().setAnimationPeriod(inputAnimationPeriod);
                    getRootScreen().getViewAction().setElements(getRootScreen().array);
                }
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
                    "", Color.WHITE, 1, 0, 0
            );

            dialog.add(button);
            dialog.add(textField);
        }

        public abstract void addListeners();
    }

    private class DialogSetElements extends Dialog {
        private Panel[] panels;
        private TextField[] textFields;
        private Button button;

        public DialogSetElements(int x, int y, int width, int height, String title) {
            super(x, y, width, height, title);
        }

        @Override
        public void addComponents() {
            createPanels();
            createTextFields();
            createButtons();
            addListeners();
            repaint();
        }

        public void createPanels() {
            panels = new Panel[getRootScreen().array.length + 1];

            int gapColumn = 10;
            int gapRow = 10;
            int heightHeader = 60;
            int heightFooter = 80;
            int initialX = 50;
            int initialY = 60;
            int widthPerColumn = (Config.WIDTH - 100 - 7 * gapColumn) / 8;
            int heightPerRow = (Config.HEIGHT - heightHeader - heightFooter - 15 * gapRow) / 16;

            panels[0] = new Panel(
                    initialX, 10,
                    getWidthDialog() - initialX * 2, 30,
                    dialog.getBackground(), null,
                    "Set value of each element in range [ 1 - 99 ]", 0
            );
            dialog.add(panels[0]);

            for (int i = 1; i < panels.length; i++) {
                int column = (i - 1) / 16;
                int row = (i - 1) % 16;
                panels[i] = new Panel(
                        initialX + column * (widthPerColumn + gapColumn),
                        initialY + row * (heightPerRow + gapRow),
                        widthPerColumn/3*2, heightPerRow,
                        dialog.getBackground(), null,
                        String.format("%-5s = ", String.format("a[%d]", i-1)),
                        0
                );
                dialog.add(panels[i]);
            }
        }

        public void createTextFields() {
            textFields = new TextField[getRootScreen().array.length + 1];
            for (int i = 1; i < panels.length; i++) {
                textFields[i] = new TextField(
                        panels[i].getX() + panels[i].getWidthPanel(),
                        panels[i].getY(),
                        panels[i].getWidthPanel() / 3,
                        panels[i].getHeightPanel(),
                        getRootScreen().array[i-1] + "",
                        Config.COLOR_BAR_PLAIN,
                        1, 0, 0
                );

                dialog.add(textFields[i]);
            }
        }

        public void createButtons() {
            button = new Button(
                    (getWidthDialog() - 200) / 2,
                    getHeightDialog() - 60, 200, 40,
                    "Save"
            );
            dialog.add(button);
        }

        public void addListeners() {
            button.addActionListener(e -> {
                int[] a = new int[getRootScreen().array.length];
                for (int i = 0; i < a.length; i++) {
                    String data = textFields[i+1].getText();
                    if (data.matches("[0-9]+")) {
                        int value = Integer.parseInt(data);
                        if (value > 99) {
                            value = 99;
                        } else if (value < 1) {
                            value = 1;
                        }
                        a[i] = value;
                    } else {
                        a[i] = 50;
                    }
                }
                getRootScreen().setArray(a);
                dialog.dispose();
            });
        }
    }
}
