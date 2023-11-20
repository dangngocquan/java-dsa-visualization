package src.models.datastructures.tree;

public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {
    @Override
    public boolean insert(E x) {
        boolean isSuccess = super.insert(x);
        if (isSuccess) root = balanceTree(root);
        return isSuccess;
    }

    @Override
    public Node<E> delete(E x) {
        Node<E> removeNode = super.delete(x);
        if (removeNode != null) root = balanceTree(root);
        return removeNode;
    }

    private Node<E> rotateLeft(Node<E> node) {
        if (node == null || node.right == null) return null;
        Node<E> right = node.right;
        node.right = right.left;
        if (node.right != null) node.right.parent = node;
        right.left = node;
        node.parent = right;
        return right;
    }

    private Node<E> rotateRight(Node<E> node) {
        if (node == null || node.left == null) return null;
        Node<E> left = node.left;
        node.left = left.right;
        if (node.left != null) node.left.parent = node;
        left.right = node;
        node.parent = left;
        return left;
    }

    public Node<E> balanceTree(Node<E> root) {
        if (root == null) return null;
        int heightL = height(root.left);
        int heightR = height(root.right);
        if (heightL - heightR > 1) {
            int heightLL = height(root.left.left);
            int heightLR = height(root.left.right);
            if (heightLR > heightLL) {
                root.left = rotateLeft(root.left);
                if (root.left != null) root.left.parent = root;
            }
            root = rotateRight(root);
        } else if (heightR - heightL > 1) {
            int heightRL = height(root.right.left);
            int heightRR = height(root.right.right);
            if (heightRL > heightRR) {
                root.right = rotateRight(root.right);
                if (root.right != null) root.right.parent = root;
            }
            root = rotateLeft(root);
        } else {
            root.left = balanceTree(root.left);
            if (root.left != null) root.left.parent = root;
            root.right = balanceTree(root.right);
            if (root.right != null) root.right.parent = root;
        }
        return root;
    }
}
