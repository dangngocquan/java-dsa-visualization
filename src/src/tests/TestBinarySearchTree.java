package src.tests;

import src.models.datastructures.tree.BinarySearchTree;

public class TestBinarySearchTree implements Test {
    public void run() {
        testBinarySearchTree();
    }

    public void testBinarySearchTree() {
        System.out.println("TEST BINARY SEARCH TREE\n");
        System.out.printf(
                "%-25s %-15s %s\n",
                "Method",
                "Return",
                "Binary Search Tree"
        );
        System.out.println("-".repeat(90));

        BinarySearchTree<Integer> tree = new BinarySearchTree<>();
        testInsert(tree, 5);
        testInsert(tree, 6);
        testInsert(tree, 4);
        testInsert(tree, 3);
        testFindMin(tree);
        testSearch(tree, 5);
        testSearch(tree, 7);
        testDelete(tree, 6);
        testInsert(tree, 1);
        testInsert(tree, 2);
        testInsert(tree, 7);
        testInsert(tree, 6);
        testDelete(tree, 1);
    }

    public void testFindMin(BinarySearchTree<Integer> tree) {
        System.out.printf(
                "%-25s %-15s %s\n",
                "findMin()",
                tree.findMin(),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void testSearch(BinarySearchTree<Integer> tree, Integer value) {
        System.out.printf(
                "%-25s %-15s %s\n",
                "search(value=" + value + ")",
                tree.search(value),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void testInsert(BinarySearchTree<Integer> tree, Integer value) {
        System.out.printf(
                "%-25s %-15s %s\n",
                "insert(value=" + value + ")",
                tree.insert(value),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void testDelete(BinarySearchTree<Integer> tree, Integer value) {
        System.out.printf(
                "%-25s %-15s %s\n",
                "delete(value=" + value + ")",
                tree.delete(value),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

}
