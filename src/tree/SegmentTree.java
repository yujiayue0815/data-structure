package tree;

import java.util.Arrays;
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
        bulidSegment(leftIndex, l, mid);
        //创建右子树
        bulidSegment(rightIndex, mid + 1, r);

        tree[treeIndex] = merger.merger(tree[leftIndex], tree[rightIndex]);


    }

    /**
     * 查询区间值
     *
     * @param queryL 左边界
     * @param queryR 右边界
     * @return
     */
    public E query(int queryL, int queryR) {
        //合法性判断
        if (queryL > queryR || queryL < 0 || queryR > data.length)
            throw new IllegalArgumentException("Segment is exist");
        return query(0, 0, data.length - 1, queryL, queryR);
    }

    /**
     * 从指定位置开始经行查找，递归
     *
     * @param treeIndex 线段树下标
     * @param l         被查找区间开始点
     * @param r         被查找区间结束点
     * @param queryL    查找区间开始点
     * @param queryR    查找区间结束点
     * @return
     */
    private E query(int treeIndex, int l, int r, int queryL, int queryR) {
        if (l == queryL && r == queryR)
            return tree[treeIndex];

        int mid = l + (r - l) / 2;
        int leftChild = leftChild(treeIndex);
        int rightChild = rightChild(treeIndex);
        if (queryR <= mid)//左子树查找
            return query(leftChild, l, mid, queryL, queryR);
        else if (queryL >= mid + 1)//又子树查找
            return query(rightChild, mid + 1, r, queryL, queryR);
        else {//分段查找
            E leftResult = query(leftChild, l, mid, queryL, mid);
            E rightResult = query(rightChild, mid + 1, r, mid + 1, queryR);
            return merger.merger(leftResult, rightResult);
        }
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

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("[");
        for (int i = 0; i < tree.length; i++) {
            if (tree[i] != null)
                res.append(tree[i] + ",");
            else
                res.append("null,");
        }
        return res.toString();
    }
}
