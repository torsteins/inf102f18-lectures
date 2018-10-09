package week40;

import week38.RedBlackBST;

import java.util.ArrayList;

/**
 * @author Torstein Strømme
 */
public class AdjListGraph implements IGraph {
    ISortableList<Integer>[] adjList;
    int m = 0;
    int n = 0;

    AdjListGraph(int n) {
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
        this.adjList[v].add(u);
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
        // Runtime O(deg(u))
        for (int nbr : this.adjList[u]) {
            if (nbr == v)
                return true;
        }
        return false;
    }

    @Override
    public Iterable<Integer> adj(int u) {
        // Runtime O(1)
        return this.adjList[u];
    }
}
