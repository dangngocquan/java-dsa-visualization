package src.components.components.algorithms.sort;

import src.components.base.Panel;

import javax.swing.*;
import java.awt.*;

public class ViewSettingSortAlgorithm extends Panel {
    public ViewSettingSortAlgorithm(
            int x, int y, int width, int height,
            Color backgroundColor, ImageIcon backgroundImage,
            String text, int shadowSize) {
        super(x, y, width, height, backgroundColor, backgroundImage, text, shadowSize);
        setBorderWidth(2);
        repaint();
    }
}
