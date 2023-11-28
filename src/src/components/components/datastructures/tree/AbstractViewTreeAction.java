package src.components.components.datastructures.tree;

import src.Config;
import src.components.components.datastructures.AbstractViewDataStructureAction;
import src.components.components.datastructures.tree.actionanimation.TreeActionTraversal;
import src.services.ServiceComponent;

import java.awt.*;

public abstract class AbstractViewTreeAction extends AbstractViewDataStructureAction {
    public static final int INITIAL_X = 2;
    public static final int INITIAL_Y = 30;
    public static final int GAP_X = 0;
    public static final int GAP_Y = 60;
    public static final int[] INDEX_ROWS = new int[] {
            0,
            1, 1,
            2, 2, 2, 2,
            3, 3, 3, 3, 3, 3, 3, 3,
            4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4
    };
    public static final int[] INDEX_COLUMNS = new int[] {
            15,
            7, 23,
            3, 11, 19, 27,
            1, 5, 9, 13, 17, 21, 25, 29,
            0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30
    };
//    public static final int[] INDEX_ROWS = new int[] {
//            0,
//            1, 1,
//            2, 2, 2, 2,
//            3, 3, 3, 3, 3, 3, 3, 3,
//            4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4, 4,
//            5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5
//    };
//    public static final int[] INDEX_COLUMNS = new int[] {
//            31,
//            15, 47,
//            7, 23, 39, 55,
//            3, 11, 19, 27, 35, 43, 51, 59,
//            1, 5, 9, 13, 17, 21, 25, 29, 33, 37, 41, 45, 49, 53, 57, 61,
//            0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 42, 44, 46, 48, 50, 52, 54, 56, 58, 60, 62
//    };
    public static final int SIZE_PER_NODE =
            (Config.WIDTH - 2 * INITIAL_X - (INDEX_COLUMNS.length + 1) * GAP_X)
                    / INDEX_COLUMNS.length;

    public TreePanelNode[] panelsClone;

    // Variables support for animation
    public static boolean movableArrow = false;

    public AbstractViewTreeAction(AbstractTreeScreen rootScreen) {
        super(rootScreen);
        this.panelsClone = getRootScreen().getPanelNodeArray();
        drawElements();
        repaint();
    }

    public void drawElements() {
        removeAll();
        for (TreePanelNode panel : panelsClone) {
            if (panel == null) continue;
            panel.setBackgroundColor(Config.COLOR_WHITE);
            add(panel);
        }
        repaint();
    }

    public AbstractTreeScreen getRootScreen() {
        return (AbstractTreeScreen) rootScreen;
    }

    public void resetPanelsClone() {
        TreePanelNode[] a = getRootScreen().getPanelNodeArray();
        if (this.panelsClone == null) {
            this.panelsClone = a;
        } else {
            System.arraycopy(a, 0, panelsClone, 0, a.length);
        }

        drawElements();
        repaint();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        g2d.setStroke(new BasicStroke(2));
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        for (int i = 0; i < panelsClone.length; i++) {
            if (panelsClone[i] == null) continue;
            // Draw arrow from root to left tree
            if (2 * i + 1 < panelsClone.length && panelsClone[2 * i + 1] != null) {
                if (movableArrow) {
                    ServiceComponent.drawPatternArrow2(
                            g2d,
                            panelsClone[i].getX(),
                            panelsClone[i].getY() + SIZE_PER_NODE / 2,
                            panelsClone[2 * i + 1].getX() + SIZE_PER_NODE / 2,
                            panelsClone[2 * i + 1].getY(),
                            Color.BLACK
                    );
                } else {
                    ServiceComponent.drawPatternArrow2(
                            g2d,
                            getDefaultX(i),
                            getDefaultY(i) + SIZE_PER_NODE / 2,
                            getDefaultX(2 * i + 1) + SIZE_PER_NODE / 2,
                            getDefaultY(2 * i + 1),
                            Color.BLACK
                    );

                }
            }
            // Draw arrow from root to right tree
            if (2 * i + 2 < panelsClone.length && panelsClone[2 * i + 2] != null) {
                if (movableArrow) {
                    ServiceComponent.drawPatternArrow2(
                            g2d,
                            panelsClone[i].getX() + SIZE_PER_NODE,
                            panelsClone[i].getY() + SIZE_PER_NODE / 2,
                            panelsClone[2 * i + 2].getX() + SIZE_PER_NODE / 2,
                            panelsClone[2 * i + 2].getY(),
                            Color.BLACK
                    );
                } else {
                    ServiceComponent.drawPatternArrow2(
                            g2d,
                            getDefaultX(i) + SIZE_PER_NODE,
                            getDefaultY(i) + SIZE_PER_NODE / 2,
                            getDefaultX(2 * i + 2) + SIZE_PER_NODE / 2,
                            getDefaultY(2 * i + 2),
                            Color.BLACK
                    );
                }

            }
        }
    }

    public static int getDefaultX(int i) {
        return INITIAL_X
                + GAP_X * (INDEX_COLUMNS[i] + 1)
                + SIZE_PER_NODE * INDEX_COLUMNS[i];
    }

    public static int getDefaultY(int i) {
        return INITIAL_Y
                + GAP_Y * (INDEX_ROWS[i] + 1)
                + SIZE_PER_NODE * INDEX_ROWS[i];
    }

    public void actionTraversal(int type) {
        drawElements();
        repaint();
        new TreeActionTraversal(
                type, getRootScreen(),
                getRootScreen().getPeriod(), null
        ).start();
    }

    public abstract void actionInsert(Integer value);
    public abstract void actionDelete(Integer value);
}