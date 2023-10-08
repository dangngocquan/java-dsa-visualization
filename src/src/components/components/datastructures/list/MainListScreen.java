package src.components.components.datastructures.list;

import src.Config;
import src.components.base.Button;
import src.components.components.AbstractScreen;
import src.components.components.datastructures.list.arraylist.ArrayListScreen;

import javax.swing.*;
import java.awt.*;

public class MainListScreen extends AbstractScreen {
    public MainListScreen(int x, int y, int width, int height, Color backgroundColor, ImageIcon backgroundImage, String text) {
        super(x, y, width, height, backgroundColor, backgroundImage, text);
    }

    @Override
    public void addButtons() {
        int numberButtonPerColumn = 5;
        int numberButtonPerRow = 1;
        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapHeight = 40;
        int gapWidth = 40;
        int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
        int totalWidth = buttonWidth * numberButtonPerRow + (numberButtonPerRow - 1) * gapWidth;
        int initialY = (Config.HEIGHT - totalHeight) / 2;
        int initialX = (Config.WIDTH - totalWidth) / 2;

        buttons = new src.components.base.Button[5];
        buttons[0] = new src.components.base.Button(
                initialX + (gapWidth + buttonWidth) * (numberButtonPerRow - 1),
                initialY + (gapHeight + buttonHeight) * (numberButtonPerColumn - 1),
                buttonWidth, buttonHeight,
                "Back"
        );
        buttons[1] = new Button(
                initialX,
                initialY,
                buttonWidth, buttonHeight,
                "Array List"
        );
        buttons[2] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight),
                buttonWidth, buttonHeight,
                "Singly Linked List"
        );
        buttons[3] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * 2,
                buttonWidth, buttonHeight,
                "Double Linked List"
        );

        buttons[4] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * 3,
                buttonWidth, buttonHeight,
                "Circular Linked List"
        );

        add(buttons[0]);
        add(buttons[1]);
        add(buttons[2]);
        add(buttons[3]);
        add(buttons[4]);
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
                screens[0] = getApp().getScreens().get("MainDataStructuresScreen");
            }
            setHidden(true);
            screens[0].setHidden(false);
        });

        // Array List
        buttons[1].addActionListener(e -> {
            if (screens[1] == null) {
                screens[1] = new ArrayListScreen(
                        0, 0, Config.WIDTH, Config.HEIGHT,
                        Config.BACKGROUND_COLOR_APP, null
                );
                screens[1].setVisible(false);
                getApp().addScreen(screens[1]);
            }
            setHidden(true);
            screens[1].setHidden(false);
        });
    }
}
