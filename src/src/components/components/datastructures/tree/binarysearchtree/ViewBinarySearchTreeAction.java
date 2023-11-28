package src.components.components.datastructures.tree.binarysearchtree;

import src.components.components.datastructures.tree.AbstractTreeScreen;
import src.components.components.datastructures.tree.AbstractViewTreeAction;
import src.components.components.datastructures.tree.actionanimation.TreeActionDelete;
import src.components.components.datastructures.tree.actionanimation.TreeActionInsert;


public class ViewBinarySearchTreeAction extends AbstractViewTreeAction {
    public ViewBinarySearchTreeAction(AbstractTreeScreen rootScreen) {
        super(rootScreen);
    }

    @Override
    public BinarySearchTreeScreen getRootScreen() {
        return (BinarySearchTreeScreen) rootScreen;
    }

    @Override
    public void actionInsert(Integer value) {
        getRootScreen().getViewAction().resetPanelsClone();
        new TreeActionInsert(value, getRootScreen(), 500, null).start();
    }

    @Override
    public void actionDelete(Integer value) {
        getRootScreen().getViewAction().resetPanelsClone();
        new TreeActionDelete(value, getRootScreen(), 500, null).start();
    }
}