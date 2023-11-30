package src.components.components.algorithms;

import src.Config;
import src.components.base.Panel;

public abstract class AbstractViewAlgorithmAction extends Panel {
    public AbstractAlgorithmScreen rootScreen;
    public Panel description;

    public AbstractViewAlgorithmAction(AbstractAlgorithmScreen rootScreen) {
        super(
                0, 0,
                Config.WIDTH, Config.HEIGHT - Config.HEIGHT_VIEW_CONTROLLER,
                Config.BACKGROUND_COLOR_APP, null, "", 0
        );
        this.rootScreen = rootScreen;
    }

    public void addDescriptionPanel() {
        this.description = new Panel(
                0, Config.HEIGHT - Config.HEIGHT_VIEW_CONTROLLER - 40,
                Config.WIDTH, 40,
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
