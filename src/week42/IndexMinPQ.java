package week42;

import java.util.NoSuchElementException;

/**
 * @author Torstein Strømme
 */
public class IndexMinPQ<E extends Comparable<E>> implements IIndexPQ<E> {
    private final int root = 1;

    private int n = 0;
    private int maxSize;
    private E[] keys;
    private int[] pq; // The heap
    private int[] qp; // Inverse of the heap:  qp[pq[i]] = i = pq[qp[i]]
    /*
    Note: assume pq is 1-indexed, and qp is 0-indexed. We don't use pq[0].
    qp[i] == 0 means that i is not in use, and hence not on the heap.
    */

    /**
     * Create a new IndexMinPQ capable of holding indices from 0 to maxSize-1.
     * @param maxSize max number of indices this IndexMinPQ can accommodate
     */
    public IndexMinPQ(int maxSize) {
        this.maxSize = maxSize;
        this.keys = (E[]) new Comparable[maxSize];
        this.pq = new int[maxSize+1];
        this.qp = new int[maxSize];
    }

    /*
     *  The tricky public methods follow here
     */

    @Override
    public void add(int index, E key) {
        if (this.contains(index)) {
            throw new IllegalStateException("Index " + index + " already exists");
        }
        else if (key == null) {
            throw new NullPointerException("Key can't be null");
        }
        this.pq[++this.n] = index;
        this.qp[index] = this.n;
        this.keys[index] = key;
        swim(this.n);
    }

    @Override
    public void delete(int index) {
        if (!this.contains(index)) {
            throw new NoSuchElementException("Index " + index + " does not exists");
        }

        // Swap the element to delete with the last element
        int orgPos = this.qp[index];
        swap(orgPos, this.n);

        // Delete the element to delete (which is now last)
        this.qp[index] = 0;
        this.keys[index] = null;
        this.pq[this.n--] = -1; // Updating pq is for troubleshooting only; the important part is to decrement n

        // Swim and sink the displaced element
        if (orgPos <= this.n) {
            sink(orgPos);
            swim(orgPos);
        }
    }

    /*
     *  The tricky private methods follows here
     */

    private void swim(int k) {
        while (k > this.root && higherPriority(k, parent(k))) {
            swap(k, parent(k));
            k = parent(k);
        }
    }

    private void sink(int k) {
        int child = firstChild(k);

        while (child <= this.n) {

            // Select the highest priority child
            if (child + 1 <= this.n && higherPriority(child + 1, child)) {
                child++;
            }

            // Swap if needed; continue sink process if swap happened
            if (higherPriority(child, k)) {
                swap(child, k);
                k = child;
                child = firstChild(k);
            }
            else {
                break;
            }
        }
    }

    /**
     * Compare two elements on the heap
     *
     * @param a position on the heap of the first item
     * @param b position on the heap for the second item
     * @return true if item in pos i have strictly higher priority than item in pos j, false otherwise
     */
    private boolean higherPriority(int a, int b) {
        if (a < this.root || b < this.root || a > this.n || b > this.n)
            throw new IndexOutOfBoundsException();
        return this.keys[this.pq[a]].compareTo(this.keys[this.pq[b]]) < 0;
    }

    /**
     * Swap two elements on the heap
     *
     * @param a position in heap of first item
     * @param b position in heap of second item
     */
    private void swap(int a, int b) {
        if (a < this.root || b < this.root || a > this.n || b > this.n)
            throw new IndexOutOfBoundsException();
        int idx_a = this.pq[a];
        int idx_b = this.pq[b];

        this.pq[a] = idx_b;
        this.pq[b] = idx_a;

        this.qp[idx_a] = b;
        this.qp[idx_b] = a;
    }

    /*
     *   The easy public methods follows here
     */

    @Override
    public boolean contains(int index) {
        if (index < 0 || index >= this.maxSize)
            throw new IndexOutOfBoundsException("Index "+index+" is out of bounds " +
                    "(valid range is 0..."+(this.maxSize-1)+")");
        return this.qp[index] > 0;
    }

    @Override
    public void changeKey(int index, E key) {
        if (!this.contains(index)) {
            throw new NoSuchElementException("Index " + index + " does not exists");
        }
        else if (key == null) {
            throw new NullPointerException("Key can not be null");
        }

        this.delete(index);
        this.add(index, key);

        /*
        Note: One could also directly update the key, and then call sink and swim; that would indeed
        speed it up quite significantly. However, asymptotically (in big-O-notation), this approach
        provides the same worst-case guarantee; it is also much easier to code, and hence makes the
        code easier to maintain and troubleshoot. In the real world, those are crucial properties of
        the code, often more important than small constant-factor improvements (depending on the
        circumstances - in a data structure library, perhaps not).
        */
    }

    @Override
    public E getKey(int index) {
        if (!this.contains(index)) {
            throw new NoSuchElementException("Index " + index + " does not exists");
        }
        return this.keys[index];
    }

    @Override
    public E peekKey() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        return this.keys[this.pq[1]];
    }

    @Override
    public int peek() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        return this.pq[1];
    }

    @Override
    public int poll() {
        if (this.isEmpty()) {
            throw new NoSuchElementException("PQ is empty");
        }
        int res = this.pq[1];
        delete(res);
        return res;
    }

    @Override
    public int size() {
        return this.n;
    }

    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /*
     *  The easy private methods follows here
     */

    private static int firstChild(int k) { return k * 2; }
    private int parent(int k) { return k / 2; }

}
