package src.components.components.algorithms.sort.viewsetting;

import src.Config;
import src.components.base.ComboBox;
import src.components.base.Panel;

import javax.swing.*;
import java.awt.*;

public class ViewSettingSortAlgorithm extends Panel {
    private Panel[] panels;
    private ComboBox<String>[] comboBoxes;

    public ViewSettingSortAlgorithm(
            int x, int y, int width, int height,
            Color backgroundColor, ImageIcon backgroundImage,
            String text, int shadowSize) {
        super(x, y, width, height, backgroundColor, backgroundImage, text, shadowSize);
        setBorderWidth(2);
        addPanels();
        addComboBoxes();
        repaint();
    }

    public void addPanels() {
        panels = new Panel[2];
        panels[0] = new Panel(
                10, 10, 300, 30,
                getBackgroundColor(), null,
                String.format("%-26s", "Setting Algorithms"),
                0
        );
        panels[0].setFont(Config.ARIAL_BOLD_16);

        panels[1] = new Panel(
                80, panels[0].getY() + panels[0].getHeightPanel(), 170, 30,
                getBackgroundColor(), null,
                String.format("%-17s:", "Sorting Algorithm"),
                0
        );
        panels[1].setFont(Config.ARIAL_BOLD_14);

        add(panels[0]);
        add(panels[1]);
    }

    public void addComboBoxes() {
        comboBoxes = new ComboBox[1];
        String[] choices = new String[] {
                "BubbleSort",
                "SelectionSort",
                "InsertionSort",
                "QuickSort",
                "MergeSort"
        };
        comboBoxes[0] = new ComboBox<>(
                panels[1].getX() + panels[1].getWidthPanel(),
                panels[1].getY(),
                300, panels[1].getHeightPanel(),
                choices
        );

        add(comboBoxes[0]);
    }

    public String getSelectingValue() {
        return comboBoxes[0].getSelectedItem().toString();
    }

}
