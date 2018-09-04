package week36;

/**
 * @author Torstein Strømme
 */
public class Shell {

    /**
     * Sort the array using selection sort.
     *
     * @param arr the array to be sorted
     */
    public static void sort(Comparable[] arr) {

        int h = 1;
        while (h < arr.length) h = h*3 + 1;
        h /= 3;

        while (h >= 1) {

            for (int i = 0; i < arr.length; i++) {
                for (int j = i; j >= h; j -= h) {
                    if (less(j, j - h, arr))
                        swap(j, j - h, arr);
                    else
                        break;
                }
            }

            h /= 3;
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
