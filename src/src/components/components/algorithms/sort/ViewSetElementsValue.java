package src.components.components.algorithms.sort;

import src.Config;
import src.components.base.Panel;
import src.components.base.TextField;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;

public class ViewSetElementsValue extends Panel {
    private int[] elements;
    private Panel[] panels;
    private TextField[] textFields;

    public ViewSetElementsValue(
            int x, int y, int width, int height,
            Color backgroundColor, ImageIcon backgroundImage,
            String text, int shadowSize, int[] array) {
        super(x, y, width, height, backgroundColor, backgroundImage, text, shadowSize);
        setBorderWidth(2);
        setElements(array);
    }

    public void setElements(int[] array) {
        this.elements = array;
        create();
        repaint();
    }

    public void repaintAfterShuffleArray() {
        for (int i = 0; i < elements.length; i++) {
            textFields[i].setText(elements[i] + "");
        }
    }

    public void create() {
        removeAll();
        repaint();
        panels = new Panel[elements.length + 1];
        textFields = new TextField[elements.length];
        panels[0] = new Panel(
                10, 10, getWidthPanel() - 20, 30,
                getBackgroundColor(), null, "Set value of each element in range [ 1 - 250 ]", 0
        );
        add(panels[0]);

        for (int i = 1; i < panels.length; i++) {
            panels[i] = new Panel(
                    100, panels[i-1].getY() + panels[i-1].getHeightPanel() + 2,
                    80, 20,
                    getBackgroundColor(), null,
                    String.format("%-5s = ", String.format("a[%d]", i-1)), 0
            );
            panels[i].setFont(Config.ARIAL_BOLD_12);
            add(panels[i]);

            textFields[i-1] = new TextField(
                    panels[i].getX() + panels[i].getWidthPanel(),
                    panels[i].getY(),
                    panels[i].getHeightPanel() * 2,
                    panels[i].getHeightPanel(),
                    elements[i-1] + "",
                    Color.WHITE,
                    1, 0, 0
            );
            textFields[i-1].setFont(Config.ARIAL_BOLD_12);
            add(textFields[i-1]);
        }
    }

}
