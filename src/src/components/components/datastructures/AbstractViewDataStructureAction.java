package src.components.components.datastructures;

import src.Config;
import src.components.base.Panel;

public abstract class AbstractViewDataStructureAction extends Panel {
    public AbstractDataStructureScreen rootScreen;
    public int period = Config.DEFAULT_ANIMATION_SPEED;
    public Panel description;

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

    public void addDescriptionPanel() {
        this.description = new Panel(
                0, Config.HEIGHT - Config.HEIGHT_VIEW_CONTROLLER - Config.HEIGHT_DESCRIPTION,
                Config.WIDTH, Config.HEIGHT_DESCRIPTION,
                getBackgroundColor(), null,
                "" , 0
        );
        description.setFont(Config.MONOSPACED_BOLD_14);
        add(description);
    }

    public void setDescription(String s) {
        this.description.setText(s);
    }
}
