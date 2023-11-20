package src.tests;

import src.models.datastructures.tree.ArrayBinaryTree;

public class TestTree implements Test {
    @Override
    public void run() {
        new TestArrayBinaryTree().run();
        new TestLinkedBinaryTree().run();
        new TestBinarySearchTree().run();
        new TestAVLTree().run();
    }



}
