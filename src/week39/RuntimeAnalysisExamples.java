package week39;

/**
 * @author Torstein Strømme
 */
public class RuntimeAnalysisExamples {


    int sum(int a, int b) {
        return a + b;
    }


    int sum(int[] arr) {
        int total = 0;

        int i = 0;
        while (true) {
            if (i >= arr.length) break;
            int num = arr[i];
            total = total + num;
            i = i + 1;
            // Here is the actual jump
        }
        return total;
    }




    int count = 0;
    void step() {
        count++;
    }

    void functionAA(int n) {
        step();
        step();

        // Outer loop goes log_3 n times, with values n, n/3, n/(3^2), n/(3^3) ... 1 (all interger division)
        for (int i = n; i > 0; i = i / 3) {
            // Inner loop goes i times
            for (int j = 0; j < i; j++) {
                step();
            }
        }
        // Exact runtime: Sum from k=0 to floor(log_3 n) of floor(n/(3^k))
        // tilde runtime (by plugging into Wolfram alpha without the floors) ~3n/2
        // Big-O runtime: O(n)
    }




}
