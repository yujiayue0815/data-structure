package array;

import java.util.List;

public class Array<E> {

    private E[] data;
    private int size;


    /**
     * 指定容量
     *
     * @param capacity 容量
     */
    public Array(int capacity) {
        data = (E[]) new Object[capacity];
        size = 0;
    }


    public Array(E[] e) {
        data = (E[]) new Object[e.length];

        for (int i = 0; i < e.length; i++) {
            data[i] = e[i];
        }
        size = e.length;
    }

    /**
     * 默认初始容量10
     */
    public Array() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 添加数据
     *
     * @param e
     */
    public void addLast(E e) {
        add(size, e);
    }

    /**
     * 添加到头
     *
     * @param e
     */
    public void addFirst(E e) {
        add(0, e);
    }


    /**
     * 插入元素
     *
     * @param index
     * @param e
     */
    public void add(int index, E e) {


        if (index < 0 || index > size) {
            throw new IllegalArgumentException("下表异常");
        }

        if (size == data.length)
            reSize(2 * data.length);

        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];

        data[index] = e;
        size++;
    }

    /**
     * 扩容
     *
     * @param newCapacity
     */
    private void reSize(int newCapacity) {

        E[] newData = (E[]) new Object[newCapacity];
        for (int i = 0; i < size; i++) {
            newData[i] = data[i];
        }
        data = newData;
    }

    /**
     * 获取指定位置的数组
     *
     * @param index
     * @return
     */
    public E get(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("下标不存在");
        return data[index];
    }


    /**
     * 获取数组最后一个元素
     *
     * @return
     */
    public E getLast() {
        return get(size - 1);
    }


    /**
     * 获取数组第一个元素
     *
     * @return
     */
    public E getFirst() {
        return get(0);
    }

    /**
     * 修改指定位置数组的元素
     *
     * @param index
     * @param e
     */
    public void set(int index, E e) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("下标不存在");
        data[index] = e;
    }

    /**
     * 数组中是否包含某个元素
     *
     * @param e
     * @return
     */
    public Boolean contains(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return true;
        }
        return false;
    }

    /**
     * 返回索引
     *
     * @param e
     * @return
     */
    public int find(E e) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(e))
                return i;
        }
        return -1;
    }


    /**
     * 删除指定下标数据，并返回元素
     *
     * @param index
     * @return
     */
    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("下标不存在");
        E ret = data[index];
        for (int i = index - 1; i < size; i++) {
            data[i - 1] = data[i];
        }
        size--;
        if (size == data.length / 4 && data.length / 2 != 0)
            reSize(data.length / 2);
        return ret;
    }


    /**
     * 删除第一个
     *
     * @return
     */
    public E removeFirst() {
        return remove(0);
    }

    /**
     * 删除最后一个
     *
     * @return
     */
    public E removeLast() {
        return remove(size - 1);
    }


    /**
     * 删除单个元素
     *
     * @param e
     */
    public void removeElement(E e) {
        int index = find(e);
        if (index != -1)
            remove(index);
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append(String.format("Array: size = %d ,capacity = %d \n", size, data.length));
        res.append("[");
        for (int i = 0; i < size; i++) {
            res.append(data[i]);
            if (i != size - 1)
                res.append(",");
        }
        res.append("]");
        return res.toString();
    }


    /**
     * Change index i and j location.
     *
     * @param k
     * @param j
     */
    public void swap(int k, int j) {
        if (k < 0 || k > size || j < 0 || j > size)
            throw new IllegalArgumentException("index is error.");
        E e = data[k];
        data[k] = data[j];
        data[j] = e;
    }
}
