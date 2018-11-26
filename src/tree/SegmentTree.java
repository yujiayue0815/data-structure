package tree;

import java.util.function.IntBinaryOperator;

/**
 * 线段树
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;
    private Merger<E> merger;

    public SegmentTree(E[] arr, Merger<E> merger) {
        this.merger = merger;
        this.data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];
        this.tree = (E[]) new Object[4 * arr.length];

        bulidSegment(0, 0, data.length - 1);
    }

    /**
     * 创建线段树
     *
     * @param treeIndex
     * @param l
     * @param r
     */
    private void bulidSegment(int treeIndex, int l, int r) {
        if (l == r) {
            tree[treeIndex] = data[l];
            return;
        }

        int leftIndex = leftChild(treeIndex);
        int rightIndex = rightChild(treeIndex);

        //构建子树
        int mid = l + (r - l) / 2;

        //构建左子树
        bulidSegment(leftIndex, leftIndex, mid);
        //创建右子树
        bulidSegment(rightIndex, mid + 1, r);

        tree[treeIndex] =merger.merger(tree[leftIndex],tree[rightIndex]);


    }

    public int size() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is exist.");
        return data[index];
    }

    public int leftChild(int index) {
        return 2 * index + 1;
    }

    public int rightChild(int index) {
        return 2 * index + 2;
    }

}
