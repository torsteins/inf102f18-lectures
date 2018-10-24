package week43;

import week40.IBag;
import week40.ISortableList;
import week40.SortableLinkedList;

/**
 * @author Torstein Strømme
 */
public class AdjListEWD implements EdgeWeightedDigraph {
    ISortableList<DirectedEdge>[] graph;
    ISortableList<DirectedEdge> edgeList = new SortableLinkedList<>();

    public AdjListEWD(int n) {
        this.graph = new SortableLinkedList[n];
        for (int i = 0; i < n; i++) {
            this.graph[i] = new SortableLinkedList<>();
        }
    }

    @Override
    public void addEdge(DirectedEdge e) {
        graph[e.from].add(e);
        edgeList.add(e);
    }

    @Override
    public int n() {
        return this.graph.length;
    }

    @Override
    public int m() {
        return 0;
    }

    @Override
    public IBag<DirectedEdge> adj(int u) {
        return this.graph[u];
    }

    @Override
    public IBag<DirectedEdge> edges() {
        return this.edgeList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.n(); i++) {
            sb.append(i);
            sb.append(":");
            for (DirectedEdge e : graph[i]) {
                sb.append(e.toString());
            }
            sb.append("\n");
        }
        return sb.toString();
    }

}
