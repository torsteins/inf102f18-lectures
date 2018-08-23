package week34;

/**
 * @author Torstein Strømme
 */
public class MyFixedSizeStack<E> implements IStack<E> {
    private E[] data;
    private int n;

    public MyFixedSizeStack(int size) {
        this.data = (E[]) new Object[size];
        this.n = 0;
    }

    @Override
    public boolean empty() {
        return n == 0;
    }

    @Override
    public E peek() {
        return data[n-1];
    }

    @Override
    public E pop() {
        E item = data[--n];
        data[n] = null;
        return item;
    }

    @Override
    public void push(E item) {
        data[n++] = item;
    }
}
