package week36;

/**
 * @author Torstein Strømme
 */
public class Selection {

    /**
     * Sort the array using selection sort.
     *
     * @param arr the array to be sorted
     */
    public static void sort(Comparable[] arr) {

        for (int i = 0; i < arr.length; i++) {
            // Invariant: Everything to left of i is sorted, and smaller than
            // or equal to everything on the right. We find smallest element
            // to the right of index, and swap it into this location.
            int minj = i;
            for (int j = i; j < arr.length; j++) {
                if (less(j, minj, arr)) {
                    minj = j;
                }
            }
            swap(i, minj, arr);
        }
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
