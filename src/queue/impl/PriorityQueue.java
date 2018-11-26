package queue.impl;

import heap.MaxHeap;
import queue.Queue;

/**
 * 优先队列，采用大根堆实现方式
 *
 * @param <E>
 */
public class PriorityQueue<E extends Comparable<E>> implements Queue<E> {


    private MaxHeap<E> data;

    public PriorityQueue() {
        data = new MaxHeap<>();
    }

    @Override
    public int getSize() {
        return data.getSize();
    }

    @Override
    public boolean isEmpty() {
        return data.isEmpty();
    }

    @Override
    public void enqueue(E e) {
        data.add(e);
    }

    @Override
    public E dequeue() {
        return data.extractMax();
    }

    @Override
    public E getFront() {
        return data.getMax();
    }
}
