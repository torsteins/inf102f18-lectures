package week36;

/**
 * @author Torstein Strømme
 */
public class Merge {

    /**
     * Sort the array using merge sort.
     *
     * @param arr the array to be sorted
     */
    public static void sort(Comparable[] arr) {
        Comparable[] aux = new Comparable[arr.length];
        sort(arr, aux, 0, arr.length);
    }

    private static void sort(Comparable[] arr, Comparable[] aux, int lb, int ub) {
        if (lb + 1 >= ub) return;

        sort(arr, aux, lb, (lb + ub) / 2);
        sort(arr, aux, (lb + ub) / 2, ub);

        merge(arr, aux, lb, (lb + ub) / 2, ub);
    }

    private static void merge(Comparable[] arr, Comparable[] aux, int lb, int mid, int ub) {
        int li = lb, ri = mid, ki = lb;

        // Copy everything to aux
        for (int i = lb; i < ub; i++) {
            aux[i] = arr[i];
        }

        // Do merge
        while (li < mid && ri < ub) {
            arr[ki++] = less(li, ri, aux) ? aux[li++] : aux[ri++];
        }

        // At this point, either left or right half is exhuasted, so
        // we will pad the remaining values from the other side at
        // the end
        while (li < mid) {
            arr[ki++] = aux[li++];
        }
        while (ri < ub) {
            arr[ki++] =  aux[ri++];
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
