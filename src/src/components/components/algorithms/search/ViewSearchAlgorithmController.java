package src.components.components.algorithms.search;

import src.Config;
import src.components.components.AbstractScreen;
import src.components.components.algorithms.AbstractViewAlgorithmController;
import src.components.components.algorithms.search.binary.BinarySearchAlgorithmScreen;
import src.services.ServiceArray;
import src.services.ServiceGenerateRandom;
import src.components.base.Dialog;
import src.components.base.Button;
import src.components.base.Panel;
import src.components.base.TextField;

import java.awt.*;


public class ViewSearchAlgorithmController extends AbstractViewAlgorithmController {
    public Button[] buttons;
    public ViewSearchAlgorithmController(AbstractSearchAlgorithmScreen rootScreen) {
        super(rootScreen);
        addButtons();
        addActionListenerForButtons();
        repaint();
    }

    public AbstractSearchAlgorithmScreen getRootScreen() {
        return (AbstractSearchAlgorithmScreen) rootScreen;
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

        buttons = new Button[8];
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
                "Array size (20)"
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
                buttonWidth, buttonHeight,
                "Value searching (10)"
        );

        buttons[6] = new Button(
                initialX + (buttonWidth + gapWidth) * 3,
                initialY + (buttonHeight + gapHeight) ,
                buttonWidth, buttonHeight,
                "Animation Speed (500)"
        );

        buttons[7] = new Button(
                initialX + (buttonWidth + gapWidth) * 4,
                initialY ,
                buttonWidth, buttonHeight * 2 + gapHeight,
                "Run search"
        );


