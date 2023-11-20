package src.components.components.datastructures.tree.binarysearchtree;

import src.components.components.datastructures.tree.AbstractTreeScreen;
import src.components.components.datastructures.tree.AbstractViewTreeAction;
import src.components.components.datastructures.tree.TreePanelNode;
import src.models.datastructures.tree.BinarySearchTree;

public class BinarySearchTreeScreen extends AbstractTreeScreen {
    @Override
    public void createTree() {
        tree = new BinarySearchTree<>();
        for (int i = 0; i < 31; i++) {
            tree.insert(new TreePanelNode(
                    AbstractViewTreeAction.INDEX_ROWS[i],
                    AbstractViewTreeAction.INDEX_COLUMNS[i],
                    i));
        }
    }

    @Override
    public void createViewAction() {
        viewAction = new ViewBinarySearchTreeAction(this);
        add(viewAction);
    }
}