package week37;

/**
 * @author Torstein Strømme
 */
public interface IIndexPQ<E extends Comparable<E>> {


    /**
     * Insert a key into the priority queue, associate it with the index
     *
     * @param index index with which to associate the key
     * @param key key to be inserted
     */
    void add(int index, E key);


    /**
     * Update the key associated with the given index.
     *
     * @param index index to be updated (must exist)
     * @param key new key to associate with index
     */
    void changeKey(int index, E key);


    /**
     * Check whether the given index is in use in the
     * priority queue.
     *
     * @param index index to be checked
     * @return true if a key is associated with the index, false otherwise
     */
    boolean containsIndex(int index);


    /**
     * Delete the index and associated key from the priority queue
     *
     * @param index index to be deleted
     */
    void delete(int index);


    /**
     * Return the key associated with the index
     *
     * @param index index to look up
     * @return element at index
     */
    E getKey(int index);


    /**
     * Return the largest key in the priority queue.
     * Returns null if none exists. If more than one
     * key has the maximum value, no guarantee is
     * given as to which of them is returned
     *
     * @return the index associated to a largest key
     */
    E peekKey();

    /**
     * Return the index associated with the largest key
     * in the priority queue.
     * Returns -1 if none exists. If more than one
     * key has the maximum value, no guarantee is
     * given as to which of those indices is returned
     *
     * @return the index associated to a largest key
     */
    int peek();


    /**
     * Return the index associated with the largest key
     * in the priority queue, and remove from priority queue.
     * Returns -1 if none exists. If more than one
     * key has the maximum value, no guarantee is
     * given as to which of those indices is returned
     *
     * @return the index associated to a largest key
     */
    int poll();


    /**
     * Returns the number of elements in this priority queue.
     *
     * @return the size
     */
    int size();

}
