package src.components.components.datastructures;

import src.Config;
import src.components.base.Button;
import src.components.components.AbstractScreen;
import src.components.components.datastructures.list.MainListScreen;
import src.components.components.datastructures.priorityqueue.MainPriorityQueueScreen;
import src.components.components.datastructures.queue.MainQueueScreen;
import src.components.components.datastructures.stack.MainStackScreen;


public class MainDataStructuresScreen extends AbstractScreen {
    public MainDataStructuresScreen() {
        super();
    }

    @Override
    public void addButtons() {
        int numberButtonPerColumn = 6;
        int numberButtonPerRow = 1;
        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapHeight = 40;
        int gapWidth = 40;
        int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
        int totalWidth = buttonWidth * numberButtonPerRow + (numberButtonPerRow - 1) * gapWidth;
        int initialY = (Config.HEIGHT - totalHeight) / 2;
        int initialX = (Config.WIDTH - totalWidth) / 2;

        buttons = new Button[6];
        buttons[0] = new Button(
                initialX + (gapWidth + buttonWidth) * (numberButtonPerRow - 1),
                initialY + (gapHeight + buttonHeight) * (numberButtonPerColumn - 1),
                buttonWidth, buttonHeight,
                "Back"
        );
        buttons[1] = new Button(
                initialX,
                initialY,
                buttonWidth, buttonHeight,
                "List"
        );
        buttons[2] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight),
                buttonWidth, buttonHeight,
                "Stack"
        );
        buttons[3] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * 2,
                buttonWidth, buttonHeight,
                "Queue"
        );
        buttons[4] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * 3,
                buttonWidth, buttonHeight,
                "Priority Queue"
        );
        buttons[5] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * 4,
                buttonWidth, buttonHeight,
                "Tree"
        );

        for (Button button : buttons) add(button);
    }

    @Override
    public void createDefaultScreens() {
        screens = new AbstractScreen[6];
    }

    @Override
    public void addActionListenerForButtons() {
        // Back
        buttons[0].addActionListener(e -> {
            if (screens[0] == null) {
                screens[0] = getApp().getScreens().get("MainScreen");
            }
            setHidden(true);
            screens[0].setHidden(false);
        });

        // List
        buttons[1].addActionListener(e -> {
            if (screens[1] == null) {
                screens[1] = new MainListScreen();
                screens[1].setVisible(false);
                getApp().addScreen(screens[1]);
            }
            setHidden(true);
            screens[1].setHidden(false);
        });

        // Stack
        buttons[2].addActionListener(e -> {
            if (screens[2] == null) {
                screens[2] = new MainStackScreen();
                screens[2].setVisible(false);
                getApp().addScreen(screens[2]);
            }
            setHidden(true);
            screens[2].setHidden(false);
        });

        // Queue
        buttons[3].addActionListener(e -> {
            if (screens[3] == null) {
                screens[3] = new MainQueueScreen();
                screens[3].setVisible(false);
                getApp().addScreen(screens[3]);
            }
            setHidden(true);
            screens[3].setHidden(false);
        });

        // Priority Queue
        buttons[4].addActionListener(e -> {
            if (screens[4] == null) {
                screens[4] = new MainPriorityQueueScreen();
                screens[4].setVisible(false);
                getApp().addScreen(screens[4]);
            }
            setHidden(true);
            screens[4].setHidden(false);
        });
    }
}
