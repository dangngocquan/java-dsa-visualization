package src.components.components.datastructures;

import src.Config;
import src.components.base.Panel;
import src.components.components.datastructures.list.abstractlistscreen.AbstractListScreen;

public abstract class AbstractViewDataStructureAction extends Panel {
    public AbstractDataStructureScreen rootScreen;

    public AbstractViewDataStructureAction(AbstractDataStructureScreen rootScreen) {
        super(
                0, 0,
                Config.WIDTH, Config.HEIGHT - Config.HEIGHT_VIEW_CONTROLLER,
                Config.BACKGROUND_COLOR_APP, null, "", 0
        );
        this.rootScreen = rootScreen;
    }
}
