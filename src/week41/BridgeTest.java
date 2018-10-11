package week41;

import week40.IGraph;

import java.io.IOException;
import java.util.Arrays;

import static helpers.Verify.verifyEquals;

/**
 * @author Torstein Strømme
 */
public class BridgeTest {

    public static void main(String[] args) throws IOException {
        sanityTest();
    }

    private static void sanityTest() throws IOException {
        System.out.println("sanityTest for Bridge... ");
        IGraph graph = GraphReader.readGraph("sampleGraphs/tinyG.in");
        Bridge bridge = new Bridge(graph);
//        verifyEquals(true, bridge.hasBridge());

        for (int[] edj : bridge.bridges()) {
            System.out.println(Arrays.toString(edj));
        }
        System.out.println("done!");
    }

}
