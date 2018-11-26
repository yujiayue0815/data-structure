package tree;

/**
 * 合并流程
 *
 * @param <E>
 */
public interface Merger<E> {
    E merger(E e2, E e1);
}
