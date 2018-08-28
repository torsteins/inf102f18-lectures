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
//        testCorrectness(FasterThreeSum::threeSum);
//        testCorrectnessSort();

        testRuntime(ThreeSum::threeSum, 4000);
//        testRuntime(FasterThreeSum::threeSum, 4000);
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
//    private static void testCorrectnessSort() {
//        int[] arr = {-5, 1, 10, -20, 4};
//        sort(arr);
//        verifyEquals(arr[0], -20);
//        verifyEquals(arr[1], -5);
//        verifyEquals(arr[2], 1);
//        verifyEquals(arr[3], 4);
//        verifyEquals(arr[4], 10);
//        System.out.println("Sorting tested ok");
//    }



    public static boolean threeSum(int[] arr) {

        return false;
    }

}
