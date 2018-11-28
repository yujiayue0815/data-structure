package tree;

import java.util.*;

/**
 * AVL树实现自平衡的机制，采用左右旋转的方式来实现。
 *
 * @param <K>
 * @param <V>
 */
public class AVLTree<K extends Comparable<K>, V> {
    /**
     * 节点
     *
     * @param <K,V>
     */
    private class Node<K, V> {
        public K key;
        public V value;
        public Node<K, V> left;
        public Node<K, V> right;
        public int height;


        public Node(K key, V value) {
            this.key = key;
            this.value = value;
            this.left = null;
            this.right = null;
            this.height = 1;
        }
    }

    private Node<K, V> root;
    private Integer size;

    public AVLTree() {
        this.size = 0;
    }

    /**
     * Get tree size.
     *
     * @return
     */
    public Integer getSize() {
        return size;
    }

    /**
     * Judge tree is empty
     *
     * @return
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 获取树的高度
     *
     * @param node
     * @return
     */
    private int getHeight(Node<K, V> node) {
        if (node == null)
            return 0;
        else
            return node.height;
    }

    /**
     * 判断是否是二分搜索树
     *
     * @return
     */
    public boolean isBST() {
        List<K> keys = new ArrayList<>();
        inOrder(root, keys);
        for (int i = 1; i < keys.size(); i++) {
            if (keys.get(i - 1).compareTo(keys.get(i)) < 0)
                return false;
        }
        return true;
    }

    /**
     * 判断是不是平衡树
     *
     * @return
     */
    public boolean isBalance() {
        return isBalance(root);
    }

    private boolean isBalance(Node<K, V> node) {
        if (node == null)
            return true;
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            return false;
        else
            return isBalance(node.left) && isBalance(node.right);
    }

    /**
     * 中序遍历
     *
     * @param node
     * @param keys
     */
    private void inOrder(Node<K, V> node, List<K> keys) {
        if (node == null)
            return;
        inOrder(node.left, keys);
        keys.add(node.key);
        inOrder(node.right, keys);
    }

    /**
     * 获取树的平衡因子
     *
     * @param node
     * @return
     */
    private int getBalanceFactor(Node<K, V> node) {
        if (node == null)
            return 0;
        else
            return getHeight(node.left) - getHeight(node.right);
    }

    /**
     * 添加元素
     *
     * @param key
     * @param value
     */
    public void add(K key, V value) {
        root = add(root, key, value);
    }


    /**
     * 向以node为根节点的树中插入元素E
     *
     * @param node
     * @param key
     */
    private Node<K, V> add(Node<K, V> node, K key, V value) {
        if (node == null) {
            size++;
            return new Node<>(key, value);
        }
        if (key.compareTo(node.key) < 0)
            node.left = add(node.left, key, value);
        else if (key.compareTo(node.key) > 0)
            node.right = add(node.right, key, value);
        else
            node.value = value;
        //更新树的高度
        node.height = 1 + Math.max(getHeight(node.left), getHeight(node.right));

        //计算平衡因子
        int balanceFactor = getBalanceFactor(node);
        if (Math.abs(balanceFactor) > 1)
            System.err.println(balanceFactor);

        // 平衡性维护
        if (balanceFactor > 1 && getBalanceFactor(node.left) >= 0)
            return rightRotate(node);

        if (balanceFactor < -1 && getBalanceFactor(node.right) <= 0)
            return leftRotate(node);
        return node;
    }

