package week43;

/**
 * @author Torstein Strømme
 */
public final class DirectedEdge implements Comparable<DirectedEdge> {
    public final int from;
    public final int to;
    public final double weight;

    public DirectedEdge(int from, int to, double weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(DirectedEdge that) {
        return Double.compare(this.weight, that.weight);
    }

    @Override
    public String toString() {
        return "[ "+ this.from+ "->" + this.to + ", w="+this.weight+" ]";
    }
}
