package week44;

import helpers.Kattio;


/**
 * @author Torstein Strømme
 */
public class APSP {

    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);
        while (true) {
            int n = io.getInt();
            int m = io.getInt();
            int q = io.getInt();
            if (n == 0) break;
            apsp(n, m, q, io);
            io.print("\n");
        }
        io.close();
    }

    private static void apsp(int n, int m, int q, Kattio io) {
        // Build adjacency matrix M1 with at most 0 on diagonal
        final long INF = Long.MAX_VALUE / 2;
        long[][] M = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                M[i][j] = INF;
            }
        }

        for (int i = 0; i < n; i++) {
            M[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            int from = io.getInt();
            int to = io.getInt();
            long weight = io.getInt();

            M[from][to] = Math.min(M[from][to], weight);
        }

        // Jump to M2 -> M4 -> M8 -> M16 ... Mn
        for (int i = 0; i < Math.ceil(Math.log(n) / Math.log(2)); i++) {
            for (int s = 0; s < n; s++) {
                for (int t = 0; t < n; t++) {
                    for (int k = 0; k < n; k++) {
                        if (M[s][k] < INF && M[k][t] < INF) {
                            M[s][t] = Math.min(M[s][t], M[s][k] + M[k][t]);
                        }
                    }
                }
            }
        }

        // Fill with -INF for paths with neg. cycles
        for (int k = 0; k < n; k++) {
            if (M[k][k] < 0) {
                M[k][k] = -INF;
                for (int s = 0; s < n; s++) {
                    for (int t = 0; t < n; t++) {
                        if (M[s][k] < INF && M[k][t] < INF) {
                            M[s][t] = -INF;
                        }
                    }
                }
            }
        }

        // Answer queries
        for (int i = 0; i < q; i++) {
            int s = io.getInt();
            int t = io.getInt();

            if (M[s][t] >= INF) {
                io.print("Impossible\n");
            }
            else if (M[s][t] <= -INF) {
                io.print("-Infinity\n");
            }
            else {
                io.print(M[s][t]);
                io.print("\n");
            }
        }

    }
}
