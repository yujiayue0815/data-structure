package stack.impl;

import linkList.LinkedList;
import stack.Stack;

public class LinkedStack<E> implements Stack<E> {
    private LinkedList<E> list;

    public LinkedStack() {
        list = new LinkedList<>();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void push(E e) {
        list.addHead(e);
    }

    @Override
    public E pop() {
        return list.removeHead();
    }

    @Override
    public E peek() {
        return list.getFrist();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Stack :top ");
        stringBuilder.append(list);
        return stringBuilder.toString();
    }
}
