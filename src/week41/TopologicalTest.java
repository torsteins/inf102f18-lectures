package week41;

import java.io.IOException;

/**
 * @author Torstein Strømme
 */
public class TopologicalTest {

    public static void main(String[] args) throws IOException {
        sanityTest();
    }

    private static void sanityTest() throws IOException {
        System.out.println("sanity test Topological sort");
        IDigraph graph = GraphReader.readDigraph("sampleGraphs/tinyDG.in");
        Topological top = new Topological(graph);
        for (int i : top.order()) {
            System.out.println(i);
        }
        System.out.println("done!");
    }
}
