package week42;

import week41.GraphReader;

import java.io.IOException;

/**
 * @author Torstein Strømme
 */
public class MSTTest {

    public static void main(String[] args) throws IOException {
        EdgeWeightedGraph graph = GraphReader.readEWGraph("sampleGraphs/tinyEWG.in");

        MST mst = new KruskalsUF(graph);
        MST mst2 = new PrimsIPQ(graph);

        System.out.println(mst.weight());
        System.out.println(mst2.weight());

    }
}
