package src.components.components.datastructures;

import src.Config;
import src.components.base.Panel;

public abstract class AbstractViewDataStructureAction extends Panel {
    public AbstractDataStructureScreen rootScreen;
    public int period = Config.DEFAULT_ANIMATION_SPEED;

    public AbstractViewDataStructureAction(AbstractDataStructureScreen rootScreen) {
        super(
                0, 0,
                Config.WIDTH, Config.HEIGHT - Config.HEIGHT_VIEW_CONTROLLER,
                Config.BACKGROUND_COLOR_APP, null, "", 0
        );
        this.rootScreen = rootScreen;
    }

    public int getPeriod() {
        return period;
    }

    public void setPeriod(int period) {
        this.period = period;
    }
}
