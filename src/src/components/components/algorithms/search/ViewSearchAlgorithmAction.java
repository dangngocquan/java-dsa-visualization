package src.components.components.algorithms.search;

import src.Config;
import src.components.components.algorithms.AbstractViewAlgorithmAction;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.awt.*;

public class ViewSearchAlgorithmAction extends AbstractViewAlgorithmAction {
    public static int gapWidth = 2;
    public static int barWidth = 10;
    public static int initialX = 10;
    public static int initialY = 10;
    public static int initialY0;
    public static int initialY1;
    public int[] xBars;
    public int[] elements;
    public Bar[] bars;
    public Bar barSearching;
    public int animationPeriod = 500;

    public ViewSearchAlgorithmAction(AbstractSearchAlgorithmScreen rootScreen, int[] array) {
        super(rootScreen);
        this.elements = array;
        updateStaticValues();
        addBars();
        repaint();
    }

    public AbstractSearchAlgorithmScreen getRootScreen() {
        return (AbstractSearchAlgorithmScreen) rootScreen;
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
        barSearching = new Bar(0, getRootScreen().valueSearching);
        barSearching.setY(barSearching.getY() + initialY1 - initialY0);
        add(barSearching);
        if (bars.length > 64) {
            for (Bar bar : bars) bar.draw(Config.MONOSPACED_BOLD_6);
            barSearching.draw(Config.MONOSPACED_BOLD_6);
        }
    }

    public int getAnimationPeriod() {
        return animationPeriod;
    }

    public void setAnimationPeriod(int animationPeriod) {
        if (animationPeriod != this.animationPeriod) {
            this.animationPeriod = animationPeriod;
            getRootScreen().endSearch(0);
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

    public void runEndAnimation(int type) {
        if (type == 0) {
            quickAnimationSetColor(
                    0, bars.length - 1,
                    Config.COLOR_RED
            );
        }
    }

    public void quickAnimationSetColor(int from, int to, Color color) {
        if (getAnimationPeriod() >= (to - from + 1) * 3) {
            int delay = 1;
            int duration = 2;
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
        } else {
            for (int i = from; i <= to; i++) {
                bars[i].setBackgroundColor(color);
            }
        }
    }

    public void moveBarSearching(int i1, int i2) {
        ServiceAnimation.translate(
                barSearching,
                new Location(xBars[i1], barSearching.getY()),
                (i2 - i1) * (barWidth + gapWidth),
                0,
                1,
                getAnimationPeriod()-1
        );
    }
}