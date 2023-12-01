package src.components.components.datastructures.tree.actionanimation;

import src.Config;
import src.components.components.datastructures.tree.*;
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
                if (panels[i] == null) {
                    newRootPanel();
                    animationStep = 3;
                } else {
                    checkNode();
                }
            } else {
                getRootScreen().setDescription(
                        "[INSERT] Finished"
                );
                end();
                getRootScreen().tree.insert(panelInsert);
                getViewAction().resetPanelsClone();
            }
        } else if (animationStep == 1) {
            insert();
        } else {
            getRootScreen().setDescription(
                    "[INSERT] Finished."
            );
            end();
            getRootScreen().tree.insert(panelInsert);
            getViewAction().resetPanelsClone();
        }
    }

    protected void checkNode() {
        getRootScreen().setDescription(
                String.format(
                        "[CHECK] Check element %d", panels[i].getValue()
                )
        );
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

    protected void yellowFlag() {
        flag(Config.COLOR_YELLOW);
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
            getRootScreen().setDescription(
                    "[CHECK] Insert value duplicated!"
            );
            animationStep = 2;
            i = -1;
        } else if (compare < 0) {
            getRootScreen().setDescription(
                    String.format(
                            "[CHECK] Insert value = %d is smaller than current element = %d",
                            value, panels[i].getValue()
                    )
            );
            if (hasChildLeft(i)) {
                i = 2 * i + 1;
                animationStep = 0;
            } else {
               if (2 * i + 1 < panels.length) {
                   newLeftPanel();
                   animationStep = 2;
               } else {
                   end();
                   new ViewTreeController.DialogNotifyMaxHeight();
               }
            }
        } else {
            getRootScreen().setDescription(
                    String.format(
                            "[CHECK] Insert value = %d is greater than current element = %d",
                            value, panels[i].getValue()
                    )
            );
            if (hasChildRight(i)) {
                i = 2 * i + 2;
                animationStep = 0;
            } else {
                if (2 * i + 2 < panels.length) {
                    newRightPanel();
                    animationStep = 2;
                } else {
                    end();
                    new ViewTreeController.DialogNotifyMaxHeight();
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

    protected void newRootPanel() {
        newPanel(0);
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
