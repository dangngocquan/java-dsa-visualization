package src.components.components.algorithms;

import src.App;
import src.Config;
import src.components.base.Panel;
import src.services.ServiceComponent;

public abstract class AbstractViewAlgorithmController extends Panel {
    public AbstractAlgorithmScreen rootScreen;
    public AbstractViewAlgorithmController(AbstractAlgorithmScreen rootScreen) {
        super(
                0, Config.HEIGHT - Config.HEIGHT_VIEW_CONTROLLER,
                Config.WIDTH, Config.HEIGHT_VIEW_CONTROLLER,
                Config.COLOR_GRAY_1, null, "", 0
        );
        this.rootScreen = rootScreen;
    }

    public App getApp() {
        return (App) (ServiceComponent.getFrame(this));
    }
}
