package week36;

import java.util.SplittableRandom;

/**
 * Note: Code not thoroughly tested. This code also never actually showed up in lecture,
 * since I had to outsource the quickSort lecture to Erleng.
 *
 * @author Torstein Strømme
 */
@SuppressWarnings("unchecked")
public class Quick {
    private static final int CUTOFF = 8;

    /**
     * Sort the array using quick-sort.
     *
     * @param arr the array to be sorted
     */
    public static void sort(Comparable[] arr) {
        shuffle(arr);
        quickSort(arr, 0, arr.length);
//        quickSort3way(arr, 0, arr.length);
    }

    private static void quickSort(Comparable[] arr, int lb, int ub) {

        // Size of array is small -- use insertion sort
        // If cutoff is 0, then this basically just returns
        // without doing anything, like the standard base case
        if (lb + 1 + CUTOFF >= ub) {
            insertionSort(arr, lb, ub);
            return;
        }

        // Tiny optimization: Find the median of three
        // You may skip this part entirely - it makes it
        // more likely to chose a pivot closer to the
        // median.
        if (ub - lb > 3) {
            if (arr[lb].compareTo(arr[lb+1]) < 0 && arr[lb].compareTo(arr[lb+2]) < 0) {
                if (arr[lb + 1].compareTo(arr[lb + 2]) < 0)
                    swap(lb, lb + 1, arr);
                else
                    swap(lb, lb + 2, arr);
            }
            else if (arr[lb].compareTo(arr[lb+1]) > 0 && arr[lb].compareTo(arr[lb+2]) > 0) {
                if (arr[lb + 1].compareTo(arr[lb + 2]) > 0)
                    swap(lb, lb + 1, arr);
                else
                    swap(lb, lb + 2, arr);
            }
        }


        Comparable key = arr[lb];
        int i = lb, j = ub;

        // Mentally, divide array into three parts
        // At the END of every loop:
        //   * arr[lb] contains key element
        //   * arr[lb+1:i+1] values are less than or equal to key
        //   * arr[i+1:j] values are unchecked
        //   * arr[j:ub] values are at least key value or greater
        //   * j is always in range of the array, pointing to a
        //     value less than or equal to key value
        while (true) {
            while (++i < ub && arr[i].compareTo(key) < 0);
            while (arr[--j].compareTo(key) > 0); // && j > lb ); // check j > lb is redundant. (Why?)

            if (i >= j) break;
            swap(i, j, arr);
        }
        swap(lb, j, arr);

        quickSort(arr, lb, j);
        quickSort(arr, j+1, ub);
    }


    /**
     * A beefed-up sorting algorithm based on 3-way quick-sort.
     *  - Recursive implementation
     *  - Use a cutoff to insertion sort
     *  - Use Dijkstra's idea to overcome the "many equal keys" -problem.
     *
     * @param arr Array to be sorted in range between lowerbound and upperbound
     * @param lb lower bound (inclusive)
     * @param ub upper bound (exclusive)
     */
    private static void quickSort3way(Comparable[] arr, int lb, int ub) {

        // Size of array is small
        if (lb + 1 + CUTOFF >= ub) {
            insertionSort(arr, lb, ub);
            return;
        }

        Comparable key = arr[lb];
        int lt = lb, i = lb, gt = ub; // less than, equal to, greater than
        // We mentally divide the array into four areas:
        // arr[lb:lt] have values < key
        // arr[lt:i] have values == key
        // arr[i:gt] have unchecked values
        // arr[gt:ub] have values > key

        while (i < gt) {
            int cmp = arr[i].compareTo(key);
            if      (cmp < 0)  swap(lt++, i++, arr);
            else if (cmp > 0)  swap(--gt, i, arr);
            else               i++;
        }

        quickSort3way(arr, lb, lt);
        quickSort3way(arr, gt, ub);
    }

    /**
     * Simple shuffling procedure (Fisher-Yates/Durstenfeld/Knuth)
     * Resulting permutation is chosen uniformly at random
     *
     * @param arr array to be shuffled
     */
    private static void shuffle(Comparable[] arr) {
        SplittableRandom r = new SplittableRandom();
        for (int i = 0; i < arr.length; i++) {
            int j = r.nextInt(arr.length-i) + i;
            swap(i, j, arr);
        }
    }

    private static void swap(int i, int j, Comparable[] arr) {
        Comparable tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }

    public static void insertionSort(Comparable[] arr, int lb, int ub) {
        for (int i = lb; i < ub; i++) {
            // Invariant: everything to my left is sorted
            // Want to insert arr[i] in correct spot
            for (int j = i; j > lb; j--) {
                if (arr[j].compareTo(arr[j - 1]) < 0) {
                    swap(j, j - 1, arr);
                }
                else break;
            }
        }
    }

}
