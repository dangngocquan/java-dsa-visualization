package src.components.components.datastructures.tree.actionanimation;

import src.Config;
import src.components.components.datastructures.tree.AbstractTreeAnimation;
import src.components.components.datastructures.tree.AbstractTreeScreen;
import src.components.components.datastructures.tree.AbstractViewTreeAction;
import src.components.components.datastructures.tree.TreePanelNode;
import src.services.ServiceAnimation;
import src.services.serviceanimations.Location;

import java.awt.*;

public class TreeActionInsert extends AbstractTreeAnimation {
    public int value;
    public TreePanelNode[] panels;
    public int i;
    public TreePanelNode panelInsert = null;

    public TreeActionInsert(
            int value,
            AbstractTreeScreen rootScreen,
            int period,
            AbstractTreeAnimation nextAnimation) {
        super(rootScreen, period, nextAnimation);
        this.value = value;
        this.panels = getViewAction().panelsClone;
    }

    public AbstractTreeScreen getRootScreen() {
        return (AbstractTreeScreen) rootScreen;
    }

    public AbstractViewTreeAction getViewAction() {
        return (AbstractViewTreeAction) getRootScreen().viewAction;
    }

    @Override
    public void run() {
        if (animationStep == 0) {
            if (i < panels.length) {
                checkNode();
            } else {
                end();
                getRootScreen().tree.insert(panelInsert);
                getViewAction().resetPanelsClone();
            }
        } else if (animationStep == 1) {
            insert();
        } else {
            end();
            getRootScreen().tree.insert(panelInsert);
            getViewAction().resetPanelsClone();
        }
    }

    protected void checkNode() {
        ServiceAnimation.transitionColor(
                panels[i],
                panels[i].getBackgroundColor(),
                Config.COLOR_YELLOW,
                10, period - 10
        );
        animationStep = 1;
    }

    protected void whiteFlag() {
        flag(Config.COLOR_WHITE);
    }

    protected void greenFlag() {
        flag(Config.COLOR_GREEN);
    }

    protected void flag(Color color) {
        ServiceAnimation.transitionColor(
                panels[i],
                panels[i].getBackgroundColor(),
                color,
                10, period - 10
        );
    }

    protected void insert() {
        int compare = this.value - panels[i].getValue();
        whiteFlag();
        if (compare == 0) {
            animationStep = 2;
        } else if (compare < 0) {
            if (hasChildLeft(i)) {
                i = 2 * i + 1;
                animationStep = 0;
            } else {
               if (2 * i + 1 < panels.length) {
                   newLeftPanel();
                   animationStep = 2;
               } else {
                   end();
               }
            }
        } else {
            if (hasChildRight(i)) {
                i = 2 * i + 2;
                animationStep = 0;
            } else {
                if (2 * i + 2 < panels.length) {
                    newRightPanel();
                    animationStep = 2;
                } else {
                    end();
                }
            }
        }
    }

    protected void newLeftPanel() {
        newPanel(1);
    }

    protected void newRightPanel() {
        newPanel(2);
    }

    protected void newPanel(int type) {
        panelInsert = new TreePanelNode(
                AbstractViewTreeAction.INDEX_ROWS[2 * i + type],
                AbstractViewTreeAction.INDEX_COLUMNS[2 * i + type],
                value
        );
        panelInsert.setY(panelInsert.getY() + 1000);
        getViewAction().add(panelInsert);
        panels[i * 2 + type] = panelInsert;

        ServiceAnimation.translate(
                panelInsert,
                new Location(panelInsert.getX(), panelInsert.getY()),
                0,
                -1000,
                10,
                period - 10
        );
        i = 2 * i + type;
        greenFlag();
    }

    protected boolean hasChildLeft(int i) {
        return 2 * i + 1 < panels.length && panels[2 * i + 1] != null;
    }

    protected boolean hasChildRight(int i) {
        return 2 * i + 2 < panels.length && panels[2 * i + 2] != null;
    }
}
