package week38;

import week37.ISymTable;

import java.util.ArrayDeque;
import java.util.Deque;


/**
 * Vast majority of this code is an exact copy of Øyvind's BinarySearchTree, but with a few
 * added lines to the insert function, and some helper functions (the red-black tree toolbox).
 *
 * Note: This implementation still does Hibbard deletion, which violates the balance requirement
 * of a red-black tree, and might also violate the color requirements. However, it does not violate
 * the order requirement, so correctness is preserved. For applications without deletion, the
 * runtime guarantees of O(lg n) is preserved.
 *
 * @author Torstein Strømme
 */
public class RedBlackBST<Key extends Comparable<Key>, Value> implements ISymTable<Key, Value> {
    private enum Color { RED, BLACK }

    private class Node {
        Key key;
        Value value;
        Node left, right;
        int size;
        Color color; //// New field which distinguish red-black trees from BST's

        Node(Key key, Value value, Color color) {
            this.key = key;
            this.value = value;
            this.size = 1;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == Color.RED;
    }

    // Our toolbox for working with red-black trees:
    //   * rotate left
    //   * rotate right
    //   * flip colors


    private Node rotateLeft(Node h) {
//        assert isRed(h.right);
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = Color.RED;

        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private Node rotateRight(Node h) {
//        assert isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = Color.RED;

        x.size = h.size;
        h.size = 1 + size(h.left) + size(h.right);
        return x;
    }

    private void flipColors(Node h) {
//        assert !isRed(h);
//        assert isRed(h.left);
//        assert isRed(h.right);
        h.color = Color.RED;
        h.left.color = Color.BLACK;
        h.right.color = Color.BLACK;
    }



    private Node root;

    @Override
    public void put(Key key, Value value) {
        root = insert(root, key, value);
        root.color = Color.BLACK;
    }

    private Node insert(Node node, Key key, Value value) {
        if (node == null) {
            return new Node(key, value, Color.RED);
        }

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = insert(node.left, key, value);
        } else if (compare > 0) {
            node.right = insert(node.right, key, value);
        } else {
            node.value = value;
        }

        // Magic of a red-black tree: three lines of code which makes it balanced (see lecture notes)
        if (isRed(node.right) && !isRed(node.left))     node = rotateLeft(node);
        if (isRed(node.left) && isRed(node.left.left))  node = rotateRight(node);
        if (isRed(node.left) && isRed(node.right))      flipColors(node);

        node.size = size(node.left) + size(node.right) + 1;

        return node;
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return node.size;
    }

    @Override
    public Value get(Key key) {
        return find(root, key);
    }

    private Value find(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            return find(node.left, key);
        } else if (compare > 0) {
            return find(node.right, key);
        } else {
            return node.value;
        }
    }

    @Override
    public boolean containsKey(Key key) {
        return get(key) != null;
    }


    private Node minimum(Node node) {
        if (node == null) {
            // this is a bad situation
            throw new IllegalArgumentException("Can't find minimum in a non-existing tree!");
        }
        if (node.left == null) {
            return node; // we have no left children, so this must be the smallest
        }
        return minimum(node.left);
    }

    private Node deleteMinimum(Node node) {
        if (node == null) {
            throw new IllegalArgumentException("Can't delete minimum in a non-existing tree!");
        }
        if (node.left == null) {
            return node.right;
        }
        node.left = deleteMinimum(node.left);
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }

    @Override
    public void delete(Key key) {
        root = deleteKey(root, key);
    }

    private Node deleteKey(Node node, Key key) {
        if (node == null) {
            return null;
        }

        int compare = key.compareTo(node.key);
        if (compare < 0) {
            node.left = deleteKey(node.left, key);
        } else if (compare > 0) {
            node.right = deleteKey(node.right, key);
        } else {
            // this is the node that we want to delete
            if (node.left == null) {
                return node.right;
            }
            if (node.right == null) {
                return node.left;
            }
            // we have children, so we need to promote a child into this spot
            // our choices are either the smallest node in the right subtree,
            // or the biggest node in the left subtree (can you see why?)


            // To "move" the smallest right node into this position, we
            // 1. find it
            // 2. delete it from the subtree
            // 3. fix the links so the tree remains intact
            Node minRight = minimum(node.right);
            minRight.right = deleteMinimum(node.right);
            minRight.left = node.left;

            // finally we set the node we return to be the minRight node
            node = minRight;
        }
        node.size = size(node.left) + size(node.right) + 1;
        return node;
    }


    @Override
    public int size() {
        return size(root);
    }

    @Override
    public Iterable<Key> keys() {
        Deque<Key> queue = new ArrayDeque<>();
        keys(root, queue);
        return queue;
    }

    private void keys(Node node, Deque<Key> queue) {
        if (node == null) return;
        keys(node.left, queue);
        queue.addLast(node.key);
        keys(node.right, queue);
    }

}
