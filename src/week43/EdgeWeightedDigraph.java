package week43;

import week40.IBag;

/**
 * Interface of a directed edge weighted graph
 *
 * @author Torstein Strømme
 */
public interface EdgeWeightedDigraph {
    /**
     * Add an edge between vertex u and vertex v in the graph.
     * @param e the edge
     */
    void addEdge(DirectedEdge e);

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
    IBag<DirectedEdge> adj(int u);

    /**
     * Return an iterator over the edges of the graph
     * @return iterator over all edges of the graph
     */
    IBag<DirectedEdge> edges();
}