        for (Button button : buttons) add(button);
    }

    public void addActionListenerForButtons() {
        // Back
        buttons[0].addActionListener(e -> {
            AbstractScreen mainAlgorithmsScreen = getApp().getScreens().get("MainSearchAlgorithmsScreen");
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
            getRootScreen().setArray(
                    getRootScreen() instanceof BinarySearchAlgorithmScreen?
                            ServiceGenerateRandom.createRandomSortedArray(mainArray.length, 1, 100)
                            : ServiceGenerateRandom.createRandomArray(mainArray.length, 1, 100));
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
            widthDialog = Math.max(Config.WIDTH / 3, widthDialog);
            int heightDialog = heightHeader + heightFooter + (numberRow - 1) * gapRow + numberRow * heightPerRow;
            new DialogSetElements(
                    (Config.WIDTH - widthDialog) / 2,
                    (Config.HEIGHT - heightDialog) / 2,
                    widthDialog, heightDialog,
                    "Set elements"
            );
        });

        // Value Searching
        buttons[5].addActionListener(e -> {
            new DialogSetValueSearching(
                    (Config.WIDTH - Config.WIDTH/4) / 2,
                    (Config.HEIGHT - Config.HEIGHT/3) / 2,
                    Config.WIDTH/4,
                    Config.HEIGHT/3,
                    "Set value searching in range [1 - 99]"
            );
        });

        // Animation Speed
        buttons[6].addActionListener(e -> {
            new DialogSetAnimationSpeed(
                    (Config.WIDTH - Config.WIDTH/4) / 2,
                    (Config.HEIGHT - Config.HEIGHT/3) / 2,
                    Config.WIDTH/4,
                    Config.HEIGHT/3,
                    "Set animation speed in range [3 - ?] milliseconds"
            );
        });

        // Run/Pause sort
        buttons[7].addActionListener(e -> {
            if (getRootScreen() instanceof BinarySearchAlgorithmScreen
                    && !ServiceArray.isSortedArray(getRootScreen().array)) {
                new DialogNotifyUnsortedArray();
                return;
            }
            if (getRootScreen().searchAnimation == null) {
                getRootScreen().startSearch();
                setEnabledAllButtons(false);
                buttons[7].setEnabledButton(true);
            } else {
                if (getRootScreen().searchAnimation.isRunning()) {
                    setEnabledAllButtons(true);
                    getRootScreen().pauseSearch();
                } else if (getRootScreen().searchAnimation.isPaused()) {
                    setEnabledAllButtons(false);
                    buttons[7].setEnabledButton(true);
                    getRootScreen().continueSearch();
                }
            }
        });
    }

    public void setEnabledAllButtons(boolean enabled) {
        for (Button button : buttons) button.setEnabledButton(enabled);
    }

    public void startSearch() {
        buttons[7].setText("Pause search");
    }

    public void pauseSearch() {
        buttons[7].setText("Continue search");
    }

    public void continueSearch() {
        buttons[7].setText("Pause search");
    }

    public void endSearch() {
        buttons[7].setText("Start search");
        setEnabledAllButtons(true);
    }

    private static class DialogNotifyUnsortedArray extends Dialog {
        public DialogNotifyUnsortedArray() {
            super((Config.WIDTH - Config.WIDTH/3) / 2,
                    (Config.HEIGHT - Config.HEIGHT/3) / 2,
                    Config.WIDTH/3,
                    Config.HEIGHT/3, "Invalid array");
        }

        @Override
        public void addComponents() {
            Panel panel = new Panel(
                    0, 30, getWidthDialog(), 50,
                    dialog.getBackground(), null,
                    "Binary search only can execute with sorted array.", 0
            );
            panel.setFont(Config.MONOSPACED_BOLD_16);
            Button button = new Button(
                    getWidthDialog() / 2 - 50, panel.getY() + panel.getHeightPanel() + 30,
                    100, 50, "OK"
            );
            button.addActionListener(e -> dialog.dispose());

            dialog.add(panel);
            dialog.add(button);
        }
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
                if (data.matches("[0-9]+")) {
                    try {
                        inputSize = Integer.parseInt(data);
                    } catch (Exception exception) {
                        inputSize = 20;
                    }
                    if (inputSize > 128) inputSize = 128;
                    if (inputSize < 2) inputSize = 2;
                } else {
                    inputSize = 20;
                }
                dialog.dispose();
                if (inputSize != getRootScreen().array.length) {
                    getRootScreen().setArray(
                            getRootScreen() instanceof BinarySearchAlgorithmScreen?
                                    ServiceGenerateRandom.createRandomSortedArray(inputSize, 1, 100)
                                    : ServiceGenerateRandom.createRandomArray(inputSize, 1, 100));
                    buttons[3].setText("Array size (" + inputSize + ")");
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
                if (data.matches("[0-9]+")) {
                    try {
                        inputAnimationPeriod = Integer.parseInt(data);
                    } catch (Exception exception) {
                        inputAnimationPeriod = 200;
                    }
                    if (inputAnimationPeriod < 3) inputAnimationPeriod = 3;
                } else {
                    inputAnimationPeriod = 200;
                }
                dialog.dispose();
                if (inputAnimationPeriod != getRootScreen().getViewAction().animationPeriod) {
                    getRootScreen().getViewAction().setAnimationPeriod(inputAnimationPeriod);
                    buttons[6].setText("Animation Speed (" + getRootScreen().getViewAction().getAnimationPeriod() + ")");
                    getRootScreen().getViewAction().setElements(getRootScreen().array);
                }
            });
        }
    }

    private class DialogSetValueSearching extends DialogWithFieldText {

        public DialogSetValueSearching(int x, int y, int width, int height, String title) {
            super(x, y, width, height, title);
        }

        @Override
        public void addListeners() {
            button.addActionListener(e -> {
                String data = textField.getText();
                int inputValueSearching = 0;
                if (data.matches("[0-9]+")) {
                    try {
                        inputValueSearching = Integer.parseInt(data);
                    } catch (Exception exception) {
                        inputValueSearching = 10;
                    }
                    if (inputValueSearching < 1) inputValueSearching = 1;
                    if (inputValueSearching > 99) inputValueSearching = 99;
                } else {
                    inputValueSearching = 10;
                }
                dialog.dispose();
                if (inputValueSearching != getRootScreen().valueSearching) {
                    getRootScreen().setValueSearching(inputValueSearching);
                    buttons[5].setText("Value searching (" + getRootScreen().valueSearching + ")");
                    getRootScreen().getViewAction().setElements(getRootScreen().array);
                }
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
                panels[i].setFont(Config.MONOSPACED_BOLD_16);
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