package src;

import src.components.base.Frame;
import src.components.components.AbstractScreen;
import src.components.components.MainScreen;

import java.util.HashMap;
import java.util.Map;

public class App extends Frame {
    private AbstractScreen mainScreen;
    private Map<String, AbstractScreen> screens;
    public App() {
        super(
                "Java Algorithm Visualizer",
                0, 0, Config.DEVICE_WIDTH, Config.DEVICE_HEIGHT,
                Config.BACKGROUND_COLOR_APP,
                Config.ARIAL_BOLD_12
        );
        addScreens();
        repaint();
    }

    public void addScreens() {
        mainScreen = new MainScreen(
                0, 0, Config.WIDTH, Config.HEIGHT,
                null, null, ""
        );
        add(mainScreen);
        screens = new HashMap<>();
        screens.put(mainScreen.getClass().getSimpleName(), mainScreen);
    }

    public Map<String, AbstractScreen> getScreens() {
        return screens;
    }

}