    /**
     * 对节点经行向右旋转操作，返回旋转后新的根节点
     * y                                   x
     * / |        向右旋转                  /   |
     * x  T4   ----------------------- >   z     y
     * / \                                 / \    / \
     * z   T3                              T1 T2  T3 T4
     * / \
     * T1 T2
     *
     * @param y
     * @return
     */
    private Node<K, V> rightRotate(Node<K, V> y) {
        Node<K, V> x = y.left;
        Node<K, V> T3 = x.right;
        //向右旋转过程
        x.right = y;
        y.left = T3;

        //更新高度
        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(y.right)) + 1;
        return x;
    }

    /**
     * 向左旋转
     *
     * @param y
     * @return
     */
    private Node<K, V> leftRotate(Node<K, V> y) {
        Node<K, V> x = y.right;
        Node<K, V> T2 = x.left;

        x.left = y;
        y.right = T2;

        y.height = Math.max(getHeight(y.left), getHeight(y.right)) + 1;
        x.height = Math.max(getHeight(x.left), getHeight(x.right)) + 1;
        return x;

    }


    /**
     * 查看二分搜索查找元素
     *
     * @param e
     * @return
     */
    public boolean contains(K e) {
        return contains(root, e);
    }

    /**
     * 最小值
     *
     * @return
     */
    public K minimum() {
        if (size == 0)
            throw new IllegalArgumentException("tree is NULL");
        return minimum(root).key;
    }

    /**
     * 非递归实现最小值
     *
     * @return
     */
    public K minimumNR() {
        if (size == 0)
            throw new IllegalArgumentException("tree is NULL");

        Node<K, V> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.key;
    }

    /**
     * 删除最小值
     *
     * @return
     */
    public K removeMin() {
        K e = minimumNR();
        root = removeMin(root);
        return e;
    }

    /**
     * 删除指定节点的数据
     *
     * @param node
     * @return
     */
    private Node<K, V> removeMin(Node<K, V> node) {
        if (node.left == null) {
            Node<K, V> right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    /**
     * 删除指定元素
     *
     * @param e
     */
    public void remove(K e) {
        remove(root, e);
    }

    /**
     * 删除制定的元素
     *
     * @param node
     * @param e
     */
    private Node<K, V> remove(Node<K, V> node, K e) {
        if (node == null)
            return null;
        if (e.compareTo(node.key) < 0) {
            return remove(node.left, e);
        }
        if (e.compareTo(node.key) > 0) {
            return remove(node.right, e);
        }

        if (node.left == null) { //等于时左子树为null
            Node<K, V> right = node.right;
            node.right = null;
            size--;
            return right;
        }
        if (node.right == null) {
            Node<K, V> left = node.left;
            node.left = null;
            size--;
            return left;
        }
        //当都不为空的时候找到后继节点替代当前节点
        Node<K, V> successor = minimum(node.right);
        successor.right = removeMin(node.right);
        successor.left = node.left;
        node.left = node.right = null;
        return successor;


    }

    /**
     * 删除最大值
     *
     * @return
     */
    public K removeMax() {
        K maximum = maximum();
        root = removeMax(root);
        return maximum;
    }

    /**
     * 删除最大值递归
     *
     * @param node
     * @return
     */
    private Node<K, V> removeMax(Node<K, V> node) {
        if (node.right == null) {
            Node<K, V> left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    /**
     * 递归最小值
     *
     * @param node
     * @return
     */
    private Node<K, V> minimum(Node<K, V> node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    /**
     * 最大值
     *
     * @return
     */
    public K maximum() {
        if (size == 0)
            throw new IllegalArgumentException("tree is NULL");
        return maximum(root).key;
    }

    private Node<K, V> maximum(Node<K, V> node) {
        if (node.right == null)
            return node;

        return maximum(node.right);
    }

    /**
     * 层序遍历
     */
    public void levelOrder() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            Node cur = queue.remove();
            System.out.println(cur.key);
            if (cur.left != null)
                queue.add(cur.left);
            if (cur.right != null)
                queue.add(cur.right);
        }
    }

    /**
     * 查找指定节点的元素
     *
     * @param node
     * @param e
     * @return
     */
    private boolean contains(Node<K, V> node, K e) {
        if (node == null)
            return false;

        else if (e.compareTo(node.key) == 0)
            return true;
        else if (e.compareTo(node.key) < 0)
            return contains(node.left, e);
        else
            return contains(node.right, e);
    }

    /**
     * 非递归前序遍历
     */
    public void preOrderNR() {
        Stack<Node> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            Node pop = stack.pop();
            System.out.println(pop.key);
            if (pop.right != null)
                stack.push(pop.right);
            if (pop.left != null)
                stack.push(pop.left);
        }
    }

    /**
     * 前序遍历二分查找树
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 前序遍历以node为根的二分搜索树，递归算法
     *
     * @param node
     */
    private void preOrder(Node<K, V> node) {
        if (node == null)
            return;
        System.out.println(node.key);
        //递归
        preOrder(node.left);
        preOrder(node.right);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        generateBSTString(root, 0, stringBuilder);
        return stringBuilder.toString();
    }

    private void generateBSTString(Node<K, V> node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(depthString(depth) + "NULL\n");
            return;
        }
        res.append(depthString(depth) + node.key + "\n");
        generateBSTString(node.left, depth + 1, res);
        generateBSTString(node.right, depth + 1, res);
    }

    /**
     * 中序遍历,二分查找树中序遍历是按照从小到大的顺序排列的
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历递归算法
     *
     * @param node
     */
    private void inOrder(Node<K, V> node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.key);
        inOrder(node.right);
    }


    /**
     * 后续遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<K, V> node) {
        if (node == null)
            return;
        inOrder(node.left);
        inOrder(node.right);
        System.out.println(node.key);
    }

    /**
     * 深度字符串
     *
     * @param depth
     * @return
     */
    private String depthString(int depth) {
        StringBuilder rs = new StringBuilder();
        for (int i = 0; i < depth; i++) {
            rs.append("-");
        }
        return rs.toString();
    }
}
