package src.components.components.datastructures.list.arraylist;

import src.Config;
import src.components.components.datastructures.list.abstractlistscreen.AbstractListScreen;
import src.models.datastructures.list.MyArrayList;

import javax.swing.*;
import java.awt.*;

public class ArrayListScreen extends AbstractListScreen {
    public ArrayListScreen(
            int x, int y, int width, int height,
            Color backgroundColor, ImageIcon backgroundImage) {
        super(x, y, width, height, backgroundColor, backgroundImage);
    }

    @Override
    public void createList() {
        list = new MyArrayList<>();
        list.add(1);
        list.add(0);
        list.add(4);
    }

    @Override
    public void addViewAction() {
        viewAction = new ViewArrayListAction(
                0, 0, Config.WIDTH, Config.HEIGHT - viewController.getHeightPanel(),
                Config.BACKGROUND_COLOR_APP, this
        );
        add(viewAction);
    }
}
