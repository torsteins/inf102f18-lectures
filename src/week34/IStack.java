package week34;

import week40.IBag;

/**
 * @author Torstein Strømme
 */
public abstract class IStack<E> implements IBag<E> {

    /**
     * Return the top element of the stack without removing it.
     * Will throw exception if no top element exists.
     * @return top element
     */
    abstract public E peek();

    /**
     * Return and remove the top element of the stack.
     * Will throw exception if no top element exists
     * @return top element
     */
    abstract public E pop();

    /**
     * Add an element to the stack.
     * @param item to be added
     */
    abstract public void push(E item);

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    @Override
    public void add(E element) {
        this.push(element);
    }
}
