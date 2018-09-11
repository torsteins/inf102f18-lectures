package week35;

/**
 * @author Torstein Strømme
 */
public class UnionFind implements IUnionFind {
    private int[] id;
    private int[] size;

    /**
     * Create a union-find data structure with n singleton
     * (unconnected) items named 0, 1, 2, ... n-1
     *
     * @param n the number of items
     */
    public UnionFind(int n) {
        this.id = new int[n];
        this.size = new int[n];
        for (int i = 0; i < n; i++) {
            this.id[i] = i;
            this.size[i] = 1;
        }
    }

    /**
     * Add a connection between item p and item q
     *
     * @param p the item
     * @param q the item
     */
    public void union(int p, int q) {
        int idp = find(p);
        int idq = find(q);

        if (this.size[idp] > this.size[idq]) {
            // Idp becomes root
            this.id[idq] = idp;
            this.size[idp] += this.size[idq];
        }
        else {
            // Idq becomes root
            this.id[idp] = idq;
            this.size[idq] += this.size[idp];
        }
    }

    /**
     * Find the "component identifier" of item p
     *
     * @param p the item
     * @return component identifier of item p
     */
    public int find(int p) {
        while (id[p] != p) p = id[p];
        return p;
//
//        if (this.id[p] == p)
//            return p;
//
//        this.id[p] = find(this.id[p]);
//        return this.id[p];
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
