package week41;

import week34.IStack;
import week34.MyLinkedListStack;
import week40.IBag;

/**
 * @author Torstein Strømme
 */
public class Topological {

    private IDigraph graph;
    private int[] pre, post;
    IStack<Integer> revPostOrder = new MyLinkedListStack<>();
    int time = 0;

    public Topological(IDigraph graph) {
        this.graph = graph;
        this.pre = new int[graph.n()];
        this.post = new int[graph.n()];
        for (int i = 0; i < graph.n(); i++) {
            this.pre[i] = -1;
            this.post[i] = -1;
        }

        for (int i = 0; i < graph.n(); i++) {
            if (this.pre[i] < 0)
                dfs(i);
        }
    }

    private void dfs(int u) {
        this.pre[u] = this.time++;
        for (int nbr : this.graph.adj(u)) {
            if (this.pre[nbr] < 0) {
                dfs(nbr);
            }
            else if (this.post[nbr] < 0) {
                // Back edge!
                throw new IllegalArgumentException("Not a DAG!");
            }
        }
        this.post[u] = this.time++;
        this.revPostOrder.push(u);
    }

    IBag<Integer> order() {
        return this.revPostOrder;
    }
}
