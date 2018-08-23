package week34;

import static helpers.Verify.*;

/**
 * @author Torstein Strømme
 */
public class ThreeSum {

    public static void main(String[] args) {
        // Small verification procedure
        int[] arr = {3, -1, 4, 5, -100, 30};
        verifyFalse(threeSum(arr));
    }

    public static boolean threeSum(int[] values) {
        for (int i = 0; i < values.length; i++) {
            for (int j = i + 1; j < values.length; j++) {
                for (int k = j + 1; k < values.length; k++) {
                    if (values[i] + values[j] + values[k] == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
