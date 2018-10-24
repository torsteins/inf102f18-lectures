package week43;

import helpers.Kattio;
import week42.*;

/**
 * Single source shortest path in directed graph without negative weights
 *
 * @author Torstein Strømme
 */
public class ShortestPaths {


    private final EdgeWeightedDigraph graph;
    private final double[] distTo;
    private final int[] parent;

    public ShortestPaths(EdgeWeightedDigraph graph, int s) {
        this.graph = graph;
        this.distTo = new double[graph.n()];
        this.parent = new int[graph.n()];
        for (int i = 0; i < graph.n(); i++) {
            this.distTo[i] = Double.POSITIVE_INFINITY;
            this.parent[i] = -1;
        }
        dijkstra(s);
    }

    private void dijkstra(int s) {
        this.distTo[s] = 0;

        boolean[] reached = new boolean[this.graph.n()];
        IIndexPQ pq = new IndexMinPQ(this.graph.n());
        for (int i = 0; i < this.graph.n(); i++) {
            pq.add(i, this.distTo[i]);
        }

        while (!pq.isEmpty()) {
            int u = pq.poll();
            reached[u] = true;
            for (DirectedEdge e : this.graph.adj(u)) {
                if (!reached[e.to] && this.distTo[e.from] + e.weight < this.distTo[e.to]) {
                    this.parent[e.to] = e.from;
                    this.distTo[e.to] = this.distTo[e.from] + e.weight;
                    pq.changeKey(e.to, this.distTo[e.to]);
                }
            }
        }
    }



    public static void main(String[] args) {
        Kattio io = new Kattio(System.in);

        while (true) {
            int n = io.getInt();
            int m = io.getInt();
            int q = io.getInt();
            int s = io.getInt();
            if (n == 0) break;

            EdgeWeightedDigraph graph = new AdjListEWD(n);
            for (int i = 0; i < m; i++) {
                int from = io.getInt();
                int to = io.getInt();
                double weight = io.getInt();
                graph.addEdge(new DirectedEdge(from, to, weight));
            }

            ShortestPaths sp = new ShortestPaths(graph, s);

            for (int i = 0; i < q; i++) {
                int u = io.getInt();
                if (Double.compare(sp.distTo(u), Double.POSITIVE_INFINITY) == 0) {
                    io.print("Impossible\n");
                }
                else {
                    io.print((int) (sp.distTo(u) + 0.000000001));
                    io.print("\n");
                }
            }
            io.print("\n");
        }
        io.close();
    }

    private double distTo(int u) {
        return this.distTo[u];
    }

}
