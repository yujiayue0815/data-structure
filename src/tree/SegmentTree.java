package tree;

/**
 * 线段树
 */
public class SegmentTree<E> {
    private E[] data;
    private E[] tree;

    public SegmentTree(E[] arr) {
        this.data = (E[]) new Object[arr.length];
        for (int i = 0; i < arr.length; i++)
            data[i] = arr[i];
        this.tree = (E[]) new Object[4 * arr.length];
    }

    public int size() {
        return data.length;
    }

    public E get(int index) {
        if (index < 0 || index >= data.length)
            throw new IllegalArgumentException("Index is exist.");
        return data[index];
    }
}
