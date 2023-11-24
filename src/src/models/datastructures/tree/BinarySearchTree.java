package src.models.datastructures.tree;


public class BinarySearchTree<E extends Comparable<E>> extends LinkedBinaryTree<E> {
    public E findMin() {
        return findMin(root());
    }

    private E findMin(Node<E> root) {
        while (root != null && root.left != null) root = root.left;
        return root == null? null : root.element;
    }

    public Node<E> search(E x) {
        if (x == null) return null;
        Node<E> node = root();
        while(node != null) {
            int compare = x.compareTo(node.element);
            if (compare == 0) return node;
            if (compare < 0) node = node.left;
            if (compare > 0) node = node.right;
        }
        return null;
    }

    public boolean insert(E x) {
        if (x == null) return false;
        if (root() == null) {
            addRoot(x);
            return true;
        }
        Node<E> node = root();
        while (node != null) {
            int compare = x.compareTo(node.element);
            if (compare == 0) return false; // Duplicated value
            if (compare < 0) {
                if (node.left == null) {
                    addLeft(node, x);
                    return true;
                }
                node = node.left;
                continue;
            }
            if (node.right == null) {
                addRight(node, x);
                return true;
            }
            node = node.right;
        }
        return false;
    }

    // Return root of tree after deleted x
    public Node<E> delete(E x) {
        if (x == null) return root();
        root = delete(x, root());
        return root;
    }

    private Node<E> delete(E x, Node<E> root) {
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
        return root;
    }
}
