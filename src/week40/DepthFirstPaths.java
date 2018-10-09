package week40;

import week34.MyDynamicArrayStack;
import week34.MyLinkedListStack;

/**
 * @author Torstein Strømme
 */
public class DepthFirstPaths {

    private final IGraph graph;
    private int[] parent;
    private boolean[] visited;

    public DepthFirstPaths(IGraph graph, int root) {
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
