package week41;

import week34.MyLinkedListStack;
import week40.IGraph;

/**
 * @author Torstein Strømme
 */
public class DepthFirstPathsDG {


    private final IDigraph graph;
    private int[] parent;
    private boolean[] visited;

    public DepthFirstPathsDG(IDigraph graph, int root) {
        this.graph = graph;
        this.visited = new boolean[graph.n()];
        this.parent = new int[graph.n()];
        for (int j = 0; j < graph.n(); j++) {
            this.parent[j] = -1;
        }

        dfs(root);
    }

    private void dfs(int u) {
        this.visited[u] = true;
        for (int nbr : graph.adj(u)) {
            if (!visited[nbr]) {
                this.parent[nbr] = u;
                dfs(nbr);
            }
        }
    }

    public boolean hasPathTo(int u) {
        return this.visited[u];
    }

    public Iterable<Integer> pathTo(int u) {
        MyLinkedListStack<Integer> stack = new MyLinkedListStack<>();
        while (u >= 0) {
            stack.push(u);
            u = parent[u];
        }
        return stack;
    }
}
