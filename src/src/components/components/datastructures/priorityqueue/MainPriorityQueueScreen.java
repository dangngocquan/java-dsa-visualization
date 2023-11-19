package src.components.components.datastructures.priorityqueue;

import src.Config;
import src.components.base.Button;
import src.components.components.AbstractScreen;
import src.components.components.datastructures.priorityqueue.minheappriorityqueue.MinHeapPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.sortedarraypriorityqueue.SortedArrayPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.sortedlinkedpriorityqueue.SortedLinkedPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.unsortedarraypriorityqueue.UnsortedArrayPriorityQueueScreen;
import src.components.components.datastructures.priorityqueue.unsortedlinkedpriorityqueue.UnsortedLinkedPriorityQueueScreen;

public class MainPriorityQueueScreen extends AbstractScreen {
    @Override
    public void addButtons() {
        int numberButtonPerColumn = 6;
        int numberButtonPerRow = 1;
        int buttonWidth = 400;
        int buttonHeight = 50;
        int gapHeight = 40 + 1 - 1;
        int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
        int totalWidth = buttonWidth * numberButtonPerRow;
        int initialY = (Config.HEIGHT - totalHeight) / 2;
        int initialX = (Config.WIDTH - totalWidth) / 2;

        buttons = new Button[6];
        buttons[0] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * (numberButtonPerColumn - 1),
                buttonWidth, buttonHeight,
                "Back"
        );
        buttons[1] = new Button(
                initialX,
                initialY,
                buttonWidth, buttonHeight,
                "Unsorted Array Priority Queue"
        );
        buttons[2] = new Button(
                initialX,
                initialY  + (gapHeight + buttonHeight),
                buttonWidth, buttonHeight,
                "Sorted Array Priority Queue"
        );
        buttons[3] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * 2,
                buttonWidth, buttonHeight,
                "Unsorted Linked Priority Queue"
        );
        buttons[4] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * 3,
                buttonWidth, buttonHeight,
                "Sorted Linked Priority Queue"
        );
        buttons[5] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * 4,
                buttonWidth, buttonHeight,
                "Min Heap Priority Queue"
        );

        for (Button button : buttons) add(button);
    }

    @Override
    public void createDefaultScreens() {
        screens = new AbstractScreen[6];
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

        // Unsorted Array Priority Queue
        buttons[1].addActionListener(e -> {
            if (screens[1] == null) {
                screens[1] = new UnsortedArrayPriorityQueueScreen();
                screens[1].setVisible(false);
                getApp().addScreen(screens[1]);
            }
            setHidden(true);
            screens[1].setHidden(false);
        });

        // Unsorted Array Priority Queue
        buttons[2].addActionListener(e -> {
            if (screens[2] == null) {
                screens[2] = new SortedArrayPriorityQueueScreen();
                screens[2].setVisible(false);
                getApp().addScreen(screens[2]);
            }
            setHidden(true);
            screens[2].setHidden(false);
        });

        // Unsorted Linked Priority Queue
        buttons[3].addActionListener(e -> {
            if (screens[3] == null) {
                screens[3] = new UnsortedLinkedPriorityQueueScreen();
                screens[3].setVisible(false);
                getApp().addScreen(screens[3]);
            }
            setHidden(true);
            screens[3].setHidden(false);
        });

        // Sorted Linked Priority Queue
        buttons[4].addActionListener(e -> {
            if (screens[4] == null) {
                screens[4] = new SortedLinkedPriorityQueueScreen();
                screens[4].setVisible(false);
                getApp().addScreen(screens[4]);
            }
            setHidden(true);
            screens[4].setHidden(false);
        });

        // Min Heap Priority Queue
        buttons[5].addActionListener(e -> {
            if (screens[5] == null) {
                screens[5] = new MinHeapPriorityQueueScreen();
                screens[5].setVisible(false);
                getApp().addScreen(screens[5]);
            }
            setHidden(true);
            screens[5].setHidden(false);
        });
    }
}