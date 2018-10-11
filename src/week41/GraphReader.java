package week41;

import week40.AdjListGraph;
import week40.IGraph;

import java.io.*;

/**
 * @author Torstein Strømme
 */
public class GraphReader {


    public static IGraph readGraph(String filename) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(filename)));
        int n = Integer.parseInt(br.readLine().trim());
        int m = Integer.parseInt(br.readLine().trim());

        IGraph graph = new AdjListGraph(n);
        for (int i = 0; i < m; i++) {
            String[] edj = br.readLine().split("\\s+");
            graph.addEdge(Integer.parseInt(edj[0]), Integer.parseInt(edj[1]));
        }
        return graph;
    }
}
