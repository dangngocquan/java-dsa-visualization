package src.components.components.datastructures;

import src.App;
import src.Config;
import src.components.base.Button;
import src.components.base.Dialog;
import src.components.base.Panel;
import src.components.base.TextField;
import src.services.ServiceComponent;

import java.awt.*;


public abstract class AbstractViewDataStructureController extends Panel {
    public Button[] buttons;
    public AbstractDataStructureScreen rootScreen;


    public AbstractViewDataStructureController(AbstractDataStructureScreen rootScreen) {
        super(
                0, Config.HEIGHT - Config.HEIGHT_VIEW_CONTROLLER,
                Config.WIDTH, Config.HEIGHT_VIEW_CONTROLLER,
                Config.COLOR_GRAY_1, null, "", 0
        );
        this.rootScreen = rootScreen;
        addButtons();
        addActionListenerForButtons();
        repaint();
    }

    public void addButtons() {
        int numberButtonPerColumn = 1;
        int numberButtonPerRow = 5;
        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapWidth = 40;
        int totalHeight = buttonHeight * numberButtonPerColumn;
        int totalWidth = buttonWidth * numberButtonPerRow + (numberButtonPerRow - 1) * gapWidth;
        int initialY = (getHeightPanel() - totalHeight) / 2;
        int initialX = (getWidthPanel() - totalWidth) / 2;

        buttons = new src.components.base.Button[4];
        buttons[0] = new src.components.base.Button(
                initialX ,
                initialY,
                buttonWidth, buttonHeight,
                "Back"
        );
        buttons[1] = new Button(
                initialX + (gapWidth + buttonWidth) * 2,
                buttons[0].getY(),
                buttonWidth * 2 + gapWidth, buttonHeight,
                "Choose Action"
        );
        buttons[2] = new src.components.base.Button(
                initialX + (gapWidth + buttonWidth) * 4,
                buttons[0].getY(),
                buttonWidth, buttonHeight,
                "Run Action"
        );

        buttons[3] = new Button(
                initialX + (gapWidth + buttonWidth),
                buttons[0].getY(),
                buttonWidth, buttonHeight,
                "Animation Speed (" + getPeriod() + ")"
        );

        buttons[2].setEnabledButton(false);
        buttons[3].addActionListener(e ->
            new DialogSetAnimationSpeed(
                    (Config.WIDTH - Config.WIDTH/4) / 2,
                    (Config.HEIGHT - Config.HEIGHT/3) / 2,
                    Config.WIDTH/4,
                    Config.HEIGHT/3,
                    String.format(
                            "Set animation speed in range [%d - 10000] milliseconds",
                            Config.MIN_DATASTRUCTURES_ANIMATION_SPEED)
            )
        );

        add(buttons[0]);
        add(buttons[1]);
        add(buttons[2]);
        add(buttons[3]);
    }


    public abstract void addActionListenerForButtons();

    public int getPeriod() {
        return rootScreen.getPeriod();
    }

    public void setPeriod(int period) {
        rootScreen.setPeriod(period);
    }

    public App getApp() {
        return (App) (ServiceComponent.getFrame(this));
    }

    private class DialogSetAnimationSpeed extends DialogWithFieldText {

        public DialogSetAnimationSpeed(int x, int y, int width, int height, String title) {
            super(x, y, width, height, title);
        }

        @Override
        public void addListeners() {
            button.addActionListener(e -> {
                String data = textField.getText();
                int inputAnimationPeriod;
                if (data.matches("[0-9]+")) {
                    try {
                        inputAnimationPeriod = Integer.parseInt(data);
                    } catch (Exception exception) {
                        inputAnimationPeriod = Config.DEFAULT_ANIMATION_SPEED;
                    }
                    if (inputAnimationPeriod < Config.MIN_DATASTRUCTURES_ANIMATION_SPEED)
                        inputAnimationPeriod = Config.MIN_DATASTRUCTURES_ANIMATION_SPEED;
                    if (inputAnimationPeriod > 10000) inputAnimationPeriod = 10000;
                } else {
                    inputAnimationPeriod = Config.DEFAULT_ANIMATION_SPEED;
                }
                dialog.dispose();
                if (inputAnimationPeriod != getPeriod()) {
                    setPeriod(inputAnimationPeriod);
                    buttons[3].setText("Animation Speed (" + getPeriod() + ")");
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
}
