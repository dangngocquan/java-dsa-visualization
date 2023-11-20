package src.tests;

import src.models.datastructures.tree.AVLTree;

public class TestAVLTree implements Test {
    public void run() {
        testTree();
    }

    public void testTree() {
        System.out.println("TEST AVL TREE\n");
        System.out.printf(
                "%-25s %-15s %s\n",
                "Method",
                "Return",
                "AVL Tree"
        );
        System.out.println("-".repeat(90));

        AVLTree<Integer> tree = new AVLTree<>();
        testInsert(tree, 10);
        testInsert(tree, 5);
        testInsert(tree, 2);
        testInsert(tree, 1);
        testInsert(tree, 3);
        testInsert(tree, 4);
        testInsert(tree, 15);
        testInsert(tree, 20);
        testInsert(tree, 19);
        testInsert(tree, 18);
        testInsert(tree, 17);
    }

    public void testInsert(AVLTree<Integer> tree, Integer value) {
        System.out.printf(
                "%-25s %-15s %s\n",
                "insert(value=" + value + ")",
                tree.insert(value),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }
}
