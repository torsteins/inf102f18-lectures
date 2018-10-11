package week41;


import jdk.nashorn.internal.ir.annotations.Ignore;
import week34.MyDynamicArrayStack;
import week40.IBag;
import week40.IGraph;

/**
 * @author Torstein Strømme
 */
public class Bridge {

    private final IGraph graph;
    private final boolean[] visited;
    private final int[] depth;
    private final int[] parent;
    private IBag<int[]> bridges = new MyDynamicArrayStack<>();


    public Bridge(IGraph graph) {
        this.graph = graph;
        this.visited = new boolean[graph.n()];
        this.depth = new int[graph.n()];
        this.parent = new int[graph.n()];
        for (int i = 0; i < graph.n(); i++) {
            this.parent[i] = -1;
        }

        for (int i = 0; i < graph.n(); i++) {
            if (!visited[i])
                dfs(i, 0);
        }

    }

    private int dfs(int u, int depth) {
        this.visited[u] = true;
        this.depth[u] = depth;
        int oldestAncestorDepth = depth;
        for (int nbr : this.graph.adj(u)) {
            if (!visited[nbr]) {
                this.parent[nbr] = u;
                oldestAncestorDepth = Math.min(oldestAncestorDepth, dfs(nbr, depth+1));
            }
            else if (nbr != this.parent[u]) {
                // Back edge
                oldestAncestorDepth = Math.min(oldestAncestorDepth, this.depth[nbr]);
            }
        }

        if (oldestAncestorDepth >= depth && depth > 0) {
            // Found a cut edge - the one DFS tree edge pointing to me.
            this.bridges.add(new int[]{u, this.parent[u]});
        }

        return oldestAncestorDepth;
    }

    public IBag<int[]> bridges() {
        return this.bridges;
    }

}
