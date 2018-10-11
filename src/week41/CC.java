package week41;

import week40.IGraph;

/**
 * information about connected components of a graph
 *
 * @author Torstein Strømme
 */
public class CC {

    private final IGraph graph;
    private final boolean[] visited;
    private int counter = 0;

    public CC(IGraph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.n()];

        for (int i = 0; i < graph.n(); i++) {
            if (!this.visited[i]) {
                dfs(i);
                this.counter++;
            }
        }
    }

    private void dfs(int u) {
        visited[u] = true;
        for (int nbr : this.graph.adj(u)) {
            // How many times here?
            if (!visited[nbr])
                dfs(nbr);
        }
    }

    public int count() {
        return this.counter;
    }

}
