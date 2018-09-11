package week37;

import java.util.PriorityQueue;

import static helpers.Verify.verifyEquals;

/**
 * @author Torstein Strømme
 */
public class PQTestClient {

    public static void main(String[] args) {
        sanityTest(new HeapPQ<String>());
        PriorityQueue<String> pqOrg = new PriorityQueue<String>();
    }

    private static void sanityTest(IPriorityQueue<String> pq) {
        System.out.printf("Testing (sanityTest) PriorityQueue %s... ", pq.getClass().getCanonicalName());

        pq.add("B");
        pq.add("C");
        pq.add("A");

        verifyEquals("C", pq.peek());
        verifyEquals(3, pq.size());
        verifyEquals("C", pq.poll());
        verifyEquals(2, pq.size());
        verifyEquals("B", pq.poll());
        verifyEquals("A", pq.poll());
        verifyEquals(0, pq.size());

        pq.add("P");
        pq.add("Q");
        pq.add("E");

        verifyEquals("Q", pq.poll());

        pq.add("X");
        pq.add("A");
        pq.add("M");

        verifyEquals("X", pq.poll());

        pq.add("P");
        pq.add("L");
        pq.add("E");

        verifyEquals("P", pq.poll());

        System.out.println("success!");
    }

}
