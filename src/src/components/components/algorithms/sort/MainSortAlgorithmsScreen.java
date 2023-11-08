package src.components.components.algorithms.sort;

import src.Config;
import src.components.base.Button;
import src.components.components.AbstractScreen;
import src.components.components.algorithms.sort.bubblesort.BubbleSortAlgorithmScreen;
import src.components.components.algorithms.sort.heapsort.HeapSortAlgorithmScreen;
import src.components.components.algorithms.sort.insertionsort.InsertionSortAlgorithmScreen;
import src.components.components.algorithms.sort.mergesort.MergeSortAlgorithmScreen;
import src.components.components.algorithms.sort.quicksort.QuickSortAlgorithmScreen;
import src.components.components.algorithms.sort.selectionsort.SelectionSortAlgorithmScreen;

public class MainSortAlgorithmsScreen extends AbstractScreen {
    @Override
    public void addButtons() {
        int numberButtonPerColumn = 7;
        int numberButtonPerRow = 1;
        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapHeight = 40;
        int gapWidth = 40;
        int totalHeight = buttonHeight * numberButtonPerColumn + (numberButtonPerColumn - 1) * gapHeight;
        int totalWidth = buttonWidth * numberButtonPerRow + (numberButtonPerRow - 1) * gapWidth;
        int initialY = (Config.HEIGHT - totalHeight) / 2;
        int initialX = (Config.WIDTH - totalWidth) / 2;

        buttons = new src.components.base.Button[7];
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
                "Bubble Sort"
        );
        buttons[2] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight),
                buttonWidth, buttonHeight,
                "Selection Sort"
        );
        buttons[3] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * 2,
                buttonWidth, buttonHeight,
                "Insertion Sort"
        );
        buttons[4] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * 3,
                buttonWidth, buttonHeight,
                "Quick Sort"
        );
        buttons[5] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * 4,
                buttonWidth, buttonHeight,
                "Merge Sort"
        );

        buttons[6] = new Button(
                initialX,
                initialY + (gapHeight + buttonHeight) * 5,
                buttonWidth, buttonHeight,
                "Heap Sort"
        );

        for (Button button : buttons) add(button);
    }

    @Override
    public void createDefaultScreens() {
        screens = new AbstractScreen[7];
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

        // Bubble Sort
        buttons[1].addActionListener(e -> {
            if (screens[1] == null) {
                screens[1] = new BubbleSortAlgorithmScreen();
                screens[1].setVisible(false);
                getApp().addScreen(screens[1]);
            }
            setHidden(true);
            screens[1].setHidden(false);
        });

        // Selection Sort
        buttons[2].addActionListener(e -> {
            if (screens[2] == null) {
                screens[2] = new SelectionSortAlgorithmScreen();
                screens[2].setVisible(false);
                getApp().addScreen(screens[2]);
            }
            setHidden(true);
            screens[2].setHidden(false);
        });

        // Insertion Sort
        buttons[3].addActionListener(e -> {
            if (screens[3] == null) {
                screens[3] = new InsertionSortAlgorithmScreen();
                screens[3].setVisible(false);
                getApp().addScreen(screens[3]);
            }
            setHidden(true);
            screens[3].setHidden(false);
        });

        // Quick Sort
        buttons[4].addActionListener(e -> {
            if (screens[4] == null) {
                screens[4] = new QuickSortAlgorithmScreen();
                screens[4].setVisible(false);
                getApp().addScreen(screens[4]);
            }
            setHidden(true);
            screens[4].setHidden(false);
        });

        // Merge Sort
        buttons[5].addActionListener(e -> {
            if (screens[5] == null) {
                screens[5] = new MergeSortAlgorithmScreen();
                screens[5].setVisible(false);
                getApp().addScreen(screens[5]);
            }
            setHidden(true);
            screens[5].setHidden(false);
        });

        // Heap Sort
        buttons[6].addActionListener(e -> {
            if (screens[6] == null) {
                screens[6] = new HeapSortAlgorithmScreen();
                screens[6].setVisible(false);
                getApp().addScreen(screens[6]);
            }
            setHidden(true);
            screens[6].setHidden(false);
        });
    }
}
