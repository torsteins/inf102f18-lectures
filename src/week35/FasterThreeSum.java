package week35;

import week34.ThreeSum;

import java.util.Arrays;
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
        testFastContains();

        testRuntime(ThreeSum::threeSum, 4000);
        testRuntime(FasterThreeSum::threeSum, 4000);
    }

    /**
     * Test correctness of the a threesum function
     */
    private static void testCorrectness(Function<int[], Boolean> threeSumFunction) {
        int[] isyes = {-5, 1, 10, -20, 4};
        verify(threeSumFunction.apply(isyes));

        int[] isno = {-5, 1, 10, -20, 5};
        verifyFalse(threeSumFunction.apply(isno));

        System.out.printf("Function %s passed correctness test\n", threeSumFunction.toString());
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
        int[] arr = {-5, 1, 10, -20, 4};
        sort(arr);
        verifyEquals(arr[0], -20);
        verifyEquals(arr[1], -5);
        verifyEquals(arr[2], 1);
        verifyEquals(arr[3], 4);
        verifyEquals(arr[4], 10);
        System.out.println("Sorting tested ok");
    }



    public static boolean threeSum(int[] arr) {
        sort(arr);  // O(n^2)

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                // We're here O(n^2)
                int target = - (arr[i] + arr[j]);
                if (fastContains(target, arr, j+1, arr.length)) {
                        // total runtime = O(n^2 * log n)
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


    private static void testFastContains() {
        int[] arr = {-20, -5, 1, 10, 15};
        verify(fastContains( -5, arr, 0, arr.length));
    }

    // O(log2 n)
    private static boolean fastContains(int key, int[] arr, int lowerBound, int upperBound) {
        while (lowerBound + 1 < upperBound) {
            int mid = (lowerBound + upperBound) / 2;
            if (arr[mid] > key) {
                upperBound = mid;
            }
            else {
                lowerBound = mid;
            }
        }
        return lowerBound < arr.length && arr[lowerBound] == key;
    }


    // O(n**2)
    private static void sort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            // Assume 0...i is sorted
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j-1]) {
                    int tmp = arr[j];
                    arr[j] = arr[j-1];
                    arr[j-1] = tmp;
                }
            }
        }
    }

}
