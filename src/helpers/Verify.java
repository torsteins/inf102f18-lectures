package helpers;

/**
 * A class to help with verification (similar to assert)
 *
 * @author Torstein Strømme
 */
public class Verify {

    public static void verify(boolean value) {
        if (!value)
            throw new AssertionError("Verification failed");
    }

    public static void verifyFalse(boolean value) {
        if (value)
            throw new AssertionError("Verification failed");
    }

    public static <T> void verifyEquals(Comparable<T> expected, Comparable<T> result) {
        if (!expected.equals(result))
            throw new AssertionError(String.format(
                    "Verification failed: %s not equal to %s", expected.toString(), result.toString()
            ));
    }

    public static void verifyNull(Object o) {
        if (o != null) {
            throw new AssertionError("Object " + o + " not null!");
        }
    }
}
