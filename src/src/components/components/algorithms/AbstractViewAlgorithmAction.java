package src.components.components.algorithms;

import src.Config;
import src.components.base.Panel;

public abstract class AbstractViewAlgorithmAction extends Panel {
    public AbstractAlgorithmScreen rootScreen;
    public AbstractViewAlgorithmAction(AbstractAlgorithmScreen rootScreen) {
        super(
                0, 0,
                Config.WIDTH, Config.HEIGHT - Config.HEIGHT_VIEW_CONTROLLER,
                Config.BACKGROUND_COLOR_APP, null, "", 0
        );
        this.rootScreen = rootScreen;
    }
}
