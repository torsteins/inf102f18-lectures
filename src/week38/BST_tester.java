package week38;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static helpers.Verify.verifyEquals;
import static helpers.Verify.verify;
import static helpers.Verify.verifyFalse;


public class BST_tester {

    public static void main(String[] args) {
        test(new BinarySearchTree<String, Integer>());
    }

    private static void test(BinarySearchTree<String, Integer> bst) {
        System.out.println("Testing binary search tree: " + bst.getClass().getCanonicalName());

        verifyEquals(bst.size(), 0);
        verifyFalse(bst.containsKey("A"));

        bst.put("B", 4);
        verifyEquals(bst.size(), 1);
        bst.put("C", 5);
        verifyEquals(bst.size(), 2);
        bst.put("A", 3);
        verifyEquals(bst.size(), 3);

        verify(bst.containsKey("A"));
        verify(bst.containsKey("B"));
        verify(bst.containsKey("C"));

        verifyFalse(bst.containsKey("D"));

        verifyEquals(bst.get("B"), 4);


        bst.delete("A");
        verifyEquals(bst.size(), 2);

        verifyFalse(bst.containsKey("A"));
        verifyNull(bst.get("A"));

        verify(bst.containsKey("B"));
        verify(bst.containsKey("C"));

        verifyEquals(bst.get("C"), 5);

        bst.delete("D");
        verifyEquals(bst.size(), 2);
        verifyFalse(bst.containsKey("D"));

        bst.put("A", 32);
        verifyEquals(bst.get("A"), 32);
        verifyEquals(bst.size(), 3);

        String all = StreamSupport
                .stream(bst.keys().spliterator(), false)
                .collect(Collectors.joining(", "));
        verifyEquals("A, B, C", all);


        System.out.println("All ok!");
    }

    static void verifyNull(Object o) {
        if (o != null) {
            throw new AssertionError("Object " + o + " not null!");
        }
    }
}
