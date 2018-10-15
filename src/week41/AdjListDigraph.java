package week41;

import week40.IBag;
import week40.ISortableList;
import week40.SortableLinkedList;

/**
 * @author Torstein Strømme
 */
public class AdjListDigraph implements IDigraph {

    ISortableList<Integer>[] adjList;
    int m = 0;
    int n = 0;

    public AdjListDigraph(int n) {
        this.adjList = new SortableLinkedList[n];
        for (int i = 0; i < n; i++) {
            this.adjList[i] = new SortableLinkedList<>();
        }
        this.n = n;
    }


    @Override
    public void addEdge(int u, int v) {
        // Runtime O(1)
        this.adjList[u].add(v);
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
    public IBag<Integer> adj(int u) {
        // Runtime O(1)
        return this.adjList[u];
    }

    @Override
    public IDigraph reverse() {
        return null;
    }
}
