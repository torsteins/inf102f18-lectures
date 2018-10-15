package week41;

import java.io.IOException;

/**
 * @author Torstein Strømme
 */
public class DigraphTest {

    public static void main(String[] args) throws IOException {
        reachabilityTest();
    }

    private static void reachabilityTest() throws IOException {
        System.out.println("Digraph reachability test");
        IDigraph graph = GraphReader.readDigraph("sampleGraphs/tinyDG.in");
        DepthFirstPathsDG paths = new DepthFirstPathsDG(graph, 0);

        for (int i = 0; i < 10; i++) {
            System.out.println("" + i + " " + paths.hasPathTo(i));
        }

        System.out.println("done!");
    }
}
