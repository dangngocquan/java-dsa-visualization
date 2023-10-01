package src.components.components.algorithms.sort;

import src.components.components.AbstractScreen;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;

public class SortAlgorithmScreen extends AbstractScreen {
    private ViewSort viewSort;
    private ViewController viewController;
    private ViewSetting viewSetting;
    private ViewSetElementsValue viewSetElementsValue;
    private int[] array;

    public SortAlgorithmScreen(
            int x, int y, int width, int height,
            Color backgroundColor,
            ImageIcon backgroundImage,
            String text) {
        super(x, y, width, height, backgroundColor, backgroundImage, text);

        array = Service.createRandomArray(20, 1, 251);
        addViewSetting();
        addViewController();
        addViewSort();
        addViewSetElementsValue();
        setComponentZOrder(viewSetting, 0);
        setComponentZOrder(viewSetElementsValue, 1);
        setComponentZOrder(viewController, 2);
        setComponentZOrder(viewSort, 3);

        repaint();
    }

    @Override
    public void addButtons() {

    }

    @Override
    public void createDefaultScreens() {

    }

    @Override
    public void addActionListenerForButtons() {

    }

    public ViewSort getViewSort() {
        return viewSort;
    }

    public ViewController getViewController() {
        return viewController;
    }

    public ViewSetting getViewSetting() {
        return viewSetting;
    }

    public ViewSetElementsValue getViewSetElementsValue() {
        return viewSetElementsValue;
    }

    public int[] getArray() {
        return array;
    }

    public void setArray(int[] array) {
        this.array = array;
        viewSetElementsValue.setElements(array);
        repaint();
    }

    public void addViewSort() {
        viewSort = new ViewSort(
                300, 0, width - 300, height,
                new Color(244, 244, 248),
                null, "", 0
        );
        add(viewSort);
    }

    public void addViewController() {
        viewController = new ViewController(
                0, 0, 300, height,
                new Color(245, 168, 191),
                null, "", 10
        );
        add(viewController);
    }

    public void addViewSetting() {
        viewSetting = new ViewSetting(
                0 - width/3*2 + 250 + 25, height - 50 - 25, width/3*2, height/3*2,
                new Color(233, 239, 230),
                null, "", 20
        );
        add(viewSetting);
    }

    public void addViewSetElementsValue() {
        viewSetElementsValue = new ViewSetElementsValue(
                viewSetting.getX() + viewSetting.getWidthPanel() * 2 - 250 + 10 + 900,
                viewSetting.getY() - viewSetting.getHeightPanel() + 50 - 20,
                width/3 - 80, viewSetting.getHeightPanel(),
                new Color(233, 239, 230),
                null, "", 20, array
        );
        add(viewSetElementsValue);
    }
}
