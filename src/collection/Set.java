package collection;

public interface Set<E> {

    /**
     * 添加元素
     *
     * @param e
     */
    void add(E e);

    /**
     * is null
     *
     * @return
     */
    boolean isEmpty();

    /**
     * Get set size.
     *
     * @return
     */
    int getSize();


    /**
     * Set is contains element.
     *
     * @param e
     * @return
     */
    boolean contains(E e);

    /**
     * Set remove element e.
     *
     * @param e
     * @return
     */
    void remove(E e);
}
