package week42;

/**
 * @author Torstein Strømme
 */
public class Edge implements Comparable<Edge> {
    private final int u;
    private final int v;
    public final double w;

    public Edge(int u, int v, double w) {
        this.u = u;
        this.v = v;
        this.w = w;
    }

    public int either() {
        return u;
    }

    public int other(int u) {
        return u == this.u ? this.v : this.u;
    }

    @Override
    public int compareTo(Edge that) {
        return Double.compare(this.w, that.w);
    }

    @Override
    public String toString() {
        return "[ "+ this.u+ ", " + this.v + ", w="+this.w+" ]";
    }
}
