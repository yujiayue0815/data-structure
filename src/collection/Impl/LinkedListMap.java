package collection.Impl;

import collection.Map;

public class LinkedListMap<K, V> implements Map<K, V> {

    /**
     * Map's element size.
     */
    private int size;

    /**
     * Dummy head node.
     */
    private Node dummyHead;

    public LinkedListMap() {
        this.size = 0;
        this.dummyHead = new Node();
    }

    private class Node {
        public K key;
        public V value;
        public Node next;

        public Node() {
        }

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node(K key, V value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
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
        Node node = getNode(key);
        if (node == null) {
            dummyHead.next = new Node(key, value, dummyHead.next);
            size++;
        } else {
            node.value = value;
        }

    }

    @Override
    public void set(K key, V value) {
        Node node = getNode(key);
        if (node == null)
            throw new IllegalArgumentException(key + "is not exist.");
        else
            node.value = value;
    }

    /**
     * Get a node to key.
     *
     * @param key
     * @return
     */
    private Node getNode(K key) {
        Node cur = dummyHead.next;
        while (cur != null) {
            if (cur.key.equals(key))
                return cur;
            else
                cur = cur.next;
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return getNode(key) != null;
    }

    @Override
    public V remove(K key) {
        Node prev = this.dummyHead;
        while (prev.next != null) {
            if (prev.next.key.equals(key))
                break;
            prev = prev.next;
        }

        if (prev.next != null) {
            Node delNode = prev.next;
            prev.next = delNode.next;
            delNode.next = null;
            return delNode.value;
        }
        return null;
    }

    @Override
    public V get(K key) {
        Node node = getNode(key);
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
}
