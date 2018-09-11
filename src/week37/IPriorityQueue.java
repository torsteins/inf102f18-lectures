package week37;

/**
 * @author Torstein Strømme
 */
public interface IPriorityQueue<E extends Comparable<E>> {

    /**
     * Insert a key into the priority queue
     *
     * @param key key to be inserted
     */
    void add(E key);


    /**
     * Return the largest key in the priority queue.
     * Returns null if none exists. If more than one
     * element has the maximum value, no guarantee is
     * given as to which of those elements is returned
     *
     * @return a largest key in the priority queue, or null if none exists
     */
    E peek();


    /**
     * Return and remove the largest key in the priority queue.
     * Returns null if none exists. If more than one
     * element has the maximum value, no guarantee is
     * given as to which of those elements is returned
     *
     * @return a largest key in the priority queue, or null if none exists
     */
    E poll();


    /**
     * Returns the number of elements in this priority queue.
     *
     * @return the size
     */
    int size();
}
