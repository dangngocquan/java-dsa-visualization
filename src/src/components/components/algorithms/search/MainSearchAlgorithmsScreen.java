package src.components.components.algorithms.search;

import src.Config;
import src.components.base.Button;
import src.components.components.AbstractScreen;
import src.components.components.algorithms.search.binary.BinarySearchAlgorithmScreen;
import src.components.components.algorithms.search.sequential.SequentialSearchAlgorithmScreen;

public class MainSearchAlgorithmsScreen extends AbstractScreen {
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
                "Sequential Search"
        );
        buttons[2] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight),
                buttonWidth, buttonHeight,
                "Binary Search"
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
                screens[0] = getApp().getScreens().get("MainAlgorithmsScreen");
            }
            setHidden(true);
            screens[0].setHidden(false);
        });

        // Sequential Search
        buttons[1].addActionListener(e -> {
            if (screens[1] == null) {
                screens[1] = new SequentialSearchAlgorithmScreen();
                screens[1].setVisible(false);
                getApp().addScreen(screens[1]);
            }
            setHidden(true);
            screens[1].setHidden(false);
        });

        // Binary Search
        buttons[2].addActionListener(e -> {
            if (screens[2] == null) {
                screens[2] = new BinarySearchAlgorithmScreen();
                screens[2].setVisible(false);
                getApp().addScreen(screens[2]);
            }
            setHidden(true);
            screens[2].setHidden(false);
        });
    }
}