package week34;

/**
 * @author Torstein Strømme
 */
public interface IStack<E> {

    /**
     * Tests if the stack is empty.
     * @return true if empty, false otherwise
     */
    public boolean empty();

    /**
     * Return the top element of the stack without removing it.
     * Will throw exception if no top element exists.
     * @return top element
     */
    public E peek();

    /**
     * Return and remove the top element of the stack.
     * Will throw exception if no top element exists
     * @return top element
     */
    public E pop();

    /**
     * Add an element to the stack.
     * @param item
     */
    public void push(E item);

    /**
     * Return the i'th element on the stack (from the top) without removal.
     * 0-indexed, e.g. index 0 gives the top element of the stack.
     * If there are less than i-1 elements, it will throw an exception.
     * @param index
     * @return the elment at the index
     */
//    public E peek(int index);
}
