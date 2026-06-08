import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class WordSquareBuilderTest {

    public static void main(String[] args) {
        run("testBuildWordSquareWithInjectedDictionary", WordSquareBuilderTest::testBuildWordSquareWithInjectedDictionary);
        run("testBuildWordSquareReturnsErrorWhenCharsAreInsufficient", WordSquareBuilderTest::testBuildWordSquareReturnsErrorWhenCharsAreInsufficient);
        System.out.println("All tests passed.");
    }

    private static void run(String name, Runnable test) {
        try {
            test.run();
            System.out.println("[PASS] " + name);
        } catch (Throwable t) {
            System.err.println("[FAIL] " + name);
            t.printStackTrace();
            System.exit(1);
        }
    }

    private static void testBuildWordSquareWithInjectedDictionary() {
        HashSet<String> words = new HashSet<>(Arrays.asList("BALL", "AREA", "LEAD", "LADY"));
        Dictionary dictionary = new Dictionary(words);
        WordSquareBuilder builder = new WordSquareBuilder(dictionary, 4);

        HashMap<Character, Integer> charCount = charCount("BALLAREALEADLADY");
        List<String> square = builder.buildWordSquare(charCount);

        List<String> expected = Arrays.asList("BALL", "AREA", "LEAD", "LADY");
        assertEquals("Word square should match expected square", expected, square);
    }

    private static void testBuildWordSquareReturnsErrorWhenCharsAreInsufficient() {
        HashSet<String> words = new HashSet<>(Arrays.asList("BALL", "AREA", "LEAD", "LADY"));
        Dictionary dictionary = new Dictionary(words);
        WordSquareBuilder builder = new WordSquareBuilder(dictionary, 4);

        HashMap<Character, Integer> charCount = charCount("BALLAREA");
        try {
            builder.buildWordSquare(charCount);
            throw new AssertionError("Expected IllegalArgumentException for insufficient characters");
        } catch (IllegalArgumentException expected) {
            // expected
        }
    }

    private static HashMap<Character, Integer> charCount(String input) {
        HashMap<Character, Integer> map = new HashMap<>();
        for (char c : input.toCharArray()) {
            map.put(c, map.getOrDefault(c, 0) + 1);
        }
        return map;
    }

    private static void assertEquals(String message, Object expected, Object actual) {
        if (expected == null) {
            if (actual != null) {
                throw new AssertionError(message + ": expected null but was " + actual);
            }
            return;
        }
        if (!expected.equals(actual)) {
            throw new AssertionError(message + ": expected " + expected + " but was " + actual);
        }
    }

}
