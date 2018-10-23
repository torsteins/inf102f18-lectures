package week42;

import week35.UnionFind;
import week40.SortableLinkedList;

/**
 * @author Torstein Strømme
 */
public class KruskalsUF implements MST {

    private final EdgeWeightedGraph graph;
    private final SortableLinkedList<Edge> sortedEdges = new SortableLinkedList<>();
    private final SortableLinkedList<Edge> solution = new SortableLinkedList<>();
    private double weight;

    KruskalsUF(EdgeWeightedGraph graph) {
        this.graph = graph;
        for (Edge e : graph.edges()) {
            sortedEdges.add(e);
        }
        sortedEdges.sort();
        kruskalUF();
    }

    private void kruskalUF() {
        UnionFind uf = new UnionFind(graph.n());

        for (Edge e : sortedEdges) {

            int u = e.either();
            int v = e.other(u);

            if (uf.find(u) == uf.find(v)) {
                // ignore
            }
            else {
                this.solution.add(e);
                this.weight += e.w;
                uf.union(u, v);
            }
        }
    }


    @Override
    public Iterable<Edge> edges() {
        return this.solution;
    }

    @Override
    public double weight() {
        return this.weight;
    }
}
