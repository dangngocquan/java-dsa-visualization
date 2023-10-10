package src.components.components.datastructures;

import src.App;
import src.Config;
import src.components.base.Button;
import src.components.base.Panel;
import src.services.services.Service;


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
        int gapHeight = 40;
        int gapWidth = 40;
        int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
        int totalWidth = buttonWidth * numberButtonPerRow + (numberButtonPerRow - 1) * gapWidth;
        int initialY = (getHeightPanel() - totalHeight) / 2;
        int initialX = (getWidthPanel() - totalWidth) / 2;

        buttons = new src.components.base.Button[3];
        buttons[0] = new src.components.base.Button(
                initialX ,
                initialY + (gapHeight + buttonHeight) * (numberButtonPerColumn - 1),
                buttonWidth, buttonHeight,
                "Back"
        );
        buttons[1] = new Button(
                initialX + (gapWidth + buttonWidth) ,
                buttons[0].getY(),
                buttonWidth*3 + 2*gapWidth, buttonHeight,
                "Choose Action"
        );
        buttons[2] = new src.components.base.Button(
                initialX + (gapWidth + buttonWidth) * 4,
                buttons[0].getY(),
                buttonWidth, buttonHeight,
                "Run Action"
        );

        buttons[2].setEnabledButton(false);

        add(buttons[0]);
        add(buttons[1]);
        add(buttons[2]);
    }


    public abstract void addActionListenerForButtons();

    public App getApp() {
        return (App) (Service.getFrame(this));
    }
}
