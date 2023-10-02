package src.components.components.algorithms.sort.viewcontroller;

import src.App;
import src.components.base.Button;
import src.components.base.Panel;
import src.components.components.algorithms.sort.SortAlgorithmScreen;
import src.components.components.algorithms.sort.sortanimations.BubbleSort;
import src.components.components.algorithms.sort.sortanimations.SortAnimation;
import src.components.components.algorithms.sort.viewsort.ViewSort;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

public class ViewController extends Panel {
    private Button[] buttons;
    private SortAnimation sortAnimation;
    public ViewController(int x, int y, int width, int height, Color backgroundColor, ImageIcon backgroundImage, String text, int shadowSize) {
        super(x, y, width, height, backgroundColor, backgroundImage, text, shadowSize);
        addButtons();
        addActionListenerForButtons();
        repaint();
    }

    public void addButtons() {
        buttons = new Button[2];

        int initialX = 25;
        int initialY = 50;
        int tempY = initialY;
        int gapY = 40;
        int buttonWidth = 250;
        int buttonHeight = 50;

        buttons[0] = new Button(
                initialX, tempY, buttonWidth, buttonHeight, "Back"
        );
        tempY += buttonHeight + gapY;

        buttons[1] = new Button(
                initialX, tempY, buttonWidth, buttonHeight, "Run sort"
        );

        add(buttons[0]);
        add(buttons[1]);
    }

    public void addActionListenerForButtons() {
        // Back
        buttons[0].addActionListener(e -> {
            getApp().getScreens().get("MainAlgorithmsScreen").setHidden(false);
            getApp().getScreens().get("SortAlgorithmScreen").setHidden(true);
        });

        buttons[1].addActionListener(e -> {
            SortAlgorithmScreen sortAlgorithmScreen =
                    ((SortAlgorithmScreen) getApp().getScreens().get("SortAlgorithmScreen"));
            ViewSort viewSort = sortAlgorithmScreen.getViewSort();
            sortAlgorithmScreen.timer = new Timer();
            sortAnimation = new BubbleSort(
                    viewSort, viewSort.bars,
                    sortAlgorithmScreen.timer,
                    viewSort.getMillisPerAction());
            sortAnimation.start();
        });
    }

    public App getApp() {
        return (App) (Service.getFrame(this));
    }

}
