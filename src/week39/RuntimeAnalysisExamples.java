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
        for (int i = 0; i < arr.length; i++) {
            total = total + arr[i];
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
        for (int i = n; i > 0; i = i / 3) {
            for (int j = 0; j < i; j++) {
                for (int k = 0; k < 100; k++) {
                    step();
                }
            }
        }
    }

    void functionBB(int n) {
        for (int i = 0; i < n; i++) {
            step();
            step();
        }
        for (int i = n; i > 0; i--) {
            step();
        }
        for (int i = n; i > 0; i = i / 2) {
            for (int j = 0; j*j < n; j++) {
                step();
            }
        }
    }



}
