package src;

import src.components.base.Frame;
import src.components.base.Panel;
import src.components.components.MainScreen;

import javax.swing.*;

public class App extends Frame {
    private Panel mainScreen;
    public App() {
        super(
                "Java Algorithm Visualizer",
                0, 0, Config.DEVICE_WIDTH, Config.DEVICE_HEIGHT,
                Config.BACKGROUND_COLOR_APP,
                Config.ARIAL_BOLD_12
        );
        addMainScreen();
    }

    public void addMainScreen() {
        mainScreen = new MainScreen(
                0, 0, Config.WIDTH, Config.HEIGHT,
                null, null, ""
        );
        add(mainScreen);
    }

}
