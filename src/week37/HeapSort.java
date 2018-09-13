package week37;

/**
 * @author Torstein Strømme
 */
public class HeapSort {

    public static void sort(Comparable[] arr) {

        // Step 1: heapify the array
        for (int i = arr.length / 2; i >= 0; i--) {
            sink(i, arr.length, arr);
        }

        // Step 2: sort down
        int size = arr.length;
        for (int i = 0; i < arr.length; i++) {
            swap(0, --size, arr);
            sink(0, size, arr);
        }
    }

    private static void sink(int k, int size, Comparable[] arr) {
        int child = firstChild(k);

        while (child < size) {

            // Select the stronger child
            if (child + 1 < size && less(child, child+1, arr))
                child++;

            // Swap if needed; continue sink process if swap happened
            if (less(k, child, arr)) {
                swap(k, child, arr);
                k = child;
                child = firstChild(k);
            }
            else
                break;
        }

    }

    private static int firstChild(int k) {
        return (k + 1) * 2 - 1;
    }

    private static boolean less(int i, int j, Comparable[] arr) {
        // A usual way to measure the complexity of a sorting
        // algorithm is to count how many calls are made to this
        // function
        return arr[i].compareTo(arr[j]) < 0;
    }

    private static void swap(int i, int j, Comparable[] arr) {
        // Another (less common) way to measure complexity of a sorting algorithm
        // is to count the number of calls to this function. That's when
        // selection sort really shines.
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }


}
