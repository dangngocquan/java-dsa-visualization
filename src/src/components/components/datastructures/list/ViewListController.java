package src.components.components.datastructures.list;

import src.Config;
import src.components.base.Button;
import src.components.base.Dialog;
import src.components.components.AbstractScreen;
import src.components.components.datastructures.AbstractViewDataStructureController;

public class ViewListController extends AbstractViewDataStructureController {
    private int tempIndexActionSelecting;

    public ViewListController(AbstractListScreen rootScreen) {
        super(rootScreen);
    }

    public AbstractListScreen getRootScreen() {
        return (AbstractListScreen) rootScreen;
    }

    @Override
    public void addActionListenerForButtons() {
        // Back
        buttons[0].addActionListener(e -> {
            AbstractScreen mainListScreen =
                    getApp().getScreens().get("MainListScreen");
            rootScreen.setHidden(true);
            mainListScreen.setHidden(false);
        });

        buttons[1].addActionListener(e -> {
            tempIndexActionSelecting = -1;
            new DialogChangeAction(
                    (Config.WIDTH - Config.WIDTH / 3) / 2,
                    (Config.HEIGHT - Config.HEIGHT / 10 * 9) / 2,
                    Config.WIDTH / 3,
                    Config.HEIGHT / 10 * 9,
                    "Setting Actions"
            );
        });

        buttons[2].addActionListener(e -> rootScreen.runAction());
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
            int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
            int totalWidth = buttonWidth * numberButtonPerRow;
            int initialY = (getHeightDialog() - totalHeight) / 2;
            int initialX = (getWidthDialog() - totalWidth) / 2;

            buttons = new Button[AbstractListScreen.ACTIONS.size() + 1];
            buttons[0] = new Button(
                    initialX,
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
                    getRootScreen().list.size() < 8,
                    getRootScreen().list.size() < 8,
                    !getRootScreen().list.isEmpty(),
                    !getRootScreen().list.isEmpty(),
                    !getRootScreen().list.isEmpty()
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
