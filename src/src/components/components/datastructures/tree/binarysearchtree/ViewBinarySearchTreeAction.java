package src.components.components.datastructures.tree.binarysearchtree;

import src.components.components.datastructures.tree.AbstractTreeScreen;
import src.components.components.datastructures.tree.AbstractViewTreeAction;


public class ViewBinarySearchTreeAction extends AbstractViewTreeAction {
    public ViewBinarySearchTreeAction(AbstractTreeScreen rootScreen) {
        super(rootScreen);
    }

    @Override
    public BinarySearchTreeScreen getRootScreen() {
        return (BinarySearchTreeScreen) rootScreen;
    }

    @Override
    public void actionTraversal(int type) {

    }

    @Override
    public void actionInsert(Integer value) {

    }

    @Override
    public void actionDelete(Integer value) {

    }

}