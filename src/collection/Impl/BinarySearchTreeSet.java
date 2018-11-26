package collection.Impl;

import collection.Set;
import tree.BinarySearchTree;

public class BinarySearchTreeSet<E extends Comparable<E>> implements Set<E> {

    private BinarySearchTree<E> bst;

    @Override
    public final void add(E e) {
        bst.add(e);
    }

    @Override
    public final boolean isEmpty() {
        return bst.isEmpty();
    }

    @Override
    public final int getSize() {
        return bst.getSize();
    }

    @Override
    public final boolean contains(E e) {
        return bst.contains(e);
    }

    @Override
    public final void remove(E e) {
        bst.remove(e);
    }
}
