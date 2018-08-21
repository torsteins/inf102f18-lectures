package week34;

/**
 * @author Torstein Strømme
 */
public class TestStacks {

    public static void main(String[] args) {
//        basicTest(new MyFixedSizeStack<String>(10));
        basicTest(new MyLinkedListStack<String>());
    }

    private static void basicTest(IStack<String> s) {
        assert(false);
        assert(s.empty()); // Should be true
        s.push("A");
        s.push("B");
        s.push("C");
        System.out.println(s.pop());   // Should be C
        System.out.println(s.peek());  // Should be B
        System.out.println(s.empty()); // Should be false
        s.push("D");
        s.push("E");
        System.out.println(s.pop());   // Should be E
        System.out.println(s.pop());   // Should be D
        System.out.println(s.pop());   // Should be B
        System.out.println(s.pop());   // Should be A
        System.out.println(s.empty()); // Should be true
    }

}
