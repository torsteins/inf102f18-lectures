package week40;

/**
 * @author Torstein Strømme
 */
public interface IBag<E> extends Iterable<E> {

    /**
     * Add the element to the bag
     * @param element to be added
     */
    void add(E element);

    /**
     * @return the number of items in the bag
     */
    int size();

    /**
     * Tests if the IBag is empty.
     * @return true if empty, false otherwise
     */
    boolean isEmpty();

}
