package week40;

/**
 * @author Torstein Strømme
 */
public class AdjMtxGraph implements IGraph {
    int[][] mtx;
    int m = 0;
    int n = 0;

    AdjMtxGraph(int n) {
        mtx = new int[n][n];
        this.n = n;
    }


    @Override
    public void addEdge(int u, int v) {
        // Runtime O(1)
        this.mtx[u][v] = 1;
        this.mtx[v][u] = 1;
        m++;
    }

    @Override
    public int n() {
        return this.n;
    }

    @Override
    public int m() {
        return this.m;
    }

    @Override
    public boolean areAdj(int u, int v) {
        // Runtime O(1)
        return this.mtx[u][v] == 1;
    }

    @Override
    public IBag<Integer> adj(int u) {
        // Runtime O(n)
        SortableLinkedList<Integer> nbrs = new SortableLinkedList<>();
        for (int i = 0; i < this.n; i++) {
            if (this.mtx[u][i] == 1) {
                nbrs.add(i);
            }
        }
        return nbrs;
    }
}
