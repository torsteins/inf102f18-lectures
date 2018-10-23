package week42;

import week37.ISymTable;
import week38.RedBlackBST;

/**
 * @author Torstein Strømme
 */
public class PrimsIPQ implements MST {

    private final EdgeWeightedGraph graph;
    private final ISymTable<Integer, Integer> tree = new RedBlackBST<>();
    private double weight = 0;

    public PrimsIPQ(EdgeWeightedGraph graph) {
        this.graph = graph;
        prim();
    }

    private void prim() {
        IndexMinPQ<Double> pq = new IndexMinPQ<>(graph.n());
        for (int i = 0; i < graph.n(); i++) {
            pq.add(i, Double.POSITIVE_INFINITY);
        }

        pq.changeKey(0, 0.0);

        while (tree.size() < this.graph.n()) {
            Double weight = pq.peekKey();
            int u = pq.poll();

            this.weight += weight;
            this.tree.put(u, 0);

            for (Edge e : graph.adj(u)) {
                if (!this.tree.containsKey(e.other(u))) {
                    if (pq.getKey(e.other(u)) > e.w) {
                        pq.changeKey(e.other(u), e.w);
                    }
                }
            }
        }
    }

    @Override
    public Iterable<Edge> edges() {
        return null;
    }

    @Override
    public double weight() {
        return this.weight;
    }
}
