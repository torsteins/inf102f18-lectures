package week35;

import static helpers.Verify.verify;
import static helpers.Verify.verifyFalse;

/**
 * @author Torstein Strømme
 */
public class UnionFindTestClient {

    /**
     * Test client for UnionFind
     *
     * @param args ignored
     */
    public static void main(String[] args) {
        sanityTest(new UnionFind(10));
    }

    /**
     * Sanity test for UnionFind datastructure
     */
    private static void sanityTest(IUnionFind uf) {
        System.out.printf("Sanity test %s... ", uf.getClass().getName());
        verifyFalse(uf.connected(0, 1));
        verifyFalse(uf.connected(0, 2));

        uf.union(0, 1);
        verify(uf.connected(0, 1));
        verifyFalse(uf.connected(0, 2));
        verifyFalse(uf.connected(1, 2));

        uf.union(0, 2);
        verify(uf.connected(0, 1));
        verify(uf.connected(0, 2));
        verify(uf.connected(1, 2));

        uf.union(3, 4);
        verify(uf.connected(3, 4));
        verifyFalse(uf.connected(2, 3));

        uf.union(4, 1);
        verify(uf.connected(2, 3));
        System.out.println("ok!");
    }
}
