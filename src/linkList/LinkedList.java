package linkList;

public class LinkedList<E> {

    private class Node<E> {
        public E e;

        public Node<E> next;

        public Node(E e, Node<E> next) {
            this.e = e;
            this.next = next;
        }

        public Node(E e) {
            this.e = e;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return e.toString();
        }
    }

    private Node<E> dummyHead;

    private int size;

    public LinkedList() {
        this.dummyHead = new Node<>(null, null);
        this.size = 0;
    }

    public Node<E> getHead() {
        return dummyHead.next;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int getSize() {
        return size;
    }

    /**
     * 从头部添加元素
     *
     * @param e
     */
    public void addHead(E e) {
        add(0, e);
    }


    /**
     * 在指定下标位置添加元素
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Add failed. Illegal index.");

        Node prev = dummyHead;
        for (int i = 0; i < index; i++)
            prev = prev.next;

        prev.next = new Node(e, prev.next);
        size++;
    }

    /**
     * 链表末尾添加元素
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 获取指定位置的元素
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get Field. Index error");

        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur.e;
    }

    /**
     * 获取第一个元素
     *
     * @return
     */
    public E getFrist() {
        return get(0);
    }

    /**
     * 获取最后一个元素
     *
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }


    /**
     * 更新链表中的数组元素
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Set field .Illegal index.");

        Node<E> cur = dummyHead.next;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }

        cur.e = e;
    }


    /**
     * 判断是否包含元素
     *
     * @param e
     * @return
     */
    public boolean contains(E e) {
        Node<E> cur = dummyHead.next;

        while (cur != null) {
            if (cur.equals(e)) {
                return true;
            } else {
                cur = cur.next;
            }
        }
        return false;
    }

    /**
     * 删除指定下标元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {

        if (index < 0 || index >= size) {
            throw new IllegalArgumentException("Remove field. Index is illegal.");
        }

        Node<E> pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node<E> res = pre.next;//删除的节点

        pre.next = res.next;
        res.next = null;

        size--;
        return res.e;

    }

    /**
     * 删除元素
     *
     * @param e
     */
    public void removeEelment(E e) {

        if (dummyHead.next == null)
            return;

        Node<E> next = dummyHead.next;
        while (next != null) {
            if (next.e.equals(e)) {
                Node<E> tmp = next.next;
                next.next = null;
                next = tmp;
                return;

            } else
                next = next.next;
        }
    }

    /**
     * 删除头节点
     *
     * @return
     */
    public E removeHead() {
        return remove(0);
    }


    /**
     * 删除尾节点
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }

    @Override
    public String toString() {

        StringBuilder res = new StringBuilder();
        Node<E> cur = dummyHead.next;
        while (cur != null) {
            res.append(cur + " -> ");
            cur = cur.next;
        }

        res.append("null");
        return res.toString();
    }
}
