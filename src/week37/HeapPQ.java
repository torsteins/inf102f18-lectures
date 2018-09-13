package week37;

/**
 * @author Torstein Strømme
 */
@SuppressWarnings("unchecked")
public class HeapPQ<E extends Comparable<E>> implements IPriorityQueue<E> {
    Comparable[] arr = new Comparable[2];
    int size = 0;


    @Override
    public void add(E key) {
        if (++this.size >= arr.length)
            resize(arr.length*2);

        this.arr[this.size] = key;
        swim(this.size);
    }

    private void swim(int k) {
        while (k > 1 && less(parent(k), k)) {
            swap(parent(k), k);
            k = parent(k);
        }
    }

    private int parent(int k) {
        return k / 2;
    }

    private void resize(int newsize) {
        Comparable[] newarr = new Comparable[newsize];
        for (int i = 0; i < this.size; i++) {
            newarr[i] = arr[i];
        }
        this.arr = newarr;
    }

    @Override
    public E peek() {
        return (E) this.arr[1];
    }

    @Override
    public E poll() {
        E result = (E) this.arr[1];
        swap(1, this.size);
        this.arr[this.size--] = null;
        sink(1);
        if (this.size < this.arr.length / 4) resize(this.arr.length / 2);
        return result;
    }

    private void sink(int k) {
        int child_a = fChild(k);
        int child_b = child_a + 1;

        // To avoid indexoutofbounds
        if (child_a > this.size)
            return;
        if (child_b > this.size) {
            if (less(k, child_a)) {
                swap(k, child_a);
            }
            return;
        }


        if (less(child_b, child_a) && less(k, child_a)) {
            swap(k, child_a);
            sink(child_a);
            return;
        }
        else if (less(child_b, child_a) && less(k, child_b)) {
            swap(k, child_b);
            sink(child_b);
            return;
        }
        else {
            return;
        }


    }

    private int fChild(int k) {
        return k * 2;
    }

    @Override
    public int size() {
        return this.size;
    }


    private boolean less(int i, int j) {
        return arr[i].compareTo(arr[j]) < 0;
    }

    private void swap(int i, int j) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
