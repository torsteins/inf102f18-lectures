package week35;

/**
 * @author Torstein Strømme
 */
public class UnionFind implements IUnionFind {
    private int[] id;

    /**
     * Create a union-find data structure with n singleton
     * (unconnected) items named 0, 1, 2, ... n-1
     *
     * @param n the number of items
     */
    public UnionFind(int n) {
        this.id = new int[n];
        for (int i = 0; i < n; i++) {
            this.id[i] = i;
        }
    }

    /**
     * Add a connection between item p and item q
     *
     * @param p the item
     * @param q the item
     */
    public void union(int p, int q) {
        int idp = this.id[p];
        int idq = this.id[q];
        for (int i = 0; i < this.id.length; i++) {
            if (this.id[i] == idp) {
                this.id[i] = idq;
            }
        }
    }

    /**
     * Find the "component identifier" of item p
     *
     * @param p the item
     * @return component identifier of item p
     */
    public int find(int p) {
        return this.id[p];
    }

    /**
     * Returns true if items p and q are connected either
     * directly or indirectly through some path.
     *
     * @param p an item
     * @param q an item
     * @return true if items p and q are connected, false otherwise
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

}
