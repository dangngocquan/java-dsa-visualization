package src.components.components.algorithms.sort;

import src.Config;
import src.components.base.Panel;

import java.awt.*;

public class Bar extends Panel implements Comparable<Bar> {
    private Panel[] panels;
    private int index;
    private int value;

    public Bar(int index, int value) {
        super(
                ViewSortAlgorithmAction.initialX + index * (ViewSortAlgorithmAction.barWidth + ViewSortAlgorithmAction.gapWidth),
                ViewSortAlgorithmAction.initialY0 - (3* value + 20),
                ViewSortAlgorithmAction.barWidth,
                3* value + 20,
                Color.WHITE,
                null, "", 0);
        this.index = index;
        this.value = value;
        draw();
    }

    public void draw() {
        panels = new Panel[2];
        panels[0] = new Panel(
                0, 0, getWidthPanel(), 20, Config.BACKGROUND_COLOR_APP,
                null, value + "", 0
        );
        panels[1] = new Panel(
                0, panels[0].getHeightPanel(),
                getWidthPanel(), getHeightPanel() - panels[0].getHeightPanel(),
                getBackgroundColor(),
                null, "", 0
        );
        panels[1].setBorderWidth(1);

        add(panels[0]);
        add(panels[1]);
    }

    public int getIndex() {
        return index;
    }

    public int getValue() {
        return value;
    }


    public void setBackgroundColor(Color color) {
        this.backgroundColor = color;
        panels[1].setBackgroundColor(color);
        repaint();
    }

    public Panel[] getPanels() {
        return panels;
    }

    @Override
    public int compareTo(Bar o) {
        if (getValue() == o.getValue()) return getIndex() - o.getIndex();
        return getValue() - o.getValue();
    }
}
