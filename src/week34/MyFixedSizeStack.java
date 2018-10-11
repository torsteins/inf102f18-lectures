package week34;

import java.util.Iterator;

/**
 * @author Torstein Strømme
 */
public class MyFixedSizeStack<E> extends IStack<E> {
    private E[] data;
    private int n;

    public MyFixedSizeStack(int size) {
        this.data = (E[]) new Object[size];
        this.n = 0;
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

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            int i = MyFixedSizeStack.this.n;

            @Override
            public boolean hasNext() {
                return this.i > 0;
            }

            @Override
            public E next() {
                return MyFixedSizeStack.this.data[--this.i];
            }
        };
    }

    @Override
    public int size() {
        return this.n;
    }
}
