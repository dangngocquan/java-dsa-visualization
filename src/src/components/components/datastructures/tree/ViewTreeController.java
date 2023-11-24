package src.components.components.datastructures.tree;

import src.Config;
import src.components.base.Button;
import src.components.base.Dialog;
import src.components.base.Panel;
import src.components.components.AbstractScreen;
import src.components.components.datastructures.AbstractViewDataStructureController;

public class ViewTreeController extends AbstractViewDataStructureController {
    private int tempIndexActionSelecting;

    public ViewTreeController(AbstractTreeScreen rootScreen) {
        super(rootScreen);
    }

    public AbstractTreeScreen getRootScreen() {
        return (AbstractTreeScreen) rootScreen;
    }

    @Override
    public void addActionListenerForButtons() {
        // Back
        buttons[0].addActionListener(e -> {
            AbstractScreen mainTreeScreen =
                    getApp().getScreens().get("MainTreeScreen");
            rootScreen.setHidden(true);
            mainTreeScreen.setHidden(false);
        });

        buttons[1].addActionListener(e -> {
            tempIndexActionSelecting = -1;
            new DialogChangeAction(
                    (Config.WIDTH - Config.WIDTH / 3 * 2) / 2,
                    (Config.HEIGHT - Config.HEIGHT / 10 * 9) / 2,
                    Config.WIDTH / 3 * 2,
                    Config.HEIGHT / 10 * 9,
                    "Setting Actions"
            );
        });

        buttons[2].addActionListener(e -> {
            rootScreen.runAction();
        });
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
            int numberButtonPerColumn = AbstractTreeScreen.ACTIONS.size() + 2;
            int numberButtonPerRow = 1;
            int buttonWidth = 450;
            int buttonHeight = 40;
            int gapHeight = 20;
            int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
            int totalWidth = buttonWidth * numberButtonPerRow;
            int initialY = (getHeightDialog() - totalHeight) / 2;
            int initialX = (getWidthDialog() - totalWidth) / 2;

            this.buttons = new Button[AbstractTreeScreen.ACTIONS.size() + 1];
            this.buttons[0] = new Button(
                    initialX,
                    initialY + (gapHeight + buttonHeight) * (numberButtonPerColumn - 1),
                    buttonWidth, buttonHeight,
                    "Save"
            );

            for (int i = 0; i < AbstractTreeScreen.ACTIONS.size(); i++) {
                buttons[i+1] = new Button(
                        initialX,
                        initialY + (gapHeight + buttonHeight) * i,
                        buttonWidth, buttonHeight,
                        AbstractTreeScreen.ACTIONS.get(i)
                );
                buttons[i+1].setHasStatusClicked(true);
            }

            for (Button button : buttons) dialog.add(button);

            boolean[] enableButtons = new boolean[] {
                    !getRootScreen().tree.isEmpty(),
                    !getRootScreen().tree.isEmpty(),
                    !getRootScreen().tree.isEmpty(),
                    !getRootScreen().tree.isEmpty(),
                    !getRootScreen().tree.isEmpty(),
                    !getRootScreen().tree.isEmpty(),
                    getRootScreen().tree.size() < 31,
                    !getRootScreen().tree.isEmpty()
            };

            for (int i = 0; i < AbstractTreeScreen.ACTIONS.size(); i++) this.buttons[i+1].setEnabledButton(enableButtons[i]);
        }

        public void referAllButtons() {
            for (Button button : buttons) button.setIsClicked(false);
        }

        public void addActionListenerForButtons() {
            // Save
            this.buttons[0].addActionListener(e -> {
                rootScreen.setIndexActionSelected(tempIndexActionSelecting);
                dialog.dispose();
            });

            this.buttons[1].addActionListener(e -> {
                referAllButtons(); buttons[1].setIsClicked(true); tempIndexActionSelecting = 0; });
            this.buttons[2].addActionListener(e -> {
                referAllButtons(); buttons[2].setIsClicked(true); tempIndexActionSelecting = 1; });
            this.buttons[3].addActionListener(e -> {
                referAllButtons(); buttons[3].setIsClicked(true); tempIndexActionSelecting = 2; });
            this.buttons[4].addActionListener(e -> {
                referAllButtons(); buttons[4].setIsClicked(true); tempIndexActionSelecting = 3; });
            this.buttons[5].addActionListener(e -> {
                referAllButtons(); buttons[5].setIsClicked(true); tempIndexActionSelecting = 4; });
            this.buttons[6].addActionListener(e -> {
                referAllButtons(); buttons[6].setIsClicked(true); tempIndexActionSelecting = 5; });
            this.buttons[7].addActionListener(e -> {
                referAllButtons(); buttons[7].setIsClicked(true); tempIndexActionSelecting = 6; });
            this.buttons[8].addActionListener(e -> {
                referAllButtons(); buttons[8].setIsClicked(true); tempIndexActionSelecting = 7; });
        }
    }
}