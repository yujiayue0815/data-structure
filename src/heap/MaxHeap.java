package heap;

import array.Array;

/**
 * This class is max heap demo.
 *
 * @param <E>
 */
public class MaxHeap<E extends Comparable<E>> {

    private Array<E> data;

    public MaxHeap() {
        data = new Array<>();
    }

    /**
     * 将数组整理成一个大根堆 heapify
     *
     * @param e
     */
    public MaxHeap(E[] e) {
        data = new Array<>(e);
        for (int i = parent(data.getSize() - 1); i >= 0; i--) {
            siftDown(i);
        }
    }

    /**
     * Set array's capacity.
     *
     * @param capacity
     */
    public MaxHeap(int capacity) {
        data = new Array<>(capacity);
    }

    /**
     * Get array's size.
     *
     * @return
     */
    public int getSize() {
        return data.getSize();
    }

    /**
     * Judge array is empty.
     *
     * @return
     */
    public boolean isEmpty() {
        return getSize() == 0;
    }

    /**
     * Get index's element to parent.
     *
     * @param index
     * @return
     */
    private int parent(int index) {
        if (index <= 0)
            throw new IllegalArgumentException("Index 0 is not parent element.");
        return (index - 1) / 2;
    }

    /**
     * Get index's element to left child.
     *
     * @param index
     * @return
     */
    private int leftChild(int index) {
        return index * 2 + 1;
    }

    /**
     * Get index's element to right child.
     *
     * @param index
     * @return
     */
    private int rightChild(int index) {
        return (index + 1) * 2;
    }

    /**
     * Add a new element to array.
     *
     * @param e
     */
    public void add(E e) {
        data.addLast(e);
        siftUp(data.getSize() - 1);
    }

    /**
     * Add new element sift up.
     *
     * @param k
     */
    private void siftUp(int k) {
        while (k > 0 && data.get(parent(k)).compareTo(data.get(k)) < 0) {
            data.swap(k, parent(k));
            k = parent(k);
        }
    }

    /**
     * Get heap max value.
     *
     * @return
     */
    public E getMax() {
        if (isEmpty())
            throw new IllegalArgumentException("Heap is empty.");
        return data.get(data.getSize() - 1);
    }

    /**
     * Remove heap max value.
     *
     * @return
     */
    public E extractMax() {
        E e = getMax();
        // Change heap first and last location.
        data.swap(0, data.getSize() - 1);
        //Remove last array.
        data.removeLast();
        siftDown(0);
        return e;
    }

    /**
     * Remove index sift down.
     *
     * @param k
     */
    private void siftDown(int k) {
        while (leftChild(k) < data.getSize()) {
            int index = leftChild(k);
            if (index + 1 < data.getSize() && data.get(index).compareTo(data.get(index + 1)) < 0)
                index = rightChild(k);
            // Index is data max.

            if (data.get(k).compareTo(data.get(index)) >= 0)
                break;
            data.swap(k, index);
            k = index;
        }
    }

}
