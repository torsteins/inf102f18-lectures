package week36;

import java.util.Arrays;

/**
 * @author Torstein Strømme
 */
public class Insertion {

    /**
     * Sort the array using insertion sort.
     *
     * @param arr the array to be sorted
     */
    public static void sort(Comparable[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // Invariant: everything to my left is sorted
            // Want to insert arr[i] in correct spot
            for (int j = i; j > 0; j--) {
                if (less(j, j - 1, arr)) {
                    swap(j, j - 1, arr);
                }
                else break;
            }
        }
    }


    private static boolean less(int i, int j, Comparable[] arr) {
        return arr[i].compareTo(arr[j]) < 0;
    }

    private static void swap(int i, int j, Comparable[] arr) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

}
