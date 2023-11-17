package src.components.components.datastructures.queue;

import src.Config;
import src.components.base.Button;
import src.components.components.AbstractScreen;
import src.components.components.datastructures.queue.arrayqueue.ArrayQueueScreen;
import src.components.components.datastructures.queue.linkedqueue.LinkedQueueScreen;

public class MainQueueScreen extends AbstractScreen {
    @Override
    public void addButtons() {
        int numberButtonPerColumn = 3;
        int numberButtonPerRow = 1;
        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapHeight = 40;
        int gapWidth = 40;
        int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
        int totalWidth = buttonWidth * numberButtonPerRow;
        int initialY = (Config.HEIGHT - totalHeight) / 2;
        int initialX = (Config.WIDTH - totalWidth) / 2;

        buttons = new src.components.base.Button[3];
        buttons[0] = new src.components.base.Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * (numberButtonPerColumn - 1),
                buttonWidth, buttonHeight,
                "Back"
        );
        buttons[1] = new Button(
                initialX,
                initialY,
                buttonWidth, buttonHeight,
                "Array Queue"
        );
        buttons[2] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight),
                buttonWidth, buttonHeight,
                "Linked Queue"
        );

        for (Button button : buttons) add(button);
    }

    @Override
    public void createDefaultScreens() {
        screens = new AbstractScreen[3];
    }

    @Override
    public void addActionListenerForButtons() {
        // Back
        buttons[0].addActionListener(e -> {
            if (screens[0] == null) {
                screens[0] = getApp().getScreens().get("MainDataStructuresScreen");
            }
            setHidden(true);
            screens[0].setHidden(false);
        });

        // Array Queue
        buttons[1].addActionListener(e -> {
            if (screens[1] == null) {
                screens[1] = new ArrayQueueScreen();
                screens[1].setVisible(false);
                getApp().addScreen(screens[1]);
            }
            setHidden(true);
            screens[1].setHidden(false);
        });

        // Linked Queue
        buttons[2].addActionListener(e -> {
            if (screens[2] == null) {
                screens[2] = new LinkedQueueScreen();
                screens[2].setVisible(false);
                getApp().addScreen(screens[2]);
            }
            setHidden(true);
            screens[2].setHidden(false);
        });
    }
}
