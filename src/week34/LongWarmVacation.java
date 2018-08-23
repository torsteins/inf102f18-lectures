package week34;

/**
 * Three functions for solving the "long, warm vacation problem."
 *
 * Input: array of temperatures (weather forecast)
 * Question: how long is the longest consecutive stretch when every day
 * has has at least 20 degrees?
 *
 *
 * @author Torstein Strømme
 */
public class LongWarmVacation {


    public static void main(String[] args) {
        int[] shortArray = { 10, 19, 20, 22, 20, 9, 21};
        System.out.println(longWarmVacationC(shortArray));


        int[] bigArray = new int[100000000];
        for (int i = 0; i < bigArray.length; i++) {
            bigArray[i] = i;
        }

        long timeBefore = System.currentTimeMillis();
        System.out.println(longWarmVacationC(bigArray));
        long timeAfter = System.currentTimeMillis();
        System.out.printf("Took time %d ms\n", timeAfter-timeBefore);
    }

    /**
     * First attempt.
     *
     * Idea: Try every choice of startpoint and endpoint, then check whether this
     * stretch satisfies our criteria (everything in interval above 20 degrees).
     *
     * Worst case runtime occurs when every temperature is >= 20,
     * then the runtime is about (1/6)*n^3 "steps" (for some constant value of a step).
     *
     * @param arr
     * @return
     */
    public static int longWarmVacationA(int[] arr) {
        int n = arr.length;
        int best = 0;

        for (int start = 0; start < n; start++) {
            for (int end = start+1; end <= n; end++) {
                if (allAbove20(arr, start, end))
                    best = Math.max(best, end - start);
            }
        }
        return best;
    }

    public static boolean allAbove20(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            // Runtime analysis comment: At this point we have chosen three indices,
            // namely start, end, and i. Assume for a second that i is always distinct
            // from start. Then these form a distinct triplet. We will never pick the
            // same triplet twice, and we will visit all such triplets. There are
            // (n choose 3) ways of making this choice, which then becomes the
            // number of "steps" we take in our algorithm. In addition we sometimes
            // have i be equal to a, in exactly (n choose 2) cases. The total
            // number of steps is then (n choose 3) + (n choose 2) =
            // (n)(n-1)(n-2)/6 + (n)(n-1)/2 = (1/6)n^3 - (1/2)n^2 + n/3 + (1/2)n^2 - n/2 =
            // (1/6)n^3 - n/6.
            //
            // We say that the number of steps is ~(1/6)n^3. In big-O notation, that turns
            // out to be O(n^3)
            if (arr[i] < 20)
                return false;
        }
        return true;
    }

    /**
     * Second attempt.
     *
     * Idea: Try every choice of startpoint, and find the longest valid stretch
     * starting from there.
     *
     * Worst case runtime when every temperature is at least 20 degrees, then
     * this function runs in about (1/2)n^2 "steps" (for some constant value of a step)
     *
     * @param arr
     * @return
     */
    public static int longWarmVacationB(int[] arr) {
        int n = arr.length;
        int best = 0;

        for (int start = 0; start < n; start++) {
            best = Math.max(best, longestStartingAt(arr, start));
        }
        return best;
    }

    public static int longestStartingAt(int[] arr, int start) {
        int end = start;

        while (end < arr.length && arr[end] >= 20) {
            end++;
        }
        return end-start;
    }

    /**
     * Third attempt.
     *
     * Idea: Do one sweep through the array, and always remember the
     * previous time you saw a value of less than 20. When we encounter
     * a value less than 20, we update our memory. When we encounter a
     * value greater than or equal to 20, we know that the stretch between
     * here and the previous value below 20 is a valid stretch; if this
     * stretch is longest, we remember it.
     *
     * Runtime: n "steps" (for some constant value of a step)
     * @param arr
     * @return
     */
    public static int longWarmVacationC(int[] arr) {
        int n = arr.length;
        int best = 0;
        int prevLessThan20 = -1;

        for (int end = 0; end < n; end++) {
            if (arr[end] >= 20) {
                best = Math.max(best, end-prevLessThan20);
            }
            else {
                prevLessThan20 = end;
            }
        }
        return best;
    }

}
