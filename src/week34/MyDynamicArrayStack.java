package week34;

/**
 * @author Torstein Strømme
 */
public class MyDynamicArrayStack<E> implements IStack<E> {
    private E[] data;
    private int n;

    public MyDynamicArrayStack() {
        this.data = (E[]) new Object[2];
        this.n = 0;
    }

    @Override
    public E pop() {
        E item = data[--n];
        data[n] = null;
        if (n < data.length/4) {
            this.resize(data.length/2);
        }
        return item;
    }

    @Override
    public void push(E item) {
        if (n == data.length) {
            this.resize(2*data.length);
        }
        data[n++] = item;
    }

    private void resize(int newsize) {
        E[] newdata = (E[]) new Object[newsize];
        for (int i = 0; i < n; i++) {
            newdata[i] = this.data[i];
        }
        this.data = newdata;
    }


    @Override
    public boolean empty() {
        return n == 0;
    }

    @Override
    public E peek() {
        return data[n-1];
    }
}
