package src.components.components.datastructures.tree.avltree;

import src.components.components.datastructures.tree.AbstractTreeScreen;
import src.components.components.datastructures.tree.AbstractViewTreeAction;
import src.components.components.datastructures.tree.TreePanelNode;
import src.models.datastructures.tree.AVLTree;

public class AVLTreeScreen extends AbstractTreeScreen {
    @Override
    public void createTree() {
        tree = new AVLTree<>();
//        for (int i = 0; i < 63; i++) {
//            tree.insert(new TreePanelNode(
//                    AbstractViewTreeAction.INDEX_ROWS[i],
//                    AbstractViewTreeAction.INDEX_COLUMNS[i],
//                    AbstractViewTreeAction.INDEX_COLUMNS[i]));
//        }
    }

    @Override
    public void createViewAction() {
        viewAction = new ViewAVLTreeAction(this);
        add(viewAction);
    }
}