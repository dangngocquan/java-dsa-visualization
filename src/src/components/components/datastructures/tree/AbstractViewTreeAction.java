package src.components.components.datastructures.tree;

import src.Config;
import src.components.components.datastructures.AbstractViewDataStructureAction;
import src.services.ServiceComponent;

import java.awt.*;

public abstract class AbstractViewTreeAction extends AbstractViewDataStructureAction {
    public static final int INITIAL_X = 10;
    public static final int INITIAL_Y = 50;
    public static final int GAP_X = 10;
    public static final int GAP_Y = 60;
    public static final int SIZE_PER_NODE = (Config.WIDTH - 2 * INITIAL_X - 32 * GAP_X) / 31;
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

    public AbstractViewTreeAction(AbstractTreeScreen rootScreen) {
        super(rootScreen);
        drawElements();
        repaint();
    }

    public void drawElements() {
        removeAll();
        TreePanelNode[] elements = getRootScreen().getPanelNodeArray();
        for (TreePanelNode panel : elements) {
            if (panel == null) break;
            add(panel);
        }
    }

    public AbstractTreeScreen getRootScreen() {
        return (AbstractTreeScreen) rootScreen;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        g2d.setStroke(new BasicStroke(2));
        g2d.setRenderingHint(RenderingHints.KEY_RENDERING,
                RenderingHints.VALUE_RENDER_QUALITY);
        TreePanelNode[] elements = getRootScreen().getPanelNodeArray();
        for (int i = 0; i < 15; i++) {
            if (elements[i] == null) continue;
            if (elements[2 * i + 1] != null) {
                ServiceComponent.drawPatternArrow2(
                        g2d,
                        getDefaultX(i),
                        getDefaultY(i) + elements[i].getHeightPanel() / 2,
                        getDefaultX(2 * i + 1) + elements[2 * i + 1].getWidthPanel() / 2,
                        getDefaultY(2 * i + 1),
                        Color.BLACK
                );
            }
            if (elements[2 * i + 2] != null) {
                ServiceComponent.drawPatternArrow2(
                        g2d,
                        getDefaultX(i) + elements[i].getWidthPanel(),
                        getDefaultY(i) + elements[i].getHeightPanel() / 2,
                        getDefaultX(2 * i + 2) + elements[2 * i + 1].getWidthPanel() / 2,
                        getDefaultY(2 * i + 2),
                        Color.BLACK
                );
            }
        }
    }

    public int getDefaultX(int i) {
        return INITIAL_X
                + GAP_X * (INDEX_COLUMNS[i] + 1)
                + SIZE_PER_NODE * INDEX_COLUMNS[i];
    }

    public int getDefaultY(int i) {
        return INITIAL_Y
                + GAP_Y * (INDEX_ROWS[i] + 1)
                + SIZE_PER_NODE * INDEX_ROWS[i];
    }

    public abstract void actionTraversal(int type);
    public abstract void actionInsert(Integer value);
    public abstract void actionDelete(Integer value);
}