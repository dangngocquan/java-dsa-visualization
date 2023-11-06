package src.components.components.algorithms.sort;

import src.Config;
import src.components.components.algorithms.AbstractViewAlgorithmAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.awt.*;

public class ViewSortAlgorithmAction extends AbstractViewAlgorithmAction {
    public static int gapWidth = 2;
    public static int barWidth = 10;
    public static int initialX = 10;
    public static int initialY = 10;
    public static int initialY0;
    public static int initialY1;
    public int[] xBars;
    public int[] elements;
    public Bar[] bars;
    public int animationPeriod = 500;

    public ViewSortAlgorithmAction(AbstractSortAlgorithmScreen rootScreen, int[] array) {
        super(rootScreen);
        this.elements = array;
        updateStaticValues();
        addBars();
        repaint();
    }

    public AbstractSortAlgorithmScreen getRootScreen() {
        return (AbstractSortAlgorithmScreen) rootScreen;
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
        initialY = 20;
        initialY0 = initialY + (height - 2 * initialY) / 2;
        initialY1 = initialY0 + (height - 2 * initialY) / 2;
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

    public void setBar(int i, Bar bar) {
        bars[i] = bar;
        elements[i] = bar.getValue();
        repaint();
    }

    public int getAnimationPeriod() {
        return animationPeriod;
    }

    public void setAnimationPeriod(int animationPeriod) {
        if (animationPeriod != this.animationPeriod) {
            this.animationPeriod = animationPeriod;
            getRootScreen().endSort();
        }
    }

    public void checkBar(int i, Color targetColor) {
        ServiceAnimation.transitionColor(
                bars[i],
                bars[i].getBackgroundColor(),
                targetColor,
                1,
                getAnimationPeriod()-1
                );
    }

    public void swapBars(int i1, int yBase1, int i2, int yBase2) {
        ServiceAnimation.translate(
                bars[i1],
                new Location(xBars[i1], getYBar(i1, yBase1)),
                (i2 - i1) * (barWidth + gapWidth),
                0,
                1,
                getAnimationPeriod()-1
        );
        ServiceAnimation.translate(
                bars[i2],
                new Location(xBars[i2], getYBar(i2, yBase2)),
                (i1 - i2) * (barWidth + gapWidth),
                0,
                1,
                getAnimationPeriod()-1
        );
        Bar bar = bars[i1];
        bars[i1] = bars[i2];
        bars[i2] = bar;
        int value = elements[i1];
        elements[i1] = elements[i2];
        elements[i2] = value;
    }

    public void pickUpBar(int i, int yBase) {
        ServiceAnimation.translate(
                bars[i],
                new Location(xBars[i], getYBar(i, yBase)),
                0,
                (getHeightPanel() - 2 * initialY) / 2,
                1,
                getAnimationPeriod() - 1
        );
    }

    public void pickDownBar(int i, int yBase) {
        ServiceAnimation.translate(
                bars[i],
                new Location(xBars[i], getYBar(i, yBase)),
                0,
                -(getHeightPanel() - 2 * initialY) / 2,
                1,
                getAnimationPeriod() - 1
        );
    }

    public void tempSortedBar(int i) {
        ServiceAnimation.transitionColor(
                bars[i],
                bars[i].getBackgroundColor(),
                Config.COLOR_BAR_TEMP_SORTED,
                1,
                getAnimationPeriod() - 1
        );
    }

    public void setColorAndLocationBars(int from, int to, Color color, int yBase) {
        for (int i = from; i <= to; i++) {
            bars[i].setBackgroundColor(color);
            bars[i].setY(getYBar(i, yBase));
        }
        repaint();
    }

    public int getYBar(int i, int yBase) {
        return yBase - (3 * elements[i] + 20);
    }

    public void runEndAnimation() {
        quickAnimationSetColor(
                0, bars.length - 1,
                Config.COLOR_BAR_DONE
        );
    }

    public void quickAnimationSetColor(int from, int to, Color color) {
        int delay = 1;
        int duration = 1;
        for (int i = from; i <= to; i++) {
            ServiceAnimation.transitionColor(
                    bars[i],
                    bars[i].getBackgroundColor(),
                    color,
                    delay,
                    duration
            );
            delay += duration + 1;
        }
    }

    public void moveBar(int i1, int yBase1, int i2, int yBase2) {
        ServiceAnimation.translate(
                bars[i1],
                new Location(xBars[i1], getYBar(i1, yBase1)),
                (i2 - i1) * (barWidth + gapWidth),
                getYBar(i1, yBase2) - getYBar(i1, yBase1),
                1,
                getAnimationPeriod()-1
        );
    }
}
