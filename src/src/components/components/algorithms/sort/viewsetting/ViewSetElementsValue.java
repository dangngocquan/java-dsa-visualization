package src.components.components.algorithms.sort.viewsetting;

import src.App;
import src.Config;
import src.components.base.Panel;
import src.components.base.TextField;
import src.components.components.algorithms.sort.SortAlgorithmScreen;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

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

    public void updateValueElements() {
        int[] a = new int[elements.length];
        for (int i = 0; i < textFields.length; i++) {
            a[i] = Integer.parseInt(textFields[i].getText());
        }
        SortAlgorithmScreen sortAlgorithmScreen =
                (SortAlgorithmScreen) getApp().getScreens().get("SortAlgorithmScreen");
        sortAlgorithmScreen.setArray(a);
    }

    public void updateValueTextFields() {
        for (int i = 0; i < textFields.length; i++) {
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
                getBackgroundColor(), null, "Set value of each element in range [ 1 - 100 ]", 0
        );
        add(panels[0]);

        for (int i = 1; i < Math.min(21, panels.length); i++) {
            panels[i] = new Panel(
                    50, panels[i-1].getY() + panels[i-1].getHeightPanel() + 2,
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

        for (int i = 21; i < Math.min(41, panels.length); i++) {
            panels[i] = new Panel(
                    textFields[i-21].getX() + textFields[i-21].getWidth() + 30,
                    panels[i-20].getY(),
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

        addFocusListenerForTextFields();
    }

    public void addFocusListenerForTextFields() {
        for (int i = 0; i < elements.length; i++) {
            int value = elements[i];
            textFields[i].addFocusListener(new FocusListener() {
                @Override
                public void focusGained(FocusEvent e) {

                }

                @Override
                public void focusLost(FocusEvent e) {
                    TextField textField = ((TextField) e.getSource());
                    String data = textField.getText();
                    String regex = "[0-9]+";
                    if (data.matches(regex)) {
                        int value = Integer.parseInt(data);
                        if (value > 100) {
                            value = 100;
                            textField.setText(value + "");
                        } else if (value < 1) {
                            value = 1;
                            textField.setText(value + "");
                        }
                    } else {
                        textField.setText("1");
                    }
                    updateValueElements();
                }
            });
        }
    }

    public App getApp() {
        return (App) (Service.getFrame(this));
    }
}
