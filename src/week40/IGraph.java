package week40;

/**
 * @author Torstein Strømme
 */
public interface IGraph {

    /**
     * Add an edge between vertex u and vertex v in the graph.
     * @param u a vertex
     * @param v a vertex
     */
    void addEdge(int u, int v);

    /**
     * How many vertices are in this graph?
     * @return the number of vertices
     */
    int n();

    /**
     * How many edges are in this graph?
     * @return the number of edges
     */
    int m();

    /**
     * Check whether two vertices are adjacent
     * @param u a vertex
     * @param v a vertex
     * @return true if u and v are adjacent, false otherwise
     */
    boolean areAdj(int u, int v);

    /**
     * Return an iterator over the neighbours of a vertex
     * @param u a vertex
     * @return iterator over u's neighbours
     */
    Iterable<Integer> adj(int u);

}
