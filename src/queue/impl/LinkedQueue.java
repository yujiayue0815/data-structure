package queue.impl;

import queue.Queue;

public class LinkedQueue<E> implements Queue<E> {

    private Node<E> head, tail;

    private int size = 0;


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


    @Override
    public int getSize() {
        return this.size;
    }

    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override
    public void enqueue(E e) {
        if (tail == null) {
            this.tail = new Node<>(e);
            this.head = this.tail;
        } else {
            tail.next = new Node<>(e);
            tail = tail.next;
        }
        size++;
    }

    @Override
    public E dequeue() {

        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");

        Node<E> res = this.head;
        this.head = this.head.next;
        res.next = null;
        if (head == null)
            tail = null;
        size--;
        return res.e;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("Queue is empty.");

        return head.e;
    }

    @Override
    public String toString() {

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Queue: front ");
        Node<E> cur = this.head;
        while (cur != null) {
            stringBuilder.append(cur + "-> ");
            cur = cur.next;
        }
        stringBuilder.append("NULL tail");
        return stringBuilder.toString();
    }
}
