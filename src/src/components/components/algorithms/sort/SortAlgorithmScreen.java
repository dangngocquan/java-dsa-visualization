package src.components.components.algorithms.sort;

import src.Config;
import src.components.components.AbstractScreen;
import src.components.components.algorithms.sort.sortanimations.SortAnimation;
import src.components.components.algorithms.sort.sortanimations.SortAnimationFactory;
import src.components.components.algorithms.sort.viewcontroller.ViewController;
import src.components.components.algorithms.sort.viewsetting.ViewSetElementsValue;
import src.components.components.algorithms.sort.viewsetting.ViewSetting;
import src.components.components.algorithms.sort.viewsort.ViewSort;
import src.services.services.Service;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;

public class SortAlgorithmScreen extends AbstractScreen {
    private ViewSort viewSort;
    private ViewController viewController;
    private ViewSetting viewSetting;
    private ViewSetElementsValue viewSetElementsValue;
    private int[] array;
    public static Timer timer;
    public static String sortAlgorithmName;
    public static SortAnimation sortAnimation;
    public static int period;

    public SortAlgorithmScreen(
            int x, int y, int width, int height,
            Color backgroundColor,
            ImageIcon backgroundImage,
            String text) {
        super(x, y, width, height, backgroundColor, backgroundImage, text);
        sortAlgorithmName = "";
        array = Service.createRandomArray(20, 1, 100);
        addViewSetting();
        addViewController();
        addViewSort();
        addViewSetElementsValue();
        setComponentZOrder(viewSetting, 0);
        setComponentZOrder(viewSetElementsValue, 1);
        setComponentZOrder(viewController, 2);
        setComponentZOrder(viewSort, 3);
        period = getMillisPerAction();

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
        boolean isChanged = false;
        if (this.array.length != array.length) isChanged = true;
        if (!isChanged) {
            for (int i = 0; i < array.length; i++) {
                if (this.array[i] != array[i]) {
                    isChanged = true;
                    break;
                }
            }
        }
        if (isChanged) {
            endSort();
            this.array = array;
            viewSetElementsValue.setElements(array);
            viewSort.setElements(array);
            repaint();
        }
    }

    public void addViewSort() {
        viewSort = new ViewSort(
                300, 0, width - 300, height,
                Config.BACKGROUND_COLOR_APP,
                null, "", 0, array
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

    public int getMillisPerAction() {
        return viewSetting.getSlowerScale() * Config.MILLIS_PER_ACTION;
    }

    // Sort
    public void startSort() {
        viewController.startSort();
        viewSort.resetColorBars();
        timer = new Timer();
        period = getMillisPerAction();
        sortAnimation = SortAnimationFactory.createSortAnimation(
                viewSetting.getAlgorithmName(),
                this,
                viewSort.bars,
                timer,
                period
        );
        sortAnimation.start();
    }

    public void pauseSort() {
        if (sortAnimation != null && sortAnimation.isRunning()) {
            viewController.pauseSort();
            sortAnimation.pause();
        }
    }

    public void continueSort() {
        if (sortAnimation != null && sortAnimation.isPaused()) {
            viewController.continueSort();
            sortAnimation.continueRun();
        }
    }

    public void endSort() {
        if (timer != null) {
            timer.cancel();
            timer.purge();
        }
        sortAnimation = null;
        viewController.endSort();
    }


}
