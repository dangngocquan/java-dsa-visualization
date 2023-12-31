package src.components.components;

import src.Config;
import src.components.base.Button;
import src.components.components.algorithms.MainAlgorithmsScreen;
import src.components.components.datastructures.MainDataStructuresScreen;


public class MainScreen extends AbstractScreen {
    @Override
    public void addButtons() {
        int numberButtonPerColumn = 2;
        int numberButtonPerRow = 1;
        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapHeight = 40;
        int gapWidth = 40;
        int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
        int totalWidth = buttonWidth * numberButtonPerRow + (numberButtonPerRow - 1) * gapWidth;
        int initialY = (Config.HEIGHT - totalHeight) / 2;
        int initialX = (Config.WIDTH - totalWidth) / 2;

        buttons = new Button[2];
        buttons[0] = new Button(
                initialX,
                initialY,
                buttonWidth, buttonHeight,
                "Data Structures"
        );
        buttons[1] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight),
                buttonWidth, buttonHeight,
                "Algorithms"
        );

        add(buttons[0]);
        add(buttons[1]);
    }

    @Override
    public void createDefaultScreens() {
        screens = new AbstractScreen[2];
    }

    @Override
    public void addActionListenerForButtons() {
        buttons[0].addActionListener(e -> {
            if (screens[0] == null) {
                screens[0] = new MainDataStructuresScreen();
                screens[0].setVisible(false);
                getApp().addScreen(screens[0]);
            }
            setHidden(true);
            screens[0].setHidden(false);
        });

        buttons[1].addActionListener(e -> {
            if (screens[1] == null) {
                screens[1] = new MainAlgorithmsScreen();
                screens[1].setVisible(false);
                getApp().addScreen(screens[1]);
            }
            setHidden(true);
            screens[1].setHidden(false);
        });
    }
}
