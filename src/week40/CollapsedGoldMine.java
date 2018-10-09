package week40;

/**
 * @author Torstein Strømme
 */
public class CollapsedGoldMine {

    public static void main(String[] args) {
        // Read input
        int n = 6;
        int m = 7;
        int[][] edges = {{3, 1}, {1, 0}, {1, 4}, {2, 4}, {0, 2}, {5, 4}, {2, 5}};

        // Solve problem
        System.out.println(collapsedGoldMine(n, m, edges));
    }

    private static String collapsedGoldMine(int n, int m, int[][] edges) {
        // Create the graph object
        IGraph graph = new AdjMtxGraph(n);
        for (int i = 0; i < m; i++) {
            graph.addEdge(edges[i][0], edges[i][1]);
        }

        // Solve the problem
        DepthFirstPaths search = new DepthFirstPaths(graph, 0);

        for (int v : search.pathTo(n-1)) {
            System.out.println(v);
        }

        return search.hasPathTo(n-1) ? "yes" : "no";
    }
}
