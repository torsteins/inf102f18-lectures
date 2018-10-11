package week41;

import week40.AdjListGraph;
import week40.IGraph;

import java.io.IOException;

import static helpers.Verify.verifyEquals;

/**
 * @author Torstein Strømme
 */
public class CCTest {

    public static void main(String[] args) throws IOException {
        sanityTest();
    }

    private static void sanityTest() throws IOException {
        System.out.print("sanityTest for CC... ");
        IGraph graph = GraphReader.readGraph("sampleGraphs/tinyG.in");
        CC cc = new CC(graph);
        verifyEquals(3, cc.count());
//        verifyEquals(cc.id(0), cc.id(1));
//        verifyEquals(cc.id(0), cc.id(5));
        System.out.println("done!");
    }

}
