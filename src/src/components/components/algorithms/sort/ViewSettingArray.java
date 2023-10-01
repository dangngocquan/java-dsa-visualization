package src.components.components.algorithms.sort;

import src.App;
import src.Config;
import src.components.base.Button;
import src.components.base.Panel;
import src.components.base.TextField;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class ViewSettingArray extends Panel {
    private Panel[] panels;
    private Button[] buttons;
    private TextField[] textFields;

    public ViewSettingArray(
            int x, int y, int width, int height,
            Color backgroundColor, ImageIcon backgroundImage,
            String text, int shadowSize) {
        super(x, y, width, height, backgroundColor, backgroundImage, text, shadowSize);
        setBorderWidth(2);
        addPanels();
        addButtons();
        addTextFields();
        addFocusListenerForTextFields();
        addActionListenerForButtons();
        repaint();
    }

    public void addPanels() {
        panels = new Panel[2];
        panels[0] = new Panel(
                10, 10, 300, 30,
                getBackgroundColor(), null,
                String.format("%-26s", "Setting Array"),
                0
        );
        panels[0].setFont(Config.ARIAL_BOLD_16);

        panels[1] = new Panel(
                80, panels[0].getY() + panels[0].getHeightPanel() + 15, 150, 30,
                getBackgroundColor(), null,
                String.format("%-15s:", "Size [ 2 - 20 ]"),
                0
        );
        panels[1].setFont(Config.ARIAL_BOLD_14);

        add(panels[0]);
        add(panels[1]);
    }

    public void addButtons() {
        buttons = new Button[2];

        buttons[0] = new Button(
                80, panels[1].getY() + panels[1].getHeightPanel() + 15,
                200, 30, "Shuffle Array"
        );

        buttons[1] = new Button(
                buttons[0].getX() + buttons[0].getWidth() + 50,
                buttons[0].getY(),
                200, 30, "Generate Random Array"
        );

        buttons[0].setFont(Config.ARIAL_BOLD_14);
        buttons[1].setFont(Config.ARIAL_BOLD_14);

        add(buttons[0]);
        add(buttons[1]);
    }

    public void addTextFields() {
        textFields = new TextField[1];

        textFields[0]=  new TextField(
                panels[1].getX() + panels[1].getWidthPanel() + 10,
                panels[1].getY(),
                panels[1].getHeightPanel() + 10,
                panels[1].getHeightPanel(),
                "20",
                Color.WHITE,
                1, 0, 0
        );

        textFields[0].setFont(Config.ARIAL_BOLD_14);
        add(textFields[0]);
    }

    public void addActionListenerForButtons() {
        buttons[0].addActionListener(e -> {
            SortAlgorithmScreen sortAlgorithmScreen =
                    (SortAlgorithmScreen) getApp().getScreens().get("SortAlgorithmScreen");
            int[] mainArray = sortAlgorithmScreen.getArray();
            Service.shuffleArray(mainArray);
            sortAlgorithmScreen.getViewSetElementsValue().repaintAfterShuffleArray();
        });
        buttons[1].addActionListener(e -> {
            SortAlgorithmScreen sortAlgorithmScreen =
                    (SortAlgorithmScreen) getApp().getScreens().get("SortAlgorithmScreen");
            int[] mainArray = sortAlgorithmScreen.getArray();
            sortAlgorithmScreen.setArray(Service.createRandomArray(mainArray.length, 1, 251));
        });
    }

    public void addFocusListenerForTextFields() {
        textFields[0].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                int size = getLengthArray();
                SortAlgorithmScreen sortAlgorithmScreen =
                        (SortAlgorithmScreen) getApp().getScreens().get("SortAlgorithmScreen");
                int[] mainArray = sortAlgorithmScreen.getArray();
                if (size != mainArray.length) {
                    ViewSetElementsValue viewSetElementsValue = sortAlgorithmScreen.getViewSetElementsValue();
                    sortAlgorithmScreen.setArray(Service.createRandomArray(size, 1, 251));
                }
            }
        });
    }

    public int getLengthArray() {
        String regex = "[0-9]+";
        String data = textFields[0].getText();
        if (data.matches(regex)) {
            int length = Integer.parseInt(data);
            if (length > 20) {
                length = 20;
                textFields[0].setText(length + "");
            } else if (length < 2) {
                length = 2;
                textFields[0].setText(length + "");
            }
            return length;
        } else {
            textFields[0].setText("20");
            return 20;
        }
    }

    public App getApp() {
        return (App) (Service.getFrame(this));
    }
}
