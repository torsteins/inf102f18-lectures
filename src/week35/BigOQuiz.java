package week35;
import java.util.Random;

/**
 * @author Torstein Strømme
 */
@SuppressWarnings({"SameParameterValue", "WeakerAccess"})
public class BigOQuiz {
    int count = 0;

    void step() {
        count++;
    }

    int tally() {
        int res = count;
        count = 0;
        return res;
    }

    public static void main(String[] args) {
        // Test out whether your function makes sense.
        BigOQuiz q = new BigOQuiz();

        q.functionA(10);
        System.out.printf("A %d\n", q.tally());

        q.functionB(10);
        System.out.printf("B %d\n", q.tally());
    }


    void functionA(int n) {
        step();
        for (int i = 0; i < n; i++) {
            step();
        }
    }

    void functionB(int n) {
        for (int i = 0; i < n; i++) {
            step();
            step();
        }
    }

    void functionC(int n) {
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                step();
            }
        }
    }

    void functionD(int n) {
        step();
        step();
        for (int i = 0; i < n; i++) {
            step();
            for (int j = i+1; j < n; j++) {
                step();
            }
        }
    }

    void functionE(int n) {
        if (n > 3)
            step();
        else
            step();
    }


    void functionF(int n) {
        for (int i = 0; i < n; i += 2) {
            step();
        }
    }


    void functionG(int n) {
        step();
        step();
        if (n > 0)
            functionG(n-1);
    }


    void functionH(int n) {
        step();
        if (n > 0) {
            functionH(n/2);
        }
    }


    void functionI(int n) {
        for (int i = 0; i < n; i++) {
            step();
        }

        if (n > 0) {
            functionI(n/2);
        }
    }

    void functionJ(int n) {
        for (int i = 0; i < n; i++) {
            step();
        }

        if (n > 0) {
            functionJ(n/2);
            functionJ(n/2);
        }
    }

    void functionK(int n) {
        for (int i = 0; i*i < n; i++) {
            step();
        }
    }

    void functionL(int n) {
        for (int i = 1; i <= n; i *= 2) {
            step();
        }
    }

    void functionM(int n) {
        step();
        for (int i = 0; i < n; i++) {
            functionM(n-1);
        }
    }

    void functionN(int n) {
        Random random = new Random();
        int lo = 0;
        int hi = n;

        while (hi - lo > 1) {
            step();
            int mid = (lo + hi) / 2;

            if (random.nextBoolean()) {
                hi = mid;
            }
            else {
                lo = mid;
            }
        }
    }

    void functionO(int n) {
        step();
        if (n > 0) {
            functionO(n-1);
            functionO(n-1);
            functionO(n-1);
        }
    }

    void functionP(int n) {
        for (int i = 0; i < n; i++) {
            step();
            for (int j = 1; j <= n; j *= 2) {
                step();
            }
        }
    }

    void functionQ(int n) {
        for (int i = 0; i < n; i++) {
            while (n > 0) {
                step();
                n = n / 2;
            }
        }
    }

    void functionR(int n) {
        step();
        for (int i = 0; i * i < n; i++) {
            step();
            step();
        }

        for (int i = 0; i <n; i++) {
            step();
        }
    }

    void functionS(int n) {
        int i = n * n * n;
        while (i > 0) {
            step();
            for (int j = 0; j * j < n; j++) {
                step();
                i--;
            }
        }
    }

    void functionT(int n) {
        for (int i = 0; i * i < n; i++) {
            for (int j = i; j < n * n; j++) {
                step();
                step();
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                step();
            }

            int k = 0;
            while (k < i) {
                step();
                k = (k+1)*2;
            }
        }
    }

    void functionU(int n) {
        step();
        for (int i = 0; i < Math.log(n); i++) {
            step();
        }

        if (n > 0) {
            functionU(n / 2);
        }
    }

    void functionV(int n) {
        step();
        for (int i = n; i > 0; i = i / 2) {
            for (int j = 0; j < i; j++) {
                step();
            }
        }
    }

    void functionW(int n) {
        for (int i = 0; i < n; i++) {
            step();
        }
        for (int i = n; i > 0; i--) {
            step();
        }
        for (int i = n; i > 0; i = i / 2) {
            step();
        }
    }

    void functionX(int n) {
        int len = 0;
        while (n > 0) {
            step();
            len++;
            n = n / 2;
        }

        int i = 1;
        for (int j = 0; j < len; j++) {
            step();
            i = i * 2;
        }

        for (int j = 0; j < i; j++) {
            step();
        }
    }

    boolean functionY(int n) {
        step();
        if (n % 2 == 0)
            return false;
        for (int i = 3; i*i <= n; i += 2) {
            step();
            if (n % i == 0)
                return false;
        }
        return true;
    }

    // Challenge! Hint: Make reasonable simplifications,
    // and devise an (over-) estimate on how many steps.
    // You might need to do some research
    // on the internet (e. g. Wolfram Alpha).
    boolean[] functionZ(int n) {
        boolean[] p = new boolean[n + 1];
        for (int i = 2; i <= n; i++) {
            step();
            p[i] = true;
        }

        for (int i = 2; i <= n; i++) {
            step();
            if (p[i]) {
                for (int j = i * i; j <= n; j = j + i) {
                    step();
                    p[j] = false;
                }
            }
        }
        return p;
    }

}
