package week35;

import week34.ThreeSum;

import java.util.function.Function;

import static helpers.Verify.verify;
import static helpers.Verify.verifyEquals;
import static helpers.Verify.verifyFalse;

/**
 * @author Torstein Strømme
 */
@SuppressWarnings("SpellCheckingInspection")
public class FasterThreeSum {

    public static void main(String[] args) {
        testCorrectness(ThreeSum::threeSum);
        testCorrectness(FasterThreeSum::threeSum);
        testCorrectnessSort();
        testBinarySearch();

        testRuntime(ThreeSum::threeSum, 4000);
        testRuntime(FasterThreeSum::threeSum, 4000);
    }

    /**
     * Test correctness of the a threesum function
     */
    private static void testCorrectness(Function<int[], Boolean> threeSumFunction) {
        System.out.printf("Testing %s for correctness... ", threeSumFunction.toString());
        int[] isyes = {-5, 1, 10, -20, 4};
        verify(threeSumFunction.apply(isyes));

        int[] isno = {-5, 1, 10, -20, 5};
        verifyFalse(threeSumFunction.apply(isno));

        System.out.println("ok");
    }

    /**
     * Test runtime of our threesum
     */
    private static void testRuntime(Function<int[], Boolean> threeSumFunction, int n) {
        // Create big no-instance: let all values be odd (then sum of any three is odd)

        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                arr[i] = -i - 1;
            }
            else {
                arr[i] = i;
            }
        }

        long before = System.currentTimeMillis();
        threeSumFunction.apply(arr);
        long after = System.currentTimeMillis();

        System.out.printf("Function %s on input n=%d used %d ms\n", threeSumFunction.toString(), n, (after-before));
    }

    /**
     * Simple test for correctness of sort
     */
    private static void testCorrectnessSort() {
        System.out.printf("Testing sort... ");
        int[] arr = {-5, 1, 10, -20, 4};
        sort(arr);
        verifyEquals(arr[0], -20);
        verifyEquals(arr[1], -5);
        verifyEquals(arr[2], 1);
        verifyEquals(arr[3], 4);
        verifyEquals(arr[4], 10);
        System.out.println("ok");
    }

    /**
     * Test correctness of binary search
     */
    private static void testBinarySearch() {
        System.out.printf("Testing bSearch... ");
        int[] arr = {-20, -5, 1, 10, 15};
        verify(bSearch( -5, arr, 0, arr.length));
        System.out.println("ok");
        System.out.printf("Testing bSearchRec... ");
        verify(bSearchRec( -5, arr, 0, arr.length));
        System.out.println("ok");
    }




    /**
     * Check whether the input array contains three distinct entries that sum to 0.
     * Runtime of this version is O(n^2 * log n)
     *
     * @param arr the array to check
     * @return true if arr contains three distinct entries that sum to 0, false otherwise
     */
    public static boolean threeSum(int[] arr) {
        sort(arr);  // O(n^2)

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // We're here O(n^2)
                int target = - (arr[i] + arr[j]);
                if (bSearch(target, arr, j+1, arr.length)) {
                        // fastContains has runtime O(log n)  -->  total runtime = O(n^2 * log n)
                        return true;
                }
            }
        }
        return false;
    }

    private static boolean contains(int key, int[] arr, int lowerBound, int upperBound) {
        for (int i = lowerBound; i < upperBound; i++) {
            if (arr[i] == key) {
                return true;
            }
        }
        return false;
    }


    /**
     * Returns whether the array contains the key between lowebound and upperbound. The
     * array arr must be sorted, and lowerBound and upperBound must have reasonable
     * values (not out of range etc). The function implements an iterative (non-recursive)
     * binary search.
     *
     * Runtime of this function is O(log (upperBound - lowerBound)), and this distance is
     * at worst proportional to the length of array n. Hence, runtime is O(log n)
     *
     * @param key value to be searched for
     * @param arr array to search
     * @param lowerBound (inclusive)
     * @param upperBound (exclusive)
     * @return true if key is found, false otherwise
     */
    private static boolean bSearch(int key, int[] arr, int lowerBound, int upperBound) {
        if (lowerBound >= upperBound)
            return false;
        while (lowerBound + 1 < upperBound) {
            int mid = (lowerBound + upperBound) / 2;
            if (arr[mid] > key) {
                upperBound = mid;
            }
            else {
                lowerBound = mid;
            }
        }
        return arr[lowerBound] == key;
    }

    /**
     * Returns whether the array contains the key between lowebound and upperbound. The
     * array arr must be sorted, and lowerBound and upperBound must have reasonable
     * values (not out of range etc). The function implements a recursive binary search.
     *
     * Runtime of this function is O(log (upperBound - lowerBound)), and this distance is
     * at worst proportional to the length of array n. Hence, runtime is O(log n)
     *
     * @param key value to be searched for
     * @param arr array to search
     * @param lowerBound (inclusive)
     * @param upperBound (exclusive)
     * @return true if key is found, false otherwise
     */
    private static boolean bSearchRec(int key, int[] arr, int lowerBound, int upperBound) {
        if (lowerBound >= upperBound) {
            return false;
        }
        if (lowerBound + 1 == upperBound) {
            return arr[lowerBound] == key;
        }

        int mid = (lowerBound + upperBound) / 2;
        if (arr[mid] > key) {
            return bSearchRec(key, arr, lowerBound, mid);
        }
        else {
            return bSearchRec(key, arr, mid, upperBound);
        }
    }


    /**
     * A simple sorting algorithm, known as "stupid sort" or "gnome sort".
     * It is a variation of insertion sort, where the main idea is that
     * you do one sweep from left to right, maintaining the invariant that
     * everything left of your pivot is sorted.
     *
     * A value is inserted into the sorted area by swapping it leftwards
     * one at a time until it is in the sorted position.
     *
     * The runtime of this algorithm is O(n^2) or ~(1/2)n^2
     *
     * @param arr array to be sorted
     */
    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // Invariant: 0...i-1 is sorted

            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    // If the element at (j) is smaller than element
                    // to its left (j-1), we swap them.
                    int tmp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = tmp;
                }
            }

            // Invariant: 0...i is sorted
        }
    }

}
