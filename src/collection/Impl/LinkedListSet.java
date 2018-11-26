package collection.Impl;

import collection.Set;
import linkList.LinkedList;

public class LinkedListSet<E> implements Set<E> {
    private LinkedList<E> list;

    @Override
    public void add(E e) {
        if (!list.contains(e)) {
            list.addHead(e);
        }
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public int getSize() {
        return list.getSize();
    }

    @Override
    public boolean contains(E e) {
        return list.contains(e);
    }

    @Override
    public void remove(E e) {
        list.removeEelment(e);
    }
}
