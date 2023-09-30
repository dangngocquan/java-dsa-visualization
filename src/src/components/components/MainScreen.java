package src.components.components;

import src.Config;
import src.components.base.Button;
import src.components.base.Panel;

import javax.swing.*;
import java.awt.*;

public class MainScreen extends Panel {
    private Button[] buttons;
    public MainScreen(
            int x, int y, int width, int height,
            Color backgroundColor,
            ImageIcon backgroundImage,
            String text) {
        super(x, y, width, height, backgroundColor, backgroundImage, text);
        addButtons();
    }

    public void addButtons() {
        buttons = new Button[2];
        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapHeight = 40;
        buttons[0] = new Button(
                (Config.WIDTH - buttonWidth) / 2,
                (Config.HEIGHT - buttonHeight) / 2 - (gapHeight + buttonHeight),
                buttonWidth, buttonHeight,
                "Data Structure"
        );
        buttons[1] = new Button(
                buttons[0].getX(),
                buttons[0].getY() + buttons[0].getHeight() + gapHeight,
                buttonWidth, buttonHeight,
                "Algorithms"
        );

        add(buttons[0]);
        add(buttons[1]);
        repaint();
    }
}
