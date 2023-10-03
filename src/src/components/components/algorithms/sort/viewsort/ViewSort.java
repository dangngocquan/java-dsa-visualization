package src.components.components.algorithms.sort.viewsort;

import src.App;
import src.Config;
import src.components.base.Panel;
import src.components.components.algorithms.sort.SortAlgorithmScreen;
import src.services.animation.Animation;
import src.services.animation.Location;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;

public class ViewSort extends Panel {
    public static int gapWidth = 2;
    public static int barWidth = 10;
    public static int initialX = 10;
    public static int initialY = 10;
    public static int[] xBars;
    public static int[] elements;
    public static Bar[] bars;

    public ViewSort(
            int x, int y, int width, int height,
            Color backgroundColor, ImageIcon backgroundImage,
            String text, int shadowSize, int[] array) {
        super(x, y, width, height, backgroundColor, backgroundImage, text, shadowSize);
        this.elements = array;
        updateStaticValues();
        addBars();
        repaint();
    }

    public void setElements(int[] array) {
        this.elements = array;
        updateStaticValues();
        addBars();
        repaint();
    }

    public void updateStaticValues() {
        int n = elements.length;
        initialX = 10;
        gapWidth = 4;
        barWidth = (getWidthPanel() - 2 * initialX - gapWidth * (n-1)) / n;
        initialY = (getHeightPanel() - 40) / 2 + 20;
        xBars = new int[n];
        for (int i = 0; i < n; i++) xBars[i] = initialX + i * (barWidth + gapWidth);
    }


    public void addBars() {
        removeAll();
        bars = new Bar[elements.length];
        for (int i = 0; i < bars.length; i++) {
            bars[i] = new Bar(i, elements[i]);
            add(bars[i]);
        }
    }

    public void checkBar(int i) {
        Animation.transitionColor(
                bars[i],
                bars[i].getBackgroundColor(),
                Config.COLOR_BAR_CHECKING,
                1,
                SortAlgorithmScreen.period - 1
                );
    }

    public void uncheckBar(int i) {
        Animation.transitionColor(
                bars[i],
                bars[i].getBackgroundColor(),
                Config.COLOR_BAR_PLAIN,
                1,
                SortAlgorithmScreen.period - 1
        );
    }

    public void swapBars(int i1, int i2) {
        Animation.translate(
                bars[i1],
                new Location(xBars[i1], initialY - (3* elements[i1] + 20)),
                (i2 - i1) * (barWidth + gapWidth),
                0,
                1,
                SortAlgorithmScreen.period - 1
        );
        Animation.translate(
                bars[i2],
                new Location(xBars[i2], initialY - (3* elements[i2] + 20)),
                (i1 - i2) * (barWidth + gapWidth),
                0,
                1,
                SortAlgorithmScreen.period - 1
        );
        Bar bar = bars[i1];
        bars[i1] = bars[i2];
        bars[i2] = bar;
        int value = elements[i1];
        elements[i1] = elements[i2];
        elements[i2] = value;
    }

    public void resetColorBars() {
        for (int i = 0; i < bars.length; i++) {
            bars[i].setBackgroundColor(Color.WHITE);
        }
        repaint();
    }

    public App getApp() {
        return (App) (Service.getFrame(this));
    }
}
