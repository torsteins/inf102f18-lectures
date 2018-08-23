package week34;

import java.util.ArrayList;
import java.util.function.Function;

import static helpers.Verify.verifyEquals;

/**
 * @author Torstein Strømme
 */
public class Evaluate {

    public static void main(String[] args) {
        // Some basic tests of the functionality
        // Note: the problem was NOT (as I said in class) that
        // passed functions can't be recursive, the problem was much sillier;
        // namely that the test cases we generate in the test function
        // are so big that they take a while - and our printing within
        // the function didn't help to make any more sense out of it.
        test(Evaluate::evaluateA, "A");
        test(Evaluate::evaluateB, "B");
    }

    /**
     * Generate a few testcases for Evaluation functions, then test them
     *
     * @param evaluator
     * @param desc
     */
    public static void test(Function<String, Integer> evaluator, String desc) {
        String expr = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        verifyEquals(101, evaluator.apply(expr));

        expr = "( ( ( 1 + 1 ) + 1 ) + 1 )";
        verifyEquals(4, evaluator.apply(expr));

        /// Runtime test
        int size = 5000;
        ArrayList<Character> tokens = new ArrayList<Character>();
        for (int i=0; i<size-1; i++) {
            tokens.add('(');
        }
        tokens.add('1');
        for (int i=0; i<size-1; i++) {
            tokens.add('+');
            tokens.add('1');
            tokens.add(')');
        }
        StringBuilder sb = new StringBuilder();
        for (char token : tokens) {
            sb.append(token);
            sb.append(' ');
        }
        expr = sb.substring(0, sb.length()-1);
        long before = System.currentTimeMillis();
        evaluator.apply(expr);
        long after = System.currentTimeMillis();
        System.out.printf("Success for %s, ran for %d ms\n", desc, (after-before));
    }

    /**
     * First attempt: Find outermost operation, and solve recursively
     *
     * In worst case, the outermost operation is the rightmost one,
     * so we need to read the entire string to find operator (neverminding
     * that we spend linear time in each call just to split the string
     * - but that can be avoided by a more clever implementation)
     *
     * Worst case total runtime is thus on the order of O(n^2) - n calls
     * (one for each operator), each taking n/2 time
     *
     *
     * @param expr
     * @return
     */
    public static int evaluateA(String expr) {
        int i = 0;
        int depth = 0;
        for (String token : expr.split(" ")) {
            i += token.length() + 1;
            if (token.equals("(")) depth++;
            else if (token.equals(")")) depth--;
            else if (depth == 1 && token.equals("+")) {
                // Do plus
                int leftside = evaluateA(expr.substring(2, i-3));
                int rightside = evaluateA(expr.substring(i, expr.length()-2));
                return leftside + rightside;

            }
            else if (depth == 1 && token.equals("*")) {
                String leftside = expr.substring(2, i-3);
                String rightside = expr.substring(i,expr.length()-2);
                return evaluateA(leftside) * evaluateA(rightside);
            }
            else {
                ;
            }
        }
        return Integer.parseInt(expr);
    }

    /**
     * Second attempt: Dijkstra's two-stack algorithm
     * Runtime: O(n)
     *
     * @param expr
     * @return
     */
    public static int evaluateB(String expr) {
        IStack<Integer> values = new MyDynamicArrayStack<>();
        IStack<String> operators = new MyDynamicArrayStack<>();
        for (String token : expr.split(" ")) {
            if (token.equals("(")) {
                ;
            }
            else if (token.equals("*")) {
                operators.push(token);
            }
            else if (token.equals("+")) {
                operators.push(token);
            }
            else if (token.equals(")")) {
                int a = values.pop();
                int b = values.pop();
                String op = operators.pop();
                if (op.equals("*")) {
                    values.push(a*b);
                }
                else if (op.equals("+")) {
                    values.push(a+b);
                }
            }
            else {
                values.push(Integer.parseInt(token));
            }
        }
        return values.pop();
    }


}
