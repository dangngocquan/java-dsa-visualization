package src.tests;

import src.models.datastructures.tree.ArrayBinaryTree;

public class TestArrayBinaryTree implements Test {
    @Override
    public void run() {
        System.out.println("TEST ARRAY BINARY TREE\n");
        System.out.printf(
                "%-25s %-15s %s\n",
                "Method",
                "Return",
                "Binary Tree"
        );
        System.out.println("-".repeat(90));

        ArrayBinaryTree<String> tree = new ArrayBinaryTree<>();
        root(tree);
        size(tree);
        isEmpty(tree);
        numberChildren(tree, 1);
        setRoot(tree, "A");
        Integer nodeA = tree.root(); // save to use later
        setLeft(tree, nodeA, "A0");
        Integer nodeA0 = tree.left(nodeA); // save to use later
        setRight(tree, nodeA, "A1");
        Integer nodeA1 = tree.right(nodeA); // save to use later
        element(tree, nodeA);
        element(tree, nodeA0);
        element(tree, nodeA1);
        root(tree);
        size(tree);
        isEmpty(tree);
        numberChildren(tree, nodeA);
        parent(tree, nodeA0);
        parent(tree, nodeA1);
        left(tree, nodeA);
        right(tree, nodeA);
        sibling(tree, nodeA0);
        sibling(tree, nodeA1);
        setLeft(tree, nodeA0, "A00");
        Integer nodeA00 = tree.left(nodeA0); // save to use later
        setLeft(tree, nodeA1, "A10");
        setRight(tree, nodeA0, "A01");
        size(tree);
        setLeft(tree, nodeA00, "A000");
        size(tree);
        setLeft(tree, nodeA, null);
        size(tree);
    }

    public void root(ArrayBinaryTree<String> tree) {
        System.out.printf(
                "%-25s %-15s %s\n",
                "root()",
                tree.root(),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void size(ArrayBinaryTree<String> tree) {
        System.out.printf(
                "%-25s %-15s %s\n",
                "size()",
                tree.size(),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void isEmpty(ArrayBinaryTree<String> tree) {
        System.out.printf(
                "%-25s %-15s %s\n",
                "isEmpty()",
                tree.isEmpty(),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void numberChildren(ArrayBinaryTree<String> tree, Integer p) {
        System.out.printf(
                "%-25s %-15s %s\n",
                String.format("numberChildren(%s)", p),
                tree.numberChildren(p),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void element(ArrayBinaryTree<String> tree, Integer p) {
        System.out.printf(
                "%-25s %-15s %s\n",
                String.format("element(%s)", p),
                tree.elementOfNode(p),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void parent(ArrayBinaryTree<String> tree, Integer p) {
        System.out.printf(
                "%-25s %-15s %s\n",
                String.format("parent(%s)", p),
                tree.parent(p),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void left(ArrayBinaryTree<String> tree, Integer p) {
        System.out.printf(
                "%-25s %-15s %s\n",
                String.format("left(%s)", p),
                tree.left(p),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void right(ArrayBinaryTree<String> tree, Integer p) {
        System.out.printf(
                "%-25s %-15s %s\n",
                String.format("right(%s)", p),
                tree.right(p),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void sibling(ArrayBinaryTree<String> tree, Integer p) {
        System.out.printf(
                "%-25s %-15s %s\n",
                String.format("sibling(%s)", p),
                tree.sibling(p),
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void setRoot(ArrayBinaryTree<String> tree, String element) {
        tree.setRoot(element);
        System.out.printf(
                "%-25s %-15s %s\n",
                String.format("setRoot(%s)", element),
                "",
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void setLeft(ArrayBinaryTree<String> tree, Integer p, String element) {
        tree.setLeft(p, element);
        System.out.printf(
                "%-25s %-15s %s\n",
                String.format("setLeft(%s, %s)", p, element),
                "",
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }

    public void setRight(ArrayBinaryTree<String> tree, Integer p, String element) {
        tree.setRight(p, element);
        System.out.printf(
                "%-25s %-15s %s\n",
                String.format("setRight(%s, %s)", p, element),
                "",
                tree.toString().replace("\n", "\n" + " ".repeat(42))
        );
        System.out.println("-".repeat(90));
    }
}
