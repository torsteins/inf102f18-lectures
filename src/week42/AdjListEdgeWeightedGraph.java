package week42;

import jdk.nashorn.api.scripting.AbstractJSObject;
import week40.IBag;
import week40.ISortableList;
import week40.SortableLinkedList;

/**
 * @author Torstein Strømme
 */
public class AdjListEdgeWeightedGraph implements EdgeWeightedGraph {
    ISortableList<Edge>[] graph;
    ISortableList<Edge> edgeList = new SortableLinkedList<>();

    public AdjListEdgeWeightedGraph(int n) {
        this.graph = new SortableLinkedList[n];
        for (int i = 0; i < n; i++) {
            this.graph[i] = new SortableLinkedList<>();
        }
    }

    @Override
    public void addEdge(Edge e) {
        int u = e.either();
        int v = e.other(u);
        graph[u].add(e);
        graph[v].add(e);
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
    public IBag<Edge> adj(int u) {
        return this.graph[u];
    }

    @Override
    public IBag<Edge> edges() {
        return this.edgeList;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < this.n(); i++) {
            sb.append(i);
            sb.append(":");
            for (Edge e : graph[i]) {
                sb.append(" (");
                sb.append(e.other(i));
                sb.append(", ");
                sb.append(e.w);
                sb.append(")");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
