package collection;

public interface Map<K, V> {

    /**
     * Add a element to map.
     *
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     * Judge key is exist to map.
     *
     * @param key
     * @return
     */
    boolean contains(K key);

    /**
     * Remove is key and V type value element.
     *
     * @param key
     * @return
     */
    V remove(K key);

    /**
     * Get key is key's value.
     *
     * @param key
     * @return
     */
    V get(K key);

    /**
     * Judge map is none;
     *
     * @return
     */
    boolean isEmpty();

    /**
     * Get map's size.
     *
     * @return
     */
    int getSize();

    /**
     * Set key's key to value.
     *
     * @param key
     * @param value
     */
    void set(K key, V value);
}
