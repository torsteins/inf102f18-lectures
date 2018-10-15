package week41;

import week40.IBag;

/**
 * @author Torstein Strømme
 */
public interface IDigraph {

    /**
     * Add an edge from vertex u to vertex v in the graph.
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
     * Return an iterator over the neighbours of a vertex
     * @param u a vertex
     * @return iterator over u's neighbours
     */
    IBag<Integer> adj(int u);


    /**
     * Return the reverse of the current graph (a new IDigraph object)
     *
     * @return the reverse of the graph
     */
    IDigraph reverse();

}
