package src.components.components.datastructures.stack;

import src.Config;
import src.components.base.Button;
import src.components.base.Dialog;
import src.components.components.AbstractScreen;
import src.components.components.datastructures.AbstractViewDataStructureController;
import src.components.components.datastructures.list.arraylist.ArrayListScreen;
import src.components.components.datastructures.stack.arraystack.ArrayStackScreen;

public class ViewStackController extends AbstractViewDataStructureController {
    private int tempIndexActionSelecting;

    public ViewStackController(AbstractStackScreen rootScreen) {
        super(rootScreen);
    }

    public AbstractStackScreen getRootScreen() {
        return (AbstractStackScreen) rootScreen;
    }

    @Override
    public void addActionListenerForButtons() {
        // Back
        buttons[0].addActionListener(e -> {
            AbstractScreen mainStackScreen =
                    getApp().getScreens().get("MainStackScreen");
            rootScreen.setHidden(true);
            mainStackScreen.setHidden(false);
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
            int numberButtonPerColumn = AbstractStackScreen.ACTIONS.size() + 2;
            int numberButtonPerRow = 1;
            int buttonWidth = 250;
            int buttonHeight = 50;
            int gapHeight = 20;
            int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
            int totalWidth = buttonWidth * numberButtonPerRow;
            int initialY = (getHeightDialog() - totalHeight) / 2;
            int initialX = (getWidthDialog() - totalWidth) / 2;

            this.buttons = new Button[AbstractStackScreen.ACTIONS.size() + 1];
            this.buttons[0] = new Button(
                    initialX,
                    initialY + (gapHeight + buttonHeight) * (numberButtonPerColumn - 1),
                    buttonWidth, buttonHeight,
                    "Save"
            );

            for (int i = 0; i < AbstractStackScreen.ACTIONS.size(); i++) {
                buttons[i+1] = new Button(
                        initialX,
                        initialY + (gapHeight + buttonHeight) * i,
                        buttonWidth, buttonHeight,
                        AbstractStackScreen.ACTIONS.get(i)
                );
                buttons[i+1].setHasStatusClicked(true);
            }

            for (Button button : buttons) dialog.add(button);

            boolean[] enableButtons = new boolean[] {
                    getRootScreen().stack.size() < (rootScreen instanceof ArrayStackScreen ? Config.MAX_NODE_TYPE_1 : Config.MAX_NODE_TYPE_2),
                    !getRootScreen().stack.isEmpty(),
            };

            for (int i = 0; i < AbstractStackScreen.ACTIONS.size(); i++) this.buttons[i+1].setEnabledButton(enableButtons[i]);
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
        }
    }
}
