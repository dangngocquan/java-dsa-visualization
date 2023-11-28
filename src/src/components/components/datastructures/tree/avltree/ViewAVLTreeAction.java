package src.components.components.datastructures.tree.avltree;

import src.components.components.datastructures.tree.AbstractTreeScreen;
import src.components.components.datastructures.tree.AbstractViewTreeAction;
import src.components.components.datastructures.tree.TreePanelNode;
import src.components.components.datastructures.tree.actionanimation.TreeActionDelete;
import src.components.components.datastructures.tree.actionanimation.TreeActionInsert;

public class ViewAVLTreeAction extends AbstractViewTreeAction {
    public ViewAVLTreeAction(AbstractTreeScreen rootScreen) {
        super(rootScreen);
    }

    @Override
    public void actionInsert(Integer value) {
        getRootScreen().getViewAction().resetPanelsClone();
        new AVLTreeActionInsert(value, getRootScreen(), 500, null).start();
//        getRootScreen().tree.insert(new TreePanelNode(0, 0, value));
//        getRootScreen().endAction();
//        getRootScreen().getViewAction().resetPanelsClone();
    }

    @Override
    public void actionDelete(Integer value) {
        getRootScreen().getViewAction().resetPanelsClone();
        new AVLTreeActionDelete(value, getRootScreen(), 500, null).start();
//        TreePanelNode node = null;
//        for (TreePanelNode e : panelsClone) {
//            if (e != null && e.getValue() == value) {
//                node = e;
//                break;
//            }
//        }
//        getRootScreen().tree.delete(node);
//        getRootScreen().endAction();
//        getRootScreen().getViewAction().resetPanelsClone();
    }
}
