package week36;

import java.util.Arrays;
import java.util.Random;

import static helpers.Verify.verifyEquals;

/**
 * @author Torstein Strømme
 */
public class CompareSorts {

    public static void main(String[] args) {
        sanityTest("Insertion");
        doublingTest("Insertion");


        Integer[] arr = new Integer[] {5, 3, 4, 9, 1};
        sort(arr);
        verifyEquals(Arrays.toString(arr), "[1, 3, 4, 5, 9]");


        String[] arrs = new String[] {"abc", "yxz", "ijk"};
        sort(arrs);
        verifyEquals(Arrays.toString(arrs), "[abc, ijk, yxz]");


    }

    private static void sort(Comparable[] arrs) {
    }


    /**
     * Basic, boring sanity tests. Just check that it does the right thing
     * for some basic cases.
     *
     * @param alg the sorting algorithm to be sanity tested
     */
    private static void sanityTest(String alg) {
        System.out.printf("Sanity test %s... ", alg);

        // Check that Integer objects are sorted correctly
        Integer[] arri = new Integer[] {5, 3, 4, 9, 1};
        sort(alg, arri);
        verifyEquals(Arrays.toString(arri), "[1, 3, 4, 5, 9]");

        // Check that String objects are sorted correctly
        String[] arrs = new String[] {"abc", "yxz", "ijk"};
        sort(alg, arrs);
        verifyEquals(Arrays.toString(arrs), "[abc, ijk, yxz]");

        System.out.println("success!");
    }


    /**
     * Apply one of the sorting algorithms to the array of comparable items
     *
     * @param alg what sorting algorithm to use
     * @param a the array to be sorted
     */
    private static void sort(String alg, Comparable[] a) {
        switch (alg) {
            case "Insertion":
                Insertion.sort(a);
                break;
            case "Selection":
                Selection.sort(a);
                break;
            case "Merge":
                Merge.sort(a);
                break;
            case "Quick":
                Quick.sort(a);
                break;
            case "Shell":
                Shell.sort(a);
                break;
            default:
                throw new IllegalArgumentException(String.format("I don't know algorithm \"%s\"", alg));
        }
    }


    /**
     * Run doubling test on the given algorithm. Will double input size until time
     * required reaches >= 0.1 seconds, then run 20 trails on that input size.
     * For comparison, 20 runs are also done on half that input size, so an
     * approximate doubling factor is found.
     *
     * @param alg algorithm to test
     */
    private static void doublingTest(String alg) {
        System.out.printf("Doubling test %s... ", alg);
        final int maxTimeNs = 100*1000*1000; // 0.1 second
        final int trails = 20;

        // Find suitable n
        int n = 10;
        while (timedAlg(alg, n) < maxTimeNs) n *= 2;

        // Run trail runs on n and n/2
        long totTime = 0;
        long halfTime = 0;
        for (int i = 0; i < trails; i++) {
            totTime += timedAlg(alg, n);
            halfTime += timedAlg(alg, n / 2);
        }

        totTime /= trails;
        halfTime /= trails;


        System.out.printf("does n=%d in %.3f sec. Doubling factor: %.3f\n",
                n, ((double) totTime)/1000000000.0, ((double) totTime) / halfTime);
    }


    /**
     * Run the provided algorithm on array with n random Integers
     *
     * @param alg the algorithm to run on the data
     * @param n the size of the random data
     * @return the time elapsed (in nanoseconds)
     */
    private static long timedAlg(String alg, int n) {
        Random r = new Random();

        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = r.nextInt();
        }

        long before = System.nanoTime();
        sort(alg, arr);
        return System.nanoTime() - before;
    }

}
