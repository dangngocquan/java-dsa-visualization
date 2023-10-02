package src.components.components.algorithms.sort.viewsetting;

import src.App;
import src.Config;
import src.components.base.Button;
import src.components.base.Panel;
import src.components.components.algorithms.sort.SortAlgorithmScreen;
import src.services.animation.Animation;
import src.services.animation.Location;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;

public class ViewSetting extends Panel {
    private Button[] buttons;
    private Panel[] panels;
    public ViewSetting(int x, int y, int width, int height, Color backgroundColor, ImageIcon backgroundImage, String text, int shadowSize) {
        super(x, y, width, height, backgroundColor, backgroundImage, text, shadowSize);
        setBorderWidth(2);
        addPanels();
        addButtons();
        addActionListenerForButtons();
        setComponentZOrder(buttons[0], 0);
        repaint();
    }

    public void addButtons() {
        buttons = new Button[1];

        int buttonWidth = 250;
        int buttonHeight = 50;
        int gapWidth = 40;
        int gapHeight = 40;
        buttons[0] = new Button(
                getWidthPanel() - buttonWidth, 0,
                buttonWidth, buttonHeight, "Show Settings"
        );

        buttons[0].setBorderWidth(2);
        add(buttons[0]);
    }

    public void addPanels() {
        panels = new Panel[3];

        panels[0] = new ViewSettingArray(
                0, 0, getWidthPanel(), getHeightPanel() / 3,
                getBackgroundColor(), null, "", 0
        );

        panels[1] = new ViewSettingSortAlgorithm(
                0, panels[0].getY() + panels[0].getHeightPanel(),
                getWidthPanel(), getHeightPanel() / 3,
                getBackgroundColor(), null, "", 0
        );

        panels[2] = new ViewSettingAnimation(
                0, panels[1].getY() + panels[1].getHeightPanel(),
                getWidthPanel(), getHeightPanel() - getHeightPanel() / 3 * 2,
                getBackgroundColor(), null, "", 0
        );

        add(panels[0]);
        add(panels[1]);
        add(panels[2]);
    }

    public void addActionListenerForButtons() {
        // Show/Hide Settings
        buttons[0].addActionListener(e -> {
            // Show/Hide Settings
            ViewSetElementsValue viewSetElementsValue =
                    ((SortAlgorithmScreen) getApp().getScreens().get("SortAlgorithmScreen"))
                            .getViewSetElementsValue();
            if (x == 25) {
                Animation.translate(
                        getInstance(),
                        new Location(x, y),
                        -getWidthPanel() + buttons[0].getWidth(),
                        getHeightPanel() - buttons[0].getHeight() + 20,
                        0,
                        200
                );
                buttons[0].setText("Show Settings");
                buttons[0].setBackgroundColorEntered(Config.COLOR_GREEN);
                Animation.translate(
                        viewSetElementsValue,
                        new Location(viewSetElementsValue.getX(), viewSetElementsValue.getY()),
                        900,
                        0,
                        0,
                        200
                );
            } else {
                Animation.translate(
                        getInstance(),
                        new Location(x, y),
                        getWidthPanel() - buttons[0].getWidth(),
                        -getHeightPanel() + buttons[0].getHeight() - 20,
                        0,
                        200
                );
                buttons[0].setText("Hide Settings");
                buttons[0].setBackgroundColorEntered(Config.COLOR_RED);

                Animation.translate(
                        viewSetElementsValue,
                        new Location(viewSetElementsValue.getX(), viewSetElementsValue.getY()),
                        -900,
                        0,
                        0,
                        200
                );
            }
            // Hide view set elements value if it is appearing
            if (viewSetElementsValue.getX() < Config.WIDTH) {
                Animation.translate(
                        viewSetElementsValue,
                        new Location(viewSetElementsValue.getX(), viewSetElementsValue.getY()),
                        900,
                        0,
                        0,
                        200
                );
            }
        });
    }

    public int getSlowerScale() {
        return ((ViewSettingAnimation) panels[2]).getSlowerScale();
    }

    public Panel getInstance() {
        return this;
    }

    public App getApp() {
        return (App) (Service.getFrame(this));
    }

}
