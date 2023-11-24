package src.models.datastructures.tree;

public class LinkedBinaryTree<E extends Comparable<E>> extends AbstractBinaryTree<E, LinkedBinaryTree.Node<E>> {
    public static class Node<E extends Comparable<E>> implements Comparable<Node<E>> {
        public E element;
        public Node<E> parent;
        public Node<E> left;
        public Node<E> right;

        public Node(
                E element,
                Node<E> parent,
                Node<E> left,
                Node<E> right) {
            this.element = element;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public String toString() {
            return element == null? "null" : element.toString();
        }

        @Override
        public int compareTo(Node<E> o) {
            return this.element.compareTo(o.element);
        }
    }

    public Node<E> root;

    @Override
    public Node<E> root() {
        return root;
    }

    @Override
    public E elementOfNode(Node<E> p) {
        if (p == null) return null;
        return p.element;
    }

    @Override
    public Node<E> parent(Node<E> p) {
        if (p == null) return null;
        return p.parent;
    }

    @Override
    public Node<E> left(Node<E> p) {
        if (p == null) return null;
        return p.left;
    }

    @Override
    public Node<E> right(Node<E> p) {
        if (p == null) return null;
        return p.right;
    }

    @Override
    public Node<E> sibling(Node<E> p) {
        if (p == null) return null;
        if (p.parent == null) return null;
        return p.parent.left == p? p.parent.right : p.parent.left;
    }

    public Node<E> addRoot(E element) {
        if (root == null) {
            root = new Node<>(
                    element, null, null, null
            );
        }
        return root;
    }

    public Node<E> addLeft(Node<E> p, E element) {
        if (p == null) return null;
        if (p.left == null) {
            p.left = new Node<>(
                    element, p, null, null
            );
        }
        return p.left;
    }

    public Node<E> addRight(Node<E> p, E element) {
        if (p == null) return null;
        if (p.right == null) {
            p.right = new Node<>(
                    element, p, null, null
            );
        }
        return p.right;
    }

    public Node<E> addRoot(Node<E> p) {
        if (root == null) {
            root = p;
        }
        return root;
    }

    public Node<E> addLeft(Node<E> p, Node<E> leftP) {
        if (p == null) return null;
        if (p.left == null) {
            p.left = leftP;
        }
        return p.left;
    }

    public Node<E> addRight(Node<E> p, Node<E> rightP) {
        if (p == null) return null;
        if (p.right == null) {
            p.right = rightP;
        }
        return p.right;
    }

}
