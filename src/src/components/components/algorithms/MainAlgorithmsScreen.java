package src.components.components.algorithms;

import src.Config;
import src.components.base.Button;
import src.components.components.AbstractScreen;
import src.components.components.algorithms.sort.MainSortAlgorithmsScreen;

public class MainAlgorithmsScreen extends AbstractScreen {
    public MainAlgorithmsScreen() {
        super();
    }

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
                initialX + (gapWidth + buttonWidth) * (numberButtonPerRow - 1),
                initialY + (gapHeight + buttonHeight) * (numberButtonPerColumn - 1),
                buttonWidth, buttonHeight,
                "Back"
        );
        buttons[1] = new Button(
                initialX,
                initialY,
                buttonWidth, buttonHeight,
                "Sorting Algorithms"
        );

        add(buttons[0]);
        add(buttons[1]);
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

        // Sorting Algorithms
        buttons[1].addActionListener(e -> {
            if (screens[1] == null) {
                screens[1] = new MainSortAlgorithmsScreen();
                screens[1].setVisible(false);
                getApp().addScreen(screens[1]);
            }
            setHidden(true);
            screens[1].setHidden(false);
        });
    }
}
