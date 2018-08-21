package week34;

/**
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


    // Runs (about)  n^2 / 2 times
    public static boolean allAbove20(int[] arr, int start, int end) {
        for (int i = start; i < end; i++) {
            // How many times here? n/4 (per two outer loops)
            if (arr[i] < 20)
                return false;
        }
        return true;
    }

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
