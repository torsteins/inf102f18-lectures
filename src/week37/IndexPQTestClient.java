package week37;

import static helpers.Verify.verify;
import static helpers.Verify.verifyEquals;
import static helpers.Verify.verifyFalse;

/**
 * @author Torstein Strømme
 */
public class IndexPQTestClient {

    public static void main(String[] args) {
        sanityTest(new IndexPQ<String>(20));
    }

    private static void sanityTest(IIndexPQ<String> pq) {
        System.out.printf("Sanity test %s... ", pq.getClass().getName());
        pq.add(0, "A");
        pq.add(3, "K");
        pq.add(6, "E");
        pq.add(2, "J");
        verifyEquals(4, pq.size());
        verifyEquals(3, pq.peek());
        verifyEquals("K", pq.peekKey());
        verifyEquals("K", pq.getKey(3));
        verifyEquals("A", pq.getKey(0));
        verifyEquals("J", pq.getKey(2));


        verify(pq.containsIndex(3));
        verifyEquals(3, pq.poll());
        verifyFalse(pq.containsIndex(3));
        verifyEquals(2, pq.poll());

        pq.changeKey(0, "Z");
        verifyEquals("Z", pq.peekKey());
        verifyEquals(0, pq.poll());
        verifyEquals("E", pq.peekKey());
        verifyEquals(6, pq.poll());
        verifyEquals(0, pq.size());

        System.out.println("success!");
    }
}
