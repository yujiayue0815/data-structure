package queue;

public interface Queue<E> {

    /**
     * 队列大小
     *
     * @return
     */
    int getSize();

    /**
     * is empty
     *
     * @return
     */
    boolean isEmpty();

    /**
     * insert into the queue
     *
     * @param e
     */
    void enqueue(E e);

    /**
     * remove the queue
     *
     * @return
     */
    E dequeue();

    /**
     * select a E form to queue
     *
     * @return
     */
    E getFront();

}
