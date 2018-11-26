package collection.Impl;

import collection.Map;
import tree.BinarySearchTree;

public class BianrySearchTreeMap<K extends Comparable<K>, V> implements Map<K, V> {

    private int size;
    private Node root;

    private class Node {
        public K key;
        public V value;
        public Node left;
        public Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    @Override
    public void put(K key, V value) {
        root = addVal(root, key, value);
    }

    private Node addVal(Node node, K key, V value) {
        if (node == null) {
            size++;
            return new Node(key, value);
        }

        if (node.key.compareTo(key) < 0)
            node.left = addVal(node.left, key, value);
        else if (node.key.compareTo(key) > 0)
            node.right = addVal(node.right, key, value);
        else
            node.value = value;
        return node;
    }

    @Override
    public boolean contains(K key) {
        return getNode(root, key) != null;
    }

    private final Node getNode(Node node, K key) {
        if (node == null)
            return null;
        if (node.key.compareTo(key) == 0)
            return node;
        else if (node.key.compareTo(key) < 0)
            return getNode(node.left, key);
        else
            return getNode(node.right, key);
    }

    @Override
    public V remove(K key) {
        Node node = remove(root, key);
        return node == null ? null : node.value;
    }

    private Node remove(Node node, K key) {
        if (node == null)
            return null;
        if (node.key.compareTo(key) < 0) {
            return remove(node.left, key);
        }
        if (node.key.compareTo(key) > 0) {
            return remove(node.right, key);
        }

        if (node.left == null) { //等于时左子树为null
            Node right = node.right;
            node.right = null;
            size--;
            return right;
        }
        if (node.right == null) {
            Node left = node.left;
            node.left = null;
            size--;
            return left;
        }
        //当都不为空的时候找到后继节点替代当前节点
        Node successor = minimum(node.right);
        successor.right = removeMin(node.right);
        successor.left = node.left;
        node.left = node.right = null;
        return successor;
    }

    /**
     * Remove node's min node.
     *
     * @param node
     * @return
     */
    private Node removeMin(Node node) {
        if (node.left == null) {
            Node right = node.right;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * Get min's node.
     *
     * @param node
     * @return
     */
    private Node minimum(Node node) {

        if (node.left == null)
            return node;
        return minimum(node.left);
    }

    @Override
    public V get(K key) {
        Node node = getNode(root, key);
        return node == null ? null : node.value;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(root, key);
        if (node != null)
            node.value = value;
    }
}
