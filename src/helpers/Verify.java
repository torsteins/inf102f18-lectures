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

    public static <T> void verifyEquals(Comparable<T> a, Comparable<T> b) {
        if (!a.equals(b))
            throw new AssertionError(String.format(
                    "Verification failed: %s not equal to %s", a.toString(), b.toString()
            ));
    }
}
