package src.components.components.datastructures.list.abstractlistscreen;

import src.App;
import src.Config;
import src.components.base.Button;
import src.components.base.Dialog;
import src.components.base.Panel;
import src.components.components.AbstractScreen;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;

public class ViewController extends Panel {
    public Button[] buttons;
    private AbstractListScreen rootScreen;
    private int tempIndexActionSelecting;

    public ViewController(
            int x, int y, int width, int height,
            Color backgroundColor, ImageIcon backgroundImage,
            String text, int shadowSize, AbstractListScreen rootScreen) {
        super(x, y, width, height, backgroundColor, backgroundImage, text, shadowSize);

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

    public void addActionListenerForButtons() {
        // Back
        buttons[0].addActionListener(e -> {
            AbstractScreen mainListScreen =
                    getApp().getScreens().get("MainListScreen");
            AbstractScreen arrayListScreen =
                    getApp().getScreens().get("ArrayListScreen");
            arrayListScreen.setHidden(true);
            mainListScreen.setHidden(false);
        });

        buttons[1].addActionListener(e -> {
            boolean[] enableButtons = new boolean[] {
                    rootScreen.list.size() < 8,
                    rootScreen.list.size() < 8,
                    rootScreen.list.size() > 0,
                    rootScreen.list.size() > 0,
                    rootScreen.list.size() > 0
            };
            tempIndexActionSelecting = -1;
            new DialogChangeAction(
                    (Config.WIDTH - Config.WIDTH / 3) / 2,
                    (Config.HEIGHT - Config.HEIGHT / 10 * 9) / 2,
                    Config.WIDTH / 3,
                    Config.HEIGHT / 10 * 9,
                    "Setting Actions"
            );
        });

        buttons[2].addActionListener(e -> {
            rootScreen.runAction();
        });
    }

    public App getApp() {
        return (App) (Service.getFrame(this));
    }


    private class DialogChangeAction extends Dialog {
        private Button[] buttons;


        public DialogChangeAction(
                int x, int y, int width, int height,
                String title) {
            super(x, y, width, height, title);
        }

        @Override
        public void addComponents() {
            addButtons();
            addActionListenerForButtons();
        }

        private void addButtons() {
            int numberButtonPerColumn = AbstractListScreen.ACTIONS.size() + 2;
            int numberButtonPerRow = 1;
            int buttonWidth = 250;
            int buttonHeight = 50;
            int gapHeight = 20;
            int gapWidth = 40;
            int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
            int totalWidth = buttonWidth * numberButtonPerRow + (numberButtonPerRow - 1) * gapWidth;
            int initialY = (getHeightDialog() - totalHeight) / 2;
            int initialX = (getWidthDialog() - totalWidth) / 2;

            buttons = new Button[AbstractListScreen.ACTIONS.size() + 1];
            buttons[0] = new Button(
                    initialX + (gapWidth + buttonWidth) * (numberButtonPerRow - 1),
                    initialY + (gapHeight + buttonHeight) * (numberButtonPerColumn - 1),
                    buttonWidth, buttonHeight,
                    "Save"
            );

            for (int i = 0; i < AbstractListScreen.ACTIONS.size(); i++) {
                buttons[i+1] = new Button(
                        initialX,
                        initialY + (gapHeight + buttonHeight) * i,
                        buttonWidth, buttonHeight,
                        AbstractListScreen.ACTIONS.get(i)
                );
                buttons[i+1].setHasStatusClicked(true);
            }

            for (Button button : buttons) dialog.add(button);

            boolean[] enableButtons = new boolean[] {
                    rootScreen.list.size() < 8,
                    rootScreen.list.size() < 8,
                    rootScreen.list.size() > 0,
                    rootScreen.list.size() > 0,
                    rootScreen.list.size() > 0
            };

            for (int i = 0; i < AbstractListScreen.ACTIONS.size(); i++) buttons[i+1].setEnabledButton(enableButtons[i]);

        }

        public void referAllButtons() {
            for (Button button : buttons) button.setIsClicked(false);
        }

        public void addActionListenerForButtons() {
            // Save
            buttons[0].addActionListener(e -> {
                rootScreen.setIndexActionSelected(tempIndexActionSelecting);
                dialog.dispose();
            });

            buttons[1].addActionListener(e -> {
                referAllButtons(); buttons[1].setIsClicked(true); tempIndexActionSelecting = 0; });
            buttons[2].addActionListener(e -> {
                referAllButtons(); buttons[2].setIsClicked(true); tempIndexActionSelecting = 1; });
            buttons[3].addActionListener(e -> {
                referAllButtons(); buttons[3].setIsClicked(true); tempIndexActionSelecting = 2; });
            buttons[4].addActionListener(e -> {
                referAllButtons(); buttons[4].setIsClicked(true); tempIndexActionSelecting = 3; });
            buttons[5].addActionListener(e -> {
                referAllButtons(); buttons[5].setIsClicked(true); tempIndexActionSelecting = 4; });
        }
    }
}
