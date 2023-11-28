package src.models.datastructures.tree;

public class AVLTree<E extends Comparable<E>> extends BinarySearchTree<E> {
    @Override
    public Node<E> insert(E x) {
        Node<E> insertedNode = super.insert(x);
        if (insertedNode == null) return null;
        Node<E> node = insertedNode;
        while (true) {
            int heightL = height(node.left);
            int heightR = height(node.right);
            if (heightL - heightR > 1) {
                int heightLL = height(node.left.left);
                int heightLR = height(node.left.right);
                if (heightLR > heightLL) {
                    node.left = rotateLeft(node.left);
                    if (node.left != null) node.left.parent = node;
                }
                node = rotateRight(node);
            } else if (heightR - heightL > 1) {
                int heightRL = height(node.right.left);
                int heightRR = height(node.right.right);
                if (heightRL > heightRR) {
                    node.right = rotateRight(node.right);
                    if (node.right != null) node.right.parent = node;
                }
                node = rotateLeft(node);
            }
            if (node.parent == null) break;
            node = node.parent;
        }
        root = node;
        return insertedNode;
    }

    @Override
    protected Node<E> delete(E x, Node<E> root) {
        // STEP 1: DELETE NODE
        if (root == null) return null;
        int compare = x.compareTo(root.element);
        // case 1: has no child
        if (root.left == null && root.right == null)
            return compare == 0? null : root;
        // case 2: only has child left
        if (root.left != null && root.right == null) {
            if (compare == 0) return root.left;
            root.left = delete(x, root.left);
            if (root.left != null) root.left.parent = root; // update parent for root.left
            return root;
        }
        // case 3: only has child right
        if (root.left == null) {
            if (compare == 0) return root.right;
            root.right = delete(x, root.right);
            if (root.right != null) root.right.parent = root; // update parent for root.right
            return root;
        }
        // case 4: has two children
        if (compare == 0) {
            root.element = findMin(root.right);
            root.right = delete(root.element, root.right);
        } else if (compare < 0) {
            root.left = delete(x, root.left);
            if (root.left != null) root.left.parent = root; // update parent for root.left
        } else {
            root.right = delete(x, root.right);
            if (root.right != null) root.right.parent = root; // update parent for root.right
        }

        // STEP 2: BALANCE TREE
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
        }

        return root;
    }

    private Node<E> rotateLeft(Node<E> node) {
        if (node == null || node.right == null) return null;
        Node<E> right = node.right;
        node.right = right.left;
        if (node.right != null) node.right.parent = node;
        right.left = node;
        right.parent = node.parent;
        node.parent = right;
        if (right.parent != null) {
            if (right.parent.left == node) right.parent.left = right;
            if (right.parent.right == node) right.parent.right = right;
        }
        return right;
    }

    private Node<E> rotateRight(Node<E> node) {
        if (node == null || node.left == null) return null;
        Node<E> left = node.left;
        node.left = left.right;
        if (node.left != null) node.left.parent = node;
        left.right = node;
        left.parent = node.parent;
        node.parent = left;
        if (left.parent != null) {
            if (left.parent.left == node) left.parent.left = left;
            if (left.parent.right == node) left.parent.right = left;
        }
        return left;
    }
}
