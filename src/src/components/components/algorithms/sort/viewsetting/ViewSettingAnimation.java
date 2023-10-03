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

public class ViewSettingAnimation extends Panel {
    private Panel[] panels;
    private TextField[] textFields;

    public ViewSettingAnimation(
            int x, int y, int width, int height,
            Color backgroundColor, ImageIcon backgroundImage,
            String text, int shadowSize) {
        super(x, y, width, height, backgroundColor, backgroundImage, text, shadowSize);
        setBorderWidth(2);
        addPanels();
        addTextFields();
        addFocusListenerForTextFields();
        repaint();
    }

    public void addPanels() {
        panels = new Panel[2];
        panels[0] = new Panel(
                10, 10, 300, 30,
                getBackgroundColor(), null,
                String.format("%-26s", "Setting Animation"),
                0
        );
        panels[0].setFont(Config.ARIAL_BOLD_16);

        panels[1] = new Panel(
                80, panels[0].getY() + panels[0].getHeightPanel() + 30, 120, 30,
                getBackgroundColor(), null,
                String.format("%-12s:", "Slower Speed"),
                0
        );
        panels[1].setFont(Config.ARIAL_BOLD_14);

        add(panels[0]);
        add(panels[1]);
    }

    public void addTextFields() {
        textFields = new TextField[1];

        textFields[0]=  new TextField(
                panels[1].getX() + panels[1].getWidthPanel() + 10,
                panels[1].getY(),
                panels[1].getHeightPanel() * 3,
                panels[1].getHeightPanel(),
                "500",
                Color.WHITE,
                1, 0, 0
        );

        textFields[0].setFont(Config.ARIAL_BOLD_14);
        add(textFields[0]);
    }

    public void addFocusListenerForTextFields() {
        textFields[0].addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {

            }

            @Override
            public void focusLost(FocusEvent e) {
                int period = Config.MILLIS_PER_ACTION * getSlowerScale();
                System.out.println(period + " " + SortAlgorithmScreen.period);
                if (period != SortAlgorithmScreen.period) {
                    SortAlgorithmScreen.period = period;
                    SortAlgorithmScreen sortAlgorithmScreen =
                            ((SortAlgorithmScreen) getApp().getScreens().get("SortAlgorithmScreen"));
                    sortAlgorithmScreen.endSort();
                }
            }
        });
    }

    public int getSlowerScale() {
        String regex = "[0-9]+";
        String data = textFields[0].getText();
        if (data.matches(regex)) {
            int scale = Integer.parseInt(data);
            if (scale < 1) {
                scale = 1;
                textFields[0].setText(scale + "");
            }
            return scale;
        } else {
            textFields[0].setText("500");
            return 500;
        }
    }

    public App getApp() {
        return (App) (Service.getFrame(this));
    }
}
