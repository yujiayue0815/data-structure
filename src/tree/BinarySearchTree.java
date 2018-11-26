package tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinarySearchTree<E extends Comparable> {
    /**
     * 节点
     *
     * @param <E>
     */
    private class Node<E> {
        E e;
        Node<E> left;
        Node<E> right;

        public Node(E e) {
            this.e = e;
        }
    }

    private Node<E> root;
    private Integer size;

    public BinarySearchTree() {
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
     * 添加元素
     *
     * @param e
     */
    public void add(E e) {
        root = add(root, e);
    }


    /**
     * 向以node为根节点的树中插入元素E
     *
     * @param node
     * @param e
     */
    private Node<E> add(Node node, E e) {
        if (node == null) {
            size++;
            return new Node<>(e);
        }
        if (e.compareTo(node.e) < 0)
            node.left = add(node.left, e);
        else
            node.right = add(node.right, e);
        return node;
    }

    /**
     * 查看二分搜索查找元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        return contains(root, e);
    }

    /**
     * 最小值
     *
     * @return
     */
    public E minimum() {
        if (size == 0)
            throw new IllegalArgumentException("tree is NULL");
        return minimum(root).e;
    }

    /**
     * 非递归实现最小值
     *
     * @return
     */
    public E minimumNR() {
        if (size == 0)
            throw new IllegalArgumentException("tree is NULL");

        Node<E> node = root;
        while (node.left != null) {
            node = node.left;
        }
        return node.e;
    }

    /**
     * 删除最小值
     *
     * @return
     */
    public E removeMin() {
        E e = minimumNR();
        root = removeMin(root);
        return e;
    }

    /**
     * 删除指定节点的数据
     *
     * @param node
     * @return
     */
    private Node<E> removeMin(Node<E> node) {
        if (node.left == null) {
            Node<E> right = node.right;
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
    public void remove(E e) {
        remove(root, e);
    }

    /**
     * 删除制定的元素
     *
     * @param node
     * @param e
     */
    private Node<E> remove(Node<E> node, E e) {
        if (node == null)
            return null;
        if (e.compareTo(node.e) < 0) {
            return remove(node.left, e);
        }
        if (e.compareTo(node.e) > 0) {
            return remove(node.right, e);
        }

        if (node.left == null) { //等于时左子树为null
            Node<E> right = node.right;
            node.right = null;
            size--;
            return right;
        }
        if (node.right == null) {
            Node<E> left = node.left;
            node.left = null;
            size--;
            return left;
        }
        //当都不为空的时候找到后继节点替代当前节点
        Node<E> successor = minimum(node.right);
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
    public E removeMax() {
        E maximum = maximum();
        root = removeMax(root);
        return maximum;
    }

    /**
     * 删除最大值递归
     *
     * @param node
     * @return
     */
    private Node<E> removeMax(Node<E> node) {
        if (node.right == null) {
            Node<E> left = node.left;
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
    private Node<E> minimum(Node<E> node) {
        if (node.left == null)
            return node;

        return minimum(node.left);
    }

    /**
     * 最大值
     *
     * @return
     */
    public E maximum() {
        if (size == 0)
            throw new IllegalArgumentException("tree is NULL");
        return maximum(root).e;
    }

    private Node<E> maximum(Node<E> node) {
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
            System.out.println(cur.e);
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
    private boolean contains(Node<E> node, E e) {
        if (node == null)
            return false;

        else if (e.compareTo(node.e) == 0)
            return true;
        else if (e.compareTo(node.e) < 0)
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
            System.out.println(pop.e);
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
    private void preOrder(Node<E> node) {
        if (node == null)
            return;
        System.out.println(node.e);
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

    private void generateBSTString(Node<E> node, int depth, StringBuilder res) {
        if (node == null) {
            res.append(depthString(depth) + "NULL\n");
            return;
        }
        res.append(depthString(depth) + node.e + "\n");
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
    private void inOrder(Node<E> node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.e);
        inOrder(node.right);
    }


    /**
     * 后续遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    private void postOrder(Node<E> node) {
        if (node == null)
            return;
        inOrder(node.left);
        inOrder(node.right);
        System.out.println(node.e);
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
