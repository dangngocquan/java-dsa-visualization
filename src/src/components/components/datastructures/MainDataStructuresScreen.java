package src.components.components.datastructures;

import src.Config;
import src.components.base.Button;
import src.components.components.AbstractScreen;
import src.components.components.datastructures.list.MainListScreen;


public class MainDataStructuresScreen extends AbstractScreen {
    public MainDataStructuresScreen() {
        super();
    }

    @Override
    public void addButtons() {
        int numberButtonPerColumn = 4;
        int numberButtonPerRow = 1;
        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapHeight = 40;
        int gapWidth = 40;
        int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
        int totalWidth = buttonWidth * numberButtonPerRow + (numberButtonPerRow - 1) * gapWidth;
        int initialY = (Config.HEIGHT - totalHeight) / 2;
        int initialX = (Config.WIDTH - totalWidth) / 2;

        buttons = new Button[4];
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

        add(buttons[0]);
        add(buttons[1]);
        add(buttons[2]);
        add(buttons[3]);
    }

    @Override
    public void createDefaultScreens() {
        screens = new AbstractScreen[5];
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
    }
}
