package week34;
import static helpers.Verify.*;


/**
 * @author Torstein Strømme
 */
public class TestStacks {

    public static void main(String[] args) {
        basicTest(new MyFixedSizeStack<>(10));
        basicTest(new MyLinkedListStack<>());
        basicTest(new MyDynamicArrayStack<>());

        timeTest(new MyLinkedListStack<>());
        timeTest(new MyDynamicArrayStack<>());
    }

    private static void basicTest(IStack<String> s) {
        verify(s.empty());
        s.push("A");
        s.push("B");
        s.push("C");
        verifyEquals("C", s.pop());
        verifyEquals("B", s.peek());
        verifyFalse(s.empty());
        s.push("D");
        s.push("E");
        verifyEquals("E", s.pop());
        verifyEquals("D", s.pop());
        verifyEquals("B", s.pop());
        verifyEquals("A", s.pop());
        verify(s.empty());
        System.out.println("Success!");
    }

    private static void timeTest(IStack<String> s) {
        long before = System.currentTimeMillis();
        for (int t = 0; t < 20; t++) {
            for (int i = 0; i < 100000; i++) {
                s.push("A");
            }
            for (int i = 0; i < 100000; i++) {
                s.push("A");
                s.pop();
            }
            for (int i = 0; i < 100000; i++) {
                s.pop();
            }
        }
        long after = System.currentTimeMillis();
        System.out.printf("Time %s \t %d ms\n", s.getClass().getCanonicalName(), (after-before));
    }

}
